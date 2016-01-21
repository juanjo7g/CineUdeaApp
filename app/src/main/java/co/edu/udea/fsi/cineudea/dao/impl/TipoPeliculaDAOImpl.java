package co.edu.udea.fsi.cineudea.dao.impl;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.fsi.cineudea.dao.TipoPeliculaDAO;
import co.edu.udea.fsi.cineudea.dto.TipoPelicula;

/**
 * Created by Juan on 18/01/2016.
 */
public class TipoPeliculaDAOImpl implements TipoPeliculaDAO {

    @Override
    public TipoPelicula leerTipoPelicula(String id) {
        ParseQuery<ParseObject> queryTipoPelicula = null;
        List<ParseObject> resultado;
        TipoPelicula tipoPelicula;

        try {
            queryTipoPelicula = ParseQuery.getQuery("TipoPelicula");
            queryTipoPelicula.whereEqualTo("codigo", id);
            resultado = queryTipoPelicula.find();

            if (resultado != null) {
                tipoPelicula = new TipoPelicula();
                tipoPelicula.setId(resultado.get(0).getString("codigo"));
                tipoPelicula.setDescripcion(resultado.get(0).getString("descripcion"));

                return tipoPelicula;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<TipoPelicula> leerListaTipoPeliculas() {
        ParseQuery<ParseObject> queryTipoPelicula = null;
        List<ParseObject> resultado;
        TipoPelicula tipoPelicula;
        List<TipoPelicula> listaTipoPeliculas;

        try {
            queryTipoPelicula = ParseQuery.getQuery("TipoPelicula");
            resultado = queryTipoPelicula.find();
            listaTipoPeliculas = new ArrayList<>();

            if (resultado != null) {
                for (int i = 0; i < resultado.size(); i++) {
                    tipoPelicula = new TipoPelicula();
                    tipoPelicula.setId(resultado.get(i).getString("codigo"));
                    tipoPelicula.setDescripcion(resultado.get(i).getString("descripcion"));

                    listaTipoPeliculas.add(tipoPelicula);
                }
                return listaTipoPeliculas;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
