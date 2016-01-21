package co.edu.udea.fsi.cineudea.dao.impl;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.fsi.cineudea.dao.LocalidadDAO;
import co.edu.udea.fsi.cineudea.dao.TipoCompraDAO;
import co.edu.udea.fsi.cineudea.dao.TipoPeliculaDAO;
import co.edu.udea.fsi.cineudea.dto.TipoCompra;

/**
 * Created by Juan on 19/01/2016.
 */
public class TipoCompraDAOImpl implements TipoCompraDAO {

    TipoPeliculaDAO daoTipoPelicula = new TipoPeliculaDAOImpl();
    LocalidadDAO daoLocalidad = new LocalidadDAOImpl();

    @Override
    public TipoCompra leerTipoCompra(String id) {
        ParseQuery<ParseObject> queryTipoCompra = null;
        List<ParseObject> resultado;
        TipoCompra tipoCompra;

        try {
            queryTipoCompra = ParseQuery.getQuery("TipoCompra");
            queryTipoCompra.whereEqualTo("codigo", id);
            resultado = queryTipoCompra.find();

            if (resultado != null) {
                tipoCompra = new TipoCompra();
                tipoCompra.setId(resultado.get(0).getString("codigo"));
                tipoCompra.setValorEnDinero((float) resultado.get(0).getDouble("valorEnDinero"));
                tipoCompra.setValorEnPuntos(resultado.get(0).getInt("valorEnPuntos"));

                tipoCompra.setTipoPelicula(daoTipoPelicula.leerTipoPelicula(resultado.get(0).getParseObject("tipoPelicula").getString("codigo")));

                tipoCompra.setLocalidad(daoLocalidad.leerLocalidad(resultado.get(0).getParseObject("localidad").getString("codigo")));

                return tipoCompra;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TipoCompra> leerListaTipoCompras() {
        ParseQuery<ParseObject> queryTipoCompra = null;
        List<ParseObject> resultado;
        TipoCompra tipoCompra;
        List<TipoCompra> listaTipoCompras;

        try {
            queryTipoCompra = ParseQuery.getQuery("TipoCompra");
            resultado = queryTipoCompra.find();
            listaTipoCompras = new ArrayList<>();
            if (resultado != null) {
                for (int i = 0; i < resultado.size(); i++) {
                    tipoCompra = new TipoCompra();
                    tipoCompra.setId(resultado.get(i).getString("codigo"));
                    tipoCompra.setValorEnDinero((float) resultado.get(i).getDouble("valorEnDinero"));
                    tipoCompra.setValorEnPuntos(resultado.get(i).getInt("valorEnPuntos"));

                    tipoCompra.setTipoPelicula(daoTipoPelicula.leerTipoPelicula(resultado.get(i).getParseObject("tipoPelicula").fetchIfNeeded().getString("codigo")));

                    tipoCompra.setLocalidad(daoLocalidad.leerLocalidad(resultado.get(i).getParseObject("localidad").fetchIfNeeded().getString("codigo")));

                    listaTipoCompras.add(tipoCompra);
                }
                return listaTipoCompras;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
