package co.edu.udea.fsi.cineudea.dao.impl;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.fsi.cineudea.dao.FuncionDAO;
import co.edu.udea.fsi.cineudea.dao.PeliculaDAO;
import co.edu.udea.fsi.cineudea.dao.SalaDAO;
import co.edu.udea.fsi.cineudea.dao.TipoPeliculaDAO;
import co.edu.udea.fsi.cineudea.dto.Funcion;

/**
 * Created by Juan on 18/01/2016.
 */
public class FuncionDAOImpl implements FuncionDAO {


    SalaDAO daoSala = new SalaDAOImpl();
    PeliculaDAO daoPelicula = new PeliculaDAOImpl();
    TipoPeliculaDAO daoTipoPelicula = new TipoPeliculaDAOImpl();

    @Override
    public void crearFuncion(Funcion funcion) {
        ParseObject f;

        ParseQuery<ParseObject> querySala;
        ParseQuery<ParseObject> queryCine;
        ParseQuery<ParseObject> queryPelicula;
        ParseQuery<ParseObject> queryTipoPelicula;

        try {
            f = new ParseObject("Funcion");

            f.put("codigo", funcion.getId());
            f.put("fecha", funcion.getFecha());
            f.put("horaInicio", funcion.getHoraFin());
            f.put("horaFin", funcion.getHoraFin());

            querySala = ParseQuery.getQuery("Sala");
            querySala.whereEqualTo("numero", funcion.getSala().getNumero());

            queryCine = ParseQuery.getQuery("Cine");
            queryCine.whereEqualTo("codigo", funcion.getSala().getCine().getCodigo());

            querySala.whereEqualTo("cine", queryCine.find().get(0));

            f.put("sala", querySala.find().get(0));

            queryPelicula = ParseQuery.getQuery("Pelicula");
            queryPelicula.whereEqualTo("codigo", funcion.getPelicula().getId());

            f.put("pelicula", queryPelicula.find().get(0));

            queryTipoPelicula = ParseQuery.getQuery("TipoPelicula");
            queryTipoPelicula.whereEqualTo("codigo", funcion.getTipoPelicula().getId());

            f.put("tipoPelicula", queryTipoPelicula.find().get(0));

            f.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Funcion leerFuncion(String id) {
        ParseQuery<ParseObject> queryFuncion = null;
        List<ParseObject> resultado;
        Funcion funcion;

        try {
            queryFuncion = ParseQuery.getQuery("Funcion");
            queryFuncion.whereEqualTo("codigo", id);
            resultado = queryFuncion.find();

            if (resultado.size() != 0) {
                funcion = new Funcion();
                funcion.setId(resultado.get(0).getString("codigo"));
                funcion.setFecha(resultado.get(0).getDate("fecha"));
                funcion.setHoraInicio(resultado.get(0).getDate("horaInicio"));
                funcion.setHoraFin(resultado.get(0).getDate("horaFin"));

                funcion.setSala(daoSala.leerSala(resultado.get(0).getParseObject("sala")
                        .fetchIfNeeded().getInt("numero"), resultado.get(0).getParseObject("sala")
                        .fetchIfNeeded().getParseObject("cine").fetchIfNeeded().getInt("codigo")))
                ;

                funcion.setPelicula(daoPelicula.leerPelicula(resultado.get(0)
                        .getParseObject("pelicula").fetchIfNeeded().getString("codigo")));

                funcion.setTipoPelicula(daoTipoPelicula.leerTipoPelicula(resultado.get(0)
                        .getParseObject("tipoPelicula").fetchIfNeeded().getString("codigo")));

                return funcion;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Funcion> leerListaFunciones() {
        ParseQuery<ParseObject> queryFuncion = null;
        List<ParseObject> resultado;
        Funcion funcion;
        List<Funcion> listaFunciones;

        try {
            queryFuncion = ParseQuery.getQuery("Funcion");
            resultado = queryFuncion.find();

            listaFunciones = new ArrayList<>();

            if (resultado != null) {
                for (int i = 0; i < resultado.size(); i++) {
                    funcion = new Funcion();
                    funcion.setId(resultado.get(i).getString("codigo"));
                    funcion.setFecha(resultado.get(i).getDate("fecha"));
                    funcion.setHoraInicio(resultado.get(i).getDate("horaInicio"));
                    funcion.setHoraFin(resultado.get(i).getDate("horaFin"));

                    funcion.setSala(daoSala.leerSala(resultado.get(i).getParseObject("sala")
                            .fetchIfNeeded().getInt("numero"), resultado.get(i).getParseObject("sala")
                            .fetchIfNeeded().getParseObject("cine").fetchIfNeeded().getInt("codigo")))
                    ;

                    funcion.setPelicula(daoPelicula.leerPelicula(resultado.get(i)
                            .getParseObject("pelicula").fetchIfNeeded().getString("codigo")));

                    funcion.setTipoPelicula(daoTipoPelicula.leerTipoPelicula(resultado.get(i)
                            .getParseObject("tipoPelicula").fetchIfNeeded().getString("codigo")));

                    listaFunciones.add(funcion);
                }
                return listaFunciones;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
