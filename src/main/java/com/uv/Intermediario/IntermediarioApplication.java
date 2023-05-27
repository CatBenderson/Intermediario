package com.uv.Intermediario;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import mx.xlp.ReadOneCamionResponse;

@SpringBootApplication
public class IntermediarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntermediarioApplication.class, args);
	}

	@Bean
	public CommandLineRunner ejecutar(CamionesCliente camionesCliente){
		//*  este código con expresión lambda se ejecuta una vez concluida la carga de la aplicacion */
		return x -> {
			ReadOneCamionResponse sr = camionesCliente.leerUno(1);
			System.err.println(sr.getCamion().getId());
			System.err.println(sr.getCamion().getChofer());
			System.err.println(sr.getCamion().getCarga().getCantidad());
			System.err.println(sr.getCamion().getCarga().getObjeto());
			System.err.println(sr.getCamion().getUbicacion().getLatitud());
			System.err.println(sr.getCamion().getUbicacion().getLongitud());
		};
		
		//* en cambio, este codigo se ejecuta durante la carga de la aplicaicón */
		// SaludarResponse sr = camionesCliente.saludar("yo-yo");
		// System.err.println(sr.getRespuesta());
		// return null;
	}

}
