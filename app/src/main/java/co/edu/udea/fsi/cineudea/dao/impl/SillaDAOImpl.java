package co.edu.udea.fsi.cineudea.dao.impl;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.fsi.cineudea.dao.SalaDAO;
import co.edu.udea.fsi.cineudea.dao.SillaDAO;
import co.edu.udea.fsi.cineudea.dto.Cine;
import co.edu.udea.fsi.cineudea.dto.Sala;
import co.edu.udea.fsi.cineudea.dto.Silla;

/**
 * Created by Juan on 17/01/2016.
 */
public class SillaDAOImpl implements SillaDAO {
    SalaDAO daoSala = new SalaDAOImpl();

    @Override
    public Silla leerSilla(int numero, int numeroSala, int codigoCineSala) {
        ParseQuery<ParseObject> querySilla = null;
        ParseQuery<ParseObject> querySala = null;
        ParseQuery<ParseObject> queryCine = null;
        List<ParseObject> resultado;

        Silla silla;
        Sala sala;

        try {
            querySilla = ParseQuery.getQuery("Silla");
            querySilla.whereEqualTo("numero", numero);

            querySala = ParseQuery.getQuery("Sala");
            querySala.whereEqualTo("numero", numeroSala);

            queryCine = ParseQuery.getQuery("Cine");
            queryCine.whereEqualTo("codigo", codigoCineSala);

            querySala.whereEqualTo("cine", queryCine.find().get(0));

            querySilla.whereEqualTo("sala", querySala.find().get(0));

            resultado = querySilla.find();

            if (resultado != null) {
                silla = new Silla();
                silla.setNumero(resultado.get(0).getInt("numero"));
                silla.setFila(resultado.get(0).getInt("fila"));
                silla.setColumna(resultado.get(0).getInt("columna"));
                sala = daoSala.leerSala(resultado.get(0).getParseObject("sala").getInt("numero"),
                        resultado.get(0).getParseObject("sala").getParseObject("cine").getInt("codigo"));
                silla.setSala(sala);

                return silla;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Silla> leerListaSillas() {
        ParseQuery<ParseObject> querySilla = null;
        List<ParseObject> resultado;
        Silla silla;
        Sala sala;
        List<Silla> listaSillas;

        try {
            querySilla = ParseQuery.getQuery("Silla");
            resultado = querySilla.find();
            listaSillas = new ArrayList<>();

            if (resultado != null) {
                for (int i = 0; i < resultado.size(); i++) {
                    silla = new Silla();

                    silla.setNumero(resultado.get(i).getInt("numero"));
                    silla.setFila(resultado.get(i).getInt("fila"));
                    silla.setColumna(resultado.get(i).getInt("columna"));
                    sala = daoSala.leerSala(resultado.get(i).getParseObject("sala").getInt("numero"),
                            resultado.get(i).getParseObject("sala").getParseObject("cine").getInt("codigo"));
                    silla.setSala(sala);

                    listaSillas.add(silla);
                }
                return listaSillas;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
