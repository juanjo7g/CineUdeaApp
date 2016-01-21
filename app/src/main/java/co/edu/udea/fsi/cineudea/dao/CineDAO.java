package co.edu.udea.fsi.cineudea.dao;

import java.util.List;

import co.edu.udea.fsi.cineudea.dto.Cine;

/**
 * Created by Juan on 17/01/2016.
 */
public interface CineDAO {
    Cine leerCine(int codigo);
    List<Cine> leerListaCines();
}
