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
    //Definición de la url del servicio a consumir (Camiones SOAP)
    private final String API_URL = "https://camiones-production.up.railway.app/ws";

    /*Método para recibir una respuesta del servidor SOAP al solicitar todos los camiones.
    Define el elemento request que se enviará al servicio SOAP, fue generado manualmente ya que al ser una petición nula, las dependencias no lo generan de manera automática.
    */
    public ReadAllCamionResponse leerTodos(){
        JAXBElement<ReadAllCamionRequest> requestElement = new JAXBElement<>(new QName("https://t4is.uv.mx/camiones", "ReadAllCamionRequest"), ReadAllCamionRequest.class,null);
        return (ReadAllCamionResponse) getWebServiceTemplate().marshalSendAndReceive(API_URL, requestElement);
    }

    /*Método para recibir una respuesta del servidor SOAP al solicitar un camion en específico.
    Asigna a la request el valor del id a consultar.
    */
    public ReadOneCamionResponse leerUno(Integer id){
        ReadOneCamionRequest readOneRequest = new ReadOneCamionRequest();
        readOneRequest.setId(id);
        return (ReadOneCamionResponse) getWebServiceTemplate().marshalSendAndReceive(API_URL, readOneRequest);
    }
    
    /*Método para recibir una respuesta del servidor SOAP al agregar un camion.
    Recibe un objeto de tipo camión y asigna a la request el valor del cada uno de los atributos que conforman la petición
    */
    public AgregarCamionResponse agregar(Camion camion){
        AgregarCamionRequest agregarRequest = new AgregarCamionRequest();
        agregarRequest.setChofer(camion.getChofer());
        agregarRequest.setCantidad(camion.getCantidad());
        agregarRequest.setObjeto(camion.getObjeto());
        agregarRequest.setLatitud(camion.getLatitud());
        agregarRequest.setLongitud(camion.getLongitud());
        agregarRequest.setCelsius(camion.getTemperatura().get(0));
        
        return (AgregarCamionResponse) getWebServiceTemplate().marshalSendAndReceive(API_URL, agregarRequest);
    }
    
    /*Método para recibir una respuesta del servidor SOAP al modificar un camion en específico.
    Recibe un id y asigna a la request el valor del cada uno de los atributos que conforman la petición.
    En el caso de la temperatura, realiza una conversión para poder pasarlo como una String en lugar de un Array<Double>.
    */
    public ModificarCamionResponse modificar(Integer id,Camion camion){
        String valoresTemp = "";
        for(Double valor : camion.getTemperatura()){
            valoresTemp = valoresTemp + String.valueOf(valor) + ",";
        }
        
        ModificarCamionRequest modificarRequest = new ModificarCamionRequest();
        modificarRequest.setChofer(camion.getChofer());
        modificarRequest.setCantidad(camion.getCantidad());
        modificarRequest.setObjeto(camion.getObjeto());
        modificarRequest.setLatitud(camion.getLatitud());
        modificarRequest.setLongitud(camion.getLongitud());
        modificarRequest.setCelsius(valoresTemp);
        modificarRequest.setId(id);
        
        return (ModificarCamionResponse) getWebServiceTemplate().marshalSendAndReceive(API_URL, modificarRequest);
    }
    
    /*Método para recibir una respuesta del servidor SOAP al eliminar un camion en específico.
    Asigna a la request el valor del id a eliminar.
    */
    public EliminarCamionResponse eliminar(Integer id){
        EliminarCamionRequest eliminarRequest = new EliminarCamionRequest();
        eliminarRequest.setId(id);

        return (EliminarCamionResponse) getWebServiceTemplate().marshalSendAndReceive(API_URL, eliminarRequest);
    }
}