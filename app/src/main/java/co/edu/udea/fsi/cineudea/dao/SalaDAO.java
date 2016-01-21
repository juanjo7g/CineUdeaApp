package co.edu.udea.fsi.cineudea.dao;

import java.util.List;

import co.edu.udea.fsi.cineudea.dto.Sala;

/**
 * Created by Juan on 17/01/2016.
 */
public interface SalaDAO {
    Sala leerSala(int numero, int codigoCine);

    List<Sala> leerListaSalas();
}
