package com.uv.Intermediario;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class IntermediarioConfiguration {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("mx.xlp");
        return marshaller;
    }

    @Bean
    public CamionesCliente saludosCliente(Jaxb2Marshaller mar) {
        CamionesCliente mCliente = new CamionesCliente();
        mCliente.setDefaultUri("http://localhost:8080/ws");
        mCliente.setMarshaller(mar);
        mCliente.setUnmarshaller(mar);
        return mCliente;
    }
}