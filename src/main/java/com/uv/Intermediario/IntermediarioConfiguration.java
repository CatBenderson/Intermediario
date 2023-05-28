package com.uv.intermediario;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class IntermediarioConfiguration{
    @Bean
    public Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("mx.xlp");
        return marshaller;
    }

    @Bean
    public CamionesCliente camionesCliente(Jaxb2Marshaller mar){
        CamionesCliente cCliente = new CamionesCliente();
        cCliente.setDefaultUri("http://localhost:8080/ws");
        cCliente.setMarshaller(mar);
        cCliente.setUnmarshaller(mar);
        return cCliente;
    }
}