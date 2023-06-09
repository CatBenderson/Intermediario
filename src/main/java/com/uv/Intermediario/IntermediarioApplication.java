package com.uv.Intermediario;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import mx.xlp.ReadAllCamionResponse;
import mx.xlp.ReadOneCamionResponse;

//Anotación para permitir solicitudes de cualquier origen (CORS)
@CrossOrigin(origins = "*", maxAge = 3600)

//Clase RestController que define los recursos que el servicio va a ofrecer
@RestController
@SpringBootApplication
public class IntermediarioApplication {
	@Autowired
	private CamionesCliente camionesCliente;

	public static void main(String[] args) {
		SpringApplication.run(IntermediarioApplication.class, args);
	}

	/*Recurso que recupera los datos de todos los camiones registrados en la base de datos. Los almacena en un 
	Array<JSON> y los regresa junto a un estado de la petición
	*/
	@RequestMapping(value = "/camiones", method = RequestMethod.GET)
	public String getCamiones() {
		JSONObject respuesta = new JSONObject();
		List<JSONObject> data = new ArrayList<>();
		try {
			ReadAllCamionResponse camiones = camionesCliente.leerTodos();
			respuesta.put("status", camiones.getStatus());
			for (ReadAllCamionResponse.Camion readAllCamion : camiones.getCamion()) {
				JSONObject a = new JSONObject();
				a.put("id", readAllCamion.getId());
				a.put("chofer", readAllCamion.getChofer());
				a.put("objeto", readAllCamion.getCarga().getObjeto());
				a.put("cantidad", readAllCamion.getCarga().getCantidad());
				a.put("latitud", readAllCamion.getUbicacion().getLatitud());
				a.put("longitud", readAllCamion.getUbicacion().getLongitud());
				a.put("temperatura", readAllCamion.getTemperatura().getCelsius());
				data.add(a);
			}
			respuesta.put("data", data);
			// return respuesta.toString();
		} catch (Exception e) {
			respuesta.put("status", "Failed");
		}
		return respuesta.toString();
	}
	
	/*Recurso que recupera los datos de un camión registrado en la base de datos. Toma el id del recurso 
	y regresa sus datos en un formato JSON junto a un estado de la petición
	*/
	@RequestMapping(value = "/camiones/{id}", method = RequestMethod.GET)
	public String getCamion(@PathVariable Integer id) {
		JSONObject respuesta = new JSONObject();
		JSONObject data = new JSONObject();
		try {
			ReadOneCamionResponse sr = camionesCliente.leerUno(id);
			respuesta.put("status", sr.getStatus());
			data.put("id", sr.getCamion().getId());
			data.put("chofer", sr.getCamion().getChofer());
			data.put("objeto", sr.getCamion().getCarga().getObjeto());
			data.put("cantidad", sr.getCamion().getCarga().getCantidad());
			data.put("latitud", sr.getCamion().getUbicacion().getLatitud());
			data.put("longitud", sr.getCamion().getUbicacion().getLongitud());
			data.put("temperatura", sr.getCamion().getTemperatura().getCelsius());
			respuesta.put("data", data);
		} catch (Exception e) {
			respuesta.put("status", "Failed");
		}
		return respuesta.toString();
	}
	
	/*Recurso que registra los datos de un camión en la base de datos. Toma el objeto camión del cuerpo de la petición
	y tras procesarlos regresa el estado de la petición;
	*/
	@RequestMapping(value = "/camiones", method = RequestMethod.POST)
	public String addCamion(@RequestBody Camion camion) {
		JSONObject respuesta = new JSONObject();
		try {
			camionesCliente.agregar(camion);
			respuesta.put("status", "Success");
		} catch (Exception e) {
			respuesta.put("status", "Failed");
		}
		return respuesta.toString();
	}
	
	/*Recurso que actualiza los datos de un camión en la base de datos. Toma el objeto camión del cuerpo de la petición
	y tras procesarlos regresa el estado de la petición;
	*/
	@RequestMapping(value = "/camiones/{id}", method = RequestMethod.PUT)
	public String updateCamion(@PathVariable Integer id, @RequestBody Camion camion) {
		JSONObject respuesta = new JSONObject();
		try {
			ReadOneCamionResponse sr = camionesCliente.leerUno(id);
			if (sr.getCamion() != null) {
				respuesta.put("status", "Success");
				camionesCliente.modificar(id,camion);
			}
		} catch (Exception e) {
			respuesta.put("status", "Failed");
		}
		return respuesta.toString();
	}

	/*Recurso que elimina los datos de un camión registrado en la base de datos. Toma el id del recurso 
	y tras procesarlo regresa el estado de la petición
	*/
	@RequestMapping(value = "/camiones/{id}", method = RequestMethod.DELETE)
	public String deleteCamion(@PathVariable Integer id) {
		JSONObject respuesta = new JSONObject();
		try {
			ReadOneCamionResponse sr = camionesCliente.leerUno(id);
			if (sr.getCamion() != null) {
				camionesCliente.eliminar(id);

			}
			respuesta.put("status", "Success");
		} catch (Exception e) {
			respuesta.put("status", "Failed");
		}
		return respuesta.toString();
	}
}