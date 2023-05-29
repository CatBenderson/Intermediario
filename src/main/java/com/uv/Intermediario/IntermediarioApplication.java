package com.uv.Intermediario;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.xlp.ReadAllCamionResponse;

@RestController
@SpringBootApplication
public class IntermediarioApplication{
	@Autowired
	private CamionesCliente camionesCliente;
	private CamionMapper mapper;
	private String result = "{\"status\": \"Failed\"}";

	public static void main(String[] args){
		SpringApplication.run(IntermediarioApplication.class, args);
	}

	
	@RequestMapping(value = "/camiones", method = RequestMethod.GET)
	public String getCamiones(){
		try{
			ReadAllCamionResponse camiones = camionesCliente.leerTodos();

			if(camiones != null){
				result = "{\"status\": \"Success\", \"data\": [";
	
				for(ReadAllCamionResponse.Camion readAllCamion : camiones.getCamion())
					result = result + "{" + mapper.readAllCamionToCamion(readAllCamion).toString() + "}, ";
	
				result = result + "]}";
			}
		}catch(Exception e){}

		return new JSONObject(result).toString();

		//*  este código con expresión lambda se ejecuta una vez concluida la carga de la aplicacion */
		// return x -> {
		// 	ReadOneCamionResponse sr = camionesCliente.leerUno(1);
		// 	System.err.println(sr.getCamion().getId());
		// 	System.err.println(sr.getCamion().getChofer());
		// 	System.err.println(sr.getCamion().getCarga().getCantidad());
		// 	System.err.println(sr.getCamion().getCarga().getObjeto());
		// 	System.err.println(sr.getCamion().getUbicacion().getLatitud());
		// 	System.err.println(sr.getCamion().getUbicacion().getLongitud());
		// };
		
		//* en cambio, este codigo se ejecuta durante la carga de la aplicaicón */
		// SaludarResponse sr = camionesCliente.saludar("yo-yo");
		// System.err.println(sr.getRespuesta());
		// return null;
	}

	
	@RequestMapping(value = "/camiones/{id}", method = RequestMethod.GET)
	public String getCamion(@PathVariable Integer id){
		try{
			Camion camion = mapper.readOneCamionToCamion(camionesCliente.leerUno(id).getCamion());

			if(camion != null) result = "{\"status\": \"Success\", \"data\": {" + camion.toString() + "}}";
		}catch(Exception e){}

		return new JSONObject(result).toString();
	}

	
	@RequestMapping(value = "/camiones", method = RequestMethod.POST)
	public String addCamion(@RequestBody Camion camion){
		try{
			if(camion != null){
				camionesCliente.agregar(camion);
				result = "{\"status\": \"Success\"}";
			}
		}catch(Exception e){}

		return new JSONObject(result).toString();
	}

	
	@RequestMapping(value = "/camiones/{id}", method = RequestMethod.PUT)
	public String updateCamion(@PathVariable Integer id, @RequestBody Camion camion){
		try{
			if(camion != null){
				if(mapper.readOneCamionToCamion(camionesCliente.leerUno(id).getCamion()) != null){
					camionesCliente.modificar(camion);
					result = "{\"status\": \"Success\", \"data\": {" + camion.toString() + "}}";
				}
			}
		}catch(Exception e){}

		return new JSONObject(result).toString();
	}

	
	@RequestMapping(value = "/camiones/{id}", method = RequestMethod.DELETE)
	public String deleteCamion(@PathVariable Integer id){
		try{
			Camion camion = mapper.readOneCamionToCamion(camionesCliente.leerUno(id).getCamion());

			if(camion != null){
				camionesCliente.eliminar(id);
				result = "{\"status\": \"Success\"}";
			}
		}catch(Exception e){}

		return new JSONObject(result).toString();
	}
}