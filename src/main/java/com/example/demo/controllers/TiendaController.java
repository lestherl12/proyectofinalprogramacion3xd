package com.example.demo.controllers;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.example.demo.controllers.util.VentaBody;
import com.example.demo.entities.Cliente;
import com.example.demo.entities.Producto;
import com.example.demo.entities.Venta;
import com.example.demo.other.Email;
import com.example.demo.services.ClientesService;
import com.example.demo.services.ProductosService;
import com.example.demo.services.VentasService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/tienda")
public class TiendaController {

    public final ProductosService productosService;
    public final ClientesService clientesService;
    public final VentasService ventasService;
    private final Email emailService;



    @Autowired
    public TiendaController(ProductosService productosService, ClientesService clientesService, VentasService ventasService, Email emailService) {
        this.productosService = productosService;
        this.clientesService = clientesService;
        this.ventasService = ventasService;
        this.emailService = emailService;
    }


    @GetMapping("/productos")
    public Page<Producto> getProducts(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(required = false) String category
                                     ) {
        return productosService.getPaginate(page, size, category);
    }
    @GetMapping(value = "/venta")
    public Venta GetByEcommerce(){

        AmazonSQS sqs = AmazonSQSClientBuilder.standard()
                .withCredentials(new ProfileCredentialsProvider("credenciales-umg"))
                .withRegion(Regions.US_WEST_2)
                .build();

        String queueUrl = sqs.getQueueUrl("cola-umg-sqs.fifo").getQueueUrl();

        List<Message> messages = sqs.receiveMessage(queueUrl).getMessages();
        for (Message m : messages) {
            sqs.deleteMessage(queueUrl, m.getReceiptHandle());
            String jsonBody = m.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                VentaBody body = objectMapper.readValue(jsonBody, VentaBody.class);
                Producto producto = productosService.getById(body.getProducto_id());
                Cliente cliente = clientesService.getById(body.getCliente_id());

                Venta venta = new Venta();
                venta.setCliente(cliente);
                venta.setProducto(producto);
                venta.setEstado("Pedido Recibido");
                venta.setCantidad(body.getCantidad());
                venta.setTotal(venta.getCantidad()*producto.getPrecio());
                producto.registrarVenta(body.getCantidad());
                productosService.update(producto.getProducto_id(),producto);

                if (cliente!=null){
                    if (cliente.getCorreo()!=null){
                        emailService.send(cliente.getCorreo(),"Pedido Recibido", "tu pedido ha sido recibido");
                    }
                }

                return ventasService.create(venta);

            } catch (Exception e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }
        return null;
    }

    @PostMapping("/venta")
    public void create(@RequestBody VentaBody body) {
        CompletableFuture.runAsync(() -> {
            ObjectMapper objectMapper = new ObjectMapper();
            String mensaje ="";
            try {
                mensaje = objectMapper.writeValueAsString(body);
            } catch (IOException e) {
                e.getMessage();
            }

            AmazonSQS sqs = AmazonSQSClientBuilder.standard()
                    .withCredentials(new ProfileCredentialsProvider("credenciales-umg"))
                    .withRegion(Regions.US_WEST_2)
                    .build();

            String url = sqs.getQueueUrl("cola-umg-sqs.fifo").getQueueUrl();
            SendMessageRequest send_msg_request = new SendMessageRequest()
                    .withQueueUrl(url)
                    .withMessageBody(mensaje)
                    .withMessageGroupId("VENTAS")
                    .withMessageDeduplicationId(UUID.randomUUID().toString());

            sqs.sendMessage(send_msg_request);

            Cliente cliente = clientesService.getById(body.getCliente_id());


            if (cliente!=null){
                if (cliente.getCorreo()!=null){

                    emailService.send(cliente.getCorreo(),"Pedido Recibido", "tu pedido ha sido recibido");
                }
            }

        });

    }

}
