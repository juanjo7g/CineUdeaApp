package co.edu.udea.fsi.cineudea.dao;

import java.util.List;

import co.edu.udea.fsi.cineudea.dto.Pelicula;

/**
 * Created by Juan on 17/01/2016.
 */
public interface PeliculaDAO {
    Pelicula leerPelicula(String id);

    List<Pelicula> leerListaPeliculas();
}
