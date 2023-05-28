package com.uv.intermediario;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import mx.xlp.AgregarCamionRequest;
import mx.xlp.ModificarCamionRequest;
import mx.xlp.ReadAllCamionResponse;
import mx.xlp.ReadOneCamionResponse;

@Mapper
public interface CamionMapper{
    @Mapping(source = "valorTemp", target = "celsius")
    AgregarCamionRequest camionToAgregarCamion(Camion camion, Double valorTemp);

    @Mapping(source = "valoresTemp", target = "celsius")
    ModificarCamionRequest camionToModificarCamion(Camion camion, String valoresTemp);

    @Mapping(source = "camion.temperatura.celsius", target = "temperatura")
    @Mapping(source = "camion.carga.objeto", target = "objeto")
    @Mapping(source = "camion.carga.cantidad", target = "cantidad")
    @Mapping(source = "camion.ubicacion.latitud", target = "latitud")
    @Mapping(source = "camion.ubicacion.longitud", target = "longitud")
    Camion readAllCamionToCamion(ReadAllCamionResponse.Camion camion);

    @Mapping(source = "camion.temperatura.celsius", target = "temperatura")
    @Mapping(source = "camion.carga.objeto", target = "objeto")
    @Mapping(source = "camion.carga.cantidad", target = "cantidad")
    @Mapping(source = "camion.ubicacion.latitud", target = "latitud")
    @Mapping(source = "camion.ubicacion.longitud", target = "longitud")
    Camion readOneCamionToCamion(ReadOneCamionResponse.Camion camion);
}