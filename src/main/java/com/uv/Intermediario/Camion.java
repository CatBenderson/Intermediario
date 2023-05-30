package com.uv.Intermediario;

import java.util.ArrayList;
import org.json.JSONObject;

public class Camion{
    private Integer id;
    private String chofer;
    private ArrayList<Double> temperatura;
    private String objeto;
    private Integer cantidad;
    private Double latitud;
    private Double longitud;

    public Camion(Integer id, String chofer, ArrayList<Double> temperatura, String objeto, Integer cantidad, Double latitud, Double longitud){
        this.id = id;
        this.chofer = chofer;
        this.temperatura = temperatura;
        this.objeto = objeto;
        this.cantidad = cantidad;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getChofer(){
        return chofer;
    }

    public void setChofer(String chofer){
        this.chofer = chofer;
    }

    public ArrayList<Double> getTemperatura(){
        return temperatura;
    }

    public void setTemperatura(ArrayList<Double> temperatura){
        this.temperatura = temperatura;
    }

    public String getObjeto(){
        return objeto;
    }

    public void setObjeto(String objeto){
        this.objeto = objeto;
    }

    public Integer getCantidad(){
        return cantidad;
    }

    public void setCantidad(Integer cantidad){
        this.cantidad = cantidad;
    }

    public Double getLatitud(){
        return latitud;
    }

    public void setLatitud(Double latitud){
        this.latitud = latitud;
    }

    public Double getLongitud(){
        return longitud;
    }

    public void setLongitud(Double longitud){
        this.longitud = longitud;
    }

    public String toString(){
        JSONObject respuesta = new JSONObject();
        respuesta.put("id", this.getId());
        respuesta.put("chofer", this.getChofer());
        respuesta.put("temperatura", this.getTemperatura());
        respuesta.put("objeto", this.getObjeto());
        respuesta.put("cantidad", this.getCantidad());
        respuesta.put("latitud", this.getLatitud());
        respuesta.put("longitud", this.getLongitud());
        return respuesta.toString();
    }
}