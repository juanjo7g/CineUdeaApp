package co.edu.udea.fsi.cineudea.dao.impl;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.fsi.cineudea.dao.CineDAO;
import co.edu.udea.fsi.cineudea.dao.SalaDAO;
import co.edu.udea.fsi.cineudea.dto.Cine;
import co.edu.udea.fsi.cineudea.dto.Sala;

/**
 * Created by Juan on 17/01/2016.
 */
public class SalaDAOImpl implements SalaDAO {

    CineDAO daoCine = new CineDAOImpl();

    @Override
    public Sala leerSala(int numero, int codigoCine) {
        ParseQuery<ParseObject> querySala = null;
        ParseQuery<ParseObject> queryCine = null;
        List<ParseObject> resultado;
        Sala sala;
        Cine cine;

        try {
            querySala = ParseQuery.getQuery("Sala");
            querySala.whereEqualTo("numero", numero);

            queryCine = ParseQuery.getQuery("Cine");
            queryCine.whereEqualTo("codigo", codigoCine);

            querySala.whereEqualTo("cine", queryCine.find().get(0));

            resultado = querySala.find();

            if (resultado != null) {
                sala = new Sala();
                sala.setNumero(resultado.get(0).getInt("numero"));
                sala.setEstado(resultado.get(0).getString("estado"));
                cine = daoCine.leerCine(resultado.get(0).getParseObject("cine").getInt("codigo"));
                sala.setCine(cine);

                return sala;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Sala> leerListaSalas() {
        ParseQuery<ParseObject> querySala = null;
        List<ParseObject> resultado;
        Sala sala;

        List<Sala> listaSalas;
        try {
            querySala = ParseQuery.getQuery("Sala");
            resultado = querySala.find();

            listaSalas = new ArrayList<>();

            if (resultado != null) {
                for (int i = 0; i < resultado.size(); i++) {
                    sala = new Sala();
                    sala.setNumero(resultado.get(i).getInt("numero"));
                    sala.setEstado(resultado.get(i).getString("estado"));
                    sala.setCine(daoCine.leerCine(resultado.get(i).getParseObject("cine").fetchIfNeeded().getInt("codigo")));

                    listaSalas.add(sala);
                }
                return listaSalas;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
