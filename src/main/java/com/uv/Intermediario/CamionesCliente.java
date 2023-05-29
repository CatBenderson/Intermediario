package com.uv.Intermediario;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import mx.xlp.AgregarCamionRequest;
import mx.xlp.AgregarCamionResponse;
import mx.xlp.EliminarCamionRequest;
import mx.xlp.EliminarCamionResponse;
import mx.xlp.ModificarCamionRequest;
import mx.xlp.ModificarCamionResponse;
import mx.xlp.ReadAllCamionResponse;
import mx.xlp.ReadOneCamionRequest;
import mx.xlp.ReadOneCamionResponse;

public class CamionesCliente extends WebServiceGatewaySupport{
    private final String API_URL = "http://localhost:8090/ws/camiones";
    private CamionMapper mapper;

    public ReadAllCamionResponse leerTodos(){
        JAXBElement<ReadAllCamionRequest> requestElement = new JAXBElement<>(new QName("https://t4is.uv.mx/camiones", "ReadAllCamionRequest"), ReadAllCamionRequest.class,null);
        return (ReadAllCamionResponse) getWebServiceTemplate().marshalSendAndReceive(API_URL, requestElement);
    }

    public ReadOneCamionResponse leerUno(Integer id){
        ReadOneCamionRequest readOneRequest = new ReadOneCamionRequest();
        readOneRequest.setId(id);
        System.out.println(getDefaultUri());

        return (ReadOneCamionResponse) getWebServiceTemplate().marshalSendAndReceive(API_URL, readOneRequest);
    }

    public AgregarCamionResponse agregar(Camion camion){
        AgregarCamionRequest agregarRequest = mapper.camionToAgregarCamion(camion, camion.getTemperatura().get(0));

        return (AgregarCamionResponse) getWebServiceTemplate().marshalSendAndReceive(API_URL, agregarRequest);
    }

    public ModificarCamionResponse modificar(Camion camion){
        String valoresTemp = "";
        for(Double valor : camion.getTemperatura()){
            valoresTemp = valoresTemp + String.valueOf(valor) + ",";
        }

        ModificarCamionRequest modificarRequest = mapper.camionToModificarCamion(camion, valoresTemp);

        return (ModificarCamionResponse) getWebServiceTemplate().marshalSendAndReceive(API_URL, modificarRequest);
    }

    public EliminarCamionResponse eliminar(Integer id){
        EliminarCamionRequest eliminarRequest = new EliminarCamionRequest();
        eliminarRequest.setId(id);

        return (EliminarCamionResponse) getWebServiceTemplate().marshalSendAndReceive(API_URL, eliminarRequest);
    }
}