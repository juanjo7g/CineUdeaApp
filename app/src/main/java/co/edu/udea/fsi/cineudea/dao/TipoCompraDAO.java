package co.edu.udea.fsi.cineudea.dao;

import java.util.List;

import co.edu.udea.fsi.cineudea.dto.TipoCompra;

/**
 * Created by Juan on 17/01/2016.
 */
public interface TipoCompraDAO {
    TipoCompra leerTipoCompra(String id);

    List<TipoCompra> leerListaTipoCompras();
}
