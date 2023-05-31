package com.uv.Intermediario;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


//Clase de configuración y conexión entre ambos servicios
@Configuration
public class IntermediarioConfiguration{

    // Marshaller que permite autogenerar las clases y las almacena en el paquete "mx.xlp"
    @Bean
    public Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("mx.xlp");
        return marshaller;
    }

    //Establece el contexto donde funcionará el servicio (Puerto, uri y marshaller)
    @Bean
    public CamionesCliente camionesCliente(Jaxb2Marshaller mar){
        CamionesCliente cCliente = new CamionesCliente();
        cCliente.setDefaultUri("http://localhost:8080/ws");
        cCliente.setMarshaller(mar);
        cCliente.setUnmarshaller(mar);
        return cCliente;
    }
}