package co.edu.udea.fsi.cineudea.dao.impl;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.fsi.cineudea.dao.CineDAO;
import co.edu.udea.fsi.cineudea.dto.Cine;

/**
 * Created by Juan on 18/01/2016.
 */
public class CineDAOImpl implements CineDAO {
    @Override
    public Cine leerCine(int codigo) {
        ParseQuery<ParseObject> queryCine = null;
        List<ParseObject> resultado;
        Cine cine;
        try {
            queryCine = ParseQuery.getQuery("Cine");
            queryCine.whereEqualTo("codigo", codigo);
            resultado = queryCine.find();
            if (resultado != null) {
                cine = new Cine();
                cine.setCodigo(resultado.get(0).getInt("codigo"));
                cine.setNombre(resultado.get(0).getString("nombre"));
                cine.setCiudad(resultado.get(0).getString("ciudad"));
                cine.setDireccion(resultado.get(0).getString("direccion"));
                cine.setFoto(resultado.get(0).getParseFile("foto").getData());

                return cine;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Cine> leerListaCines() {
        ParseQuery<ParseObject> queryCine = null;
        List<ParseObject> resultado;
        Cine cine;
        List<Cine> listaCines;

        try {
            queryCine = ParseQuery.getQuery("Cine");
            resultado = queryCine.find();
            listaCines = new ArrayList<>();
            if (resultado != null) {
                for (int i = 0; i < resultado.size(); i++) {
                    cine = new Cine();
                    cine.setCodigo(resultado.get(i).getInt("codigo"));
                    cine.setNombre(resultado.get(i).getString("nombre"));
                    cine.setCiudad(resultado.get(i).getString("ciudad"));
                    cine.setDireccion(resultado.get(i).getString("direccion"));

                    cine.setFoto(resultado.get(i).getParseFile("foto").getData());
                    
                    listaCines.add(cine);
                }
                return listaCines;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
