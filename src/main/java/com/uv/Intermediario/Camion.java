package com.uv.Intermediario;

import java.util.ArrayList;

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
        return "\"id\": \"" + this.getId() + "\", \"chofer\": \"" + this.getChofer() + "\", \"temperatura\": " + this.getTemperatura().toString() + ", \"objeto\": \"" + this.getObjeto() + "\", \"cantidad\": \"" + this.getCantidad() + "\", \"latitud\": \"" + this.getLatitud() + "\", \"longitud\": \"" + this.getLongitud() + "\"";
    }
}