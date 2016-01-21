package co.edu.udea.fsi.cineudea.dao;

import java.util.List;

import co.edu.udea.fsi.cineudea.dto.TipoCompra;
import co.edu.udea.fsi.cineudea.dto.TipoPelicula;

/**
 * Created by Juan on 17/01/2016.
 */
public interface TipoPeliculaDAO {
    TipoPelicula leerTipoPelicula(String id);
    List<TipoPelicula> leerListaTipoPeliculas();
}
