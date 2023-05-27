package com.uv.Intermediario;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import mx.xlp.ReadOneCamionRequest;
import mx.xlp.ReadOneCamionResponse;

public class CamionesCliente extends WebServiceGatewaySupport {
    
    public ReadOneCamionResponse leerUno(Integer id) {
        ReadOneCamionRequest rr = new ReadOneCamionRequest();
        rr.setId(id);
        System.out.println(getDefaultUri());
        ReadOneCamionResponse readOneCamionResponse = (ReadOneCamionResponse) getWebServiceTemplate().marshalSendAndReceive("http://localhost:8090/ws/camiones", rr);
        return readOneCamionResponse;
    }
}
