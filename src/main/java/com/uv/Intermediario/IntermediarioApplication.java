package com.uv.Intermediario;

import java.util.ArrayList;
import java.util.List;

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
public class IntermediarioApplication {
	@Autowired
	private CamionesCliente camionesCliente;
	private CamionMapper mapper;
	private String result = "{\"status\": \"Failed\"}";

	public static void main(String[] args) {
		SpringApplication.run(IntermediarioApplication.class, args);
	}

	@RequestMapping(value = "/camiones", method = RequestMethod.GET)
	public String getCamiones() {
		JSONObject respuesta = new JSONObject();
		JSONObject data = new JSONObject();
		try {
			ReadAllCamionResponse camiones = camionesCliente.leerTodos();
			respuesta.put("status", camiones.getStatus());
			for (ReadAllCamionResponse.Camion readAllCamion : camiones.getCamion()) {
				JSONObject a = new JSONObject();
				String aux = readAllCamion.getId() +"";
				a.put("id", readAllCamion.getId());
				a.put("chofer", readAllCamion.getChofer());
				a.put("objeto", readAllCamion.getCarga().getObjeto());
				a.put("cantidad", readAllCamion.getCarga().getCantidad());
				a.put("latitud", readAllCamion.getUbicacion().getLatitud());
				a.put("longitud", readAllCamion.getUbicacion().getLongitud());
				data.put(aux, a);
			}
			respuesta.put("data", data);
			//return respuesta.toString();
		} catch (Exception e) {
			respuesta.put("status", "Failed");
		}
		return respuesta.toString();
	}

	@RequestMapping(value = "/camiones/{id}", method = RequestMethod.GET)
	public String getCamion(@PathVariable Integer id) {
		try {
			Camion camion = mapper.readOneCamionToCamion(camionesCliente.leerUno(id).getCamion());

			if (camion != null)
				result = "{\"status\": \"Success\", \"data\": {" + camion.toString() + "}}";
		} catch (Exception e) {
		}

		return new JSONObject(result).toString();
	}

	@RequestMapping(value = "/camiones", method = RequestMethod.POST)
	public String addCamion(@RequestBody Camion camion) {
		try {
			if (camion != null) {
				camionesCliente.agregar(camion);
				result = "{\"status\": \"Success\"}";
			}
		} catch (Exception e) {
		}

		return new JSONObject(result).toString();
	}

	@RequestMapping(value = "/camiones/{id}", method = RequestMethod.PUT)
	public String updateCamion(@PathVariable Integer id, @RequestBody Camion camion) {
		try {
			if (camion != null) {
				if (mapper.readOneCamionToCamion(camionesCliente.leerUno(id).getCamion()) != null) {
					camionesCliente.modificar(camion);
					result = "{\"status\": \"Success\", \"data\": {" + camion.toString() + "}}";
				}
			}
		} catch (Exception e) {
		}

		return new JSONObject(result).toString();
	}

	@RequestMapping(value = "/camiones/{id}", method = RequestMethod.DELETE)
	public String deleteCamion(@PathVariable Integer id) {
		try {
			Camion camion = mapper.readOneCamionToCamion(camionesCliente.leerUno(id).getCamion());

			if (camion != null) {
				camionesCliente.eliminar(id);
				result = "{\"status\": \"Success\"}";
			}
		} catch (Exception e) {
		}

		return new JSONObject(result).toString();
	}
}