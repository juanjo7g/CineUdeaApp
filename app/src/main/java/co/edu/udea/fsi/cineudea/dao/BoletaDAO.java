package co.edu.udea.fsi.cineudea.dao;

import co.edu.udea.fsi.cineudea.dto.Boleta;

/**
 * Created by Juan on 17/01/2016.
 */
public interface BoletaDAO {
    void crearBoleta(Boleta boleta);

    Boleta leerBoleta(String id);
}
