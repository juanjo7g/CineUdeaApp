package co.edu.udea.fsi.cineudea.dao;

import java.util.List;

import co.edu.udea.fsi.cineudea.dto.Localidad;

/**
 * Created by Juan on 17/01/2016.
 */
public interface LocalidadDAO {
    Localidad leerLocalidad(String id);

    List<Localidad> leerListaLocalidades();
}
