package co.edu.udea.fsi.cineudea.dao.impl;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.fsi.cineudea.dao.PeliculaDAO;
import co.edu.udea.fsi.cineudea.dto.Pelicula;

/**
 * Created by Juan on 18/01/2016.
 */
public class PeliculaDAOImpl implements PeliculaDAO {
    @Override
    public Pelicula leerPelicula(String id) {
        ParseQuery<ParseObject> queryPelicula = null;
        List<ParseObject> resultado;
        Pelicula pelicula;

        try {
            queryPelicula = ParseQuery.getQuery("Pelicula");
            queryPelicula.whereEqualTo("codigo", id);
            resultado = queryPelicula.find();

            if (resultado != null) {
                pelicula = new Pelicula();
                pelicula.setId(resultado.get(0).getString("codigo"));
                pelicula.setNombre(resultado.get(0).getString("nombre"));
                pelicula.setDescripcion(resultado.get(0).getString("descripcion"));
                pelicula.setFechaEstreno(resultado.get(0).getDate("fechaEstreno"));
                pelicula.setPortada(resultado.get(0).getParseFile("portada").getData());

                return pelicula;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Pelicula> leerListaPeliculas() {
        ParseQuery<ParseObject> queryPelicula = null;
        List<ParseObject> resultado;
        Pelicula pelicula;

        List<Pelicula> listaPeliculas;

        try {
            queryPelicula = ParseQuery.getQuery("Pelicula");
            resultado = queryPelicula.find();
            listaPeliculas = new ArrayList<>();

            if (resultado != null) {
                for (int i = 0; i < resultado.size(); i++) {
                    pelicula = new Pelicula();

                    pelicula.setId(resultado.get(i).getString("codigo"));
                    pelicula.setNombre(resultado.get(i).getString("nombre"));
                    pelicula.setDescripcion(resultado.get(i).getString("descripcion"));
                    pelicula.setFechaEstreno(resultado.get(i).getDate("fechaEstreno"));
                    pelicula.setPortada(resultado.get(i).getParseFile("portada").getData());

                    listaPeliculas.add(pelicula);
                }
                return listaPeliculas;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
