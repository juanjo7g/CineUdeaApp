package co.edu.udea.fsi.cineudea.dao.impl;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.fsi.cineudea.dao.LocalidadDAO;
import co.edu.udea.fsi.cineudea.dto.Localidad;

/**
 * Created by Juan on 18/01/2016.
 */
public class LocalidadDAOImpl implements LocalidadDAO {
    @Override
    public Localidad leerLocalidad(String id) {
        ParseQuery<ParseObject> queryLocalidad = null;
        List<ParseObject> resultado;
        Localidad localidad;

        try {
            queryLocalidad = ParseQuery.getQuery("Localidad");
            queryLocalidad.whereEqualTo("codigo", id);
            resultado = queryLocalidad.find();

            if (resultado != null) {
                localidad = new Localidad();
                localidad.setId(resultado.get(0).getString("codigo"));
                localidad.setNombre(resultado.get(0).getString("nombre"));

                return localidad;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Localidad> leerListaLocalidades() {
        ParseQuery<ParseObject> queryLocalidad = null;
        List<ParseObject> resultado;
        Localidad localidad;
        List<Localidad> listaLocalidades;

        try {
            queryLocalidad = ParseQuery.getQuery("Localidad");
            resultado = queryLocalidad.find();
            listaLocalidades = new ArrayList<>();

            if (resultado != null) {
                for (int i = 0; i < resultado.size(); i++) {
                    localidad = new Localidad();
                    localidad.setId(resultado.get(i).getString("codigo"));
                    localidad.setNombre(resultado.get(i).getString("nombre"));

                    listaLocalidades.add(localidad);
                }
                return listaLocalidades;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
