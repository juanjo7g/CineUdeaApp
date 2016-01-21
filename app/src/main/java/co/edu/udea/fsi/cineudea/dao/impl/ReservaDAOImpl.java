package co.edu.udea.fsi.cineudea.dao.impl;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import co.edu.udea.fsi.cineudea.dao.FuncionDAO;
import co.edu.udea.fsi.cineudea.dao.ReservaDAO;
import co.edu.udea.fsi.cineudea.dao.SocioDAO;
import co.edu.udea.fsi.cineudea.dto.Funcion;
import co.edu.udea.fsi.cineudea.dto.Reserva;

/**
 * Created by Juan on 19/01/2016.
 */
public class ReservaDAOImpl implements ReservaDAO {
    FuncionDAO daoFuncion = new FuncionDAOImpl();
    SocioDAO daoSocio = new SocioDAOImpl();

    @Override
    public void crearReserva(Reserva reserva) {
        ParseObject r;

        ParseQuery<ParseObject> queryFuncion;
        ParseQuery<ParseObject> querySocio;

        try {
            r = new ParseObject("Reserva");

            r.put("codigo", reserva.getId());
            r.put("fecha", reserva.getFecha());
            r.put("estadoPago", reserva.getEstadoPago());

            queryFuncion = ParseQuery.getQuery("Funcion");
            queryFuncion.whereEqualTo("codigo", reserva.getFuncion().getId());

            r.put("funcion", queryFuncion.find().get(0));

            querySocio = ParseQuery.getQuery("Socio");
            querySocio.whereEqualTo("usuario", reserva.getSocio().getUsuario());

            r.put("socio", querySocio.find().get(0));

            r.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Reserva leerReserva(String id) {

        ParseQuery<ParseObject> queryReserva = null;
        List<ParseObject> resultado;
        Reserva reserva;

        try {
            queryReserva = ParseQuery.getQuery("Reserva");
            queryReserva.whereEqualTo("codigo", id);
            resultado = queryReserva.find();

            if (resultado != null) {
                reserva = new Reserva();
                reserva.setId(resultado.get(0).getString("codigo"));
                reserva.setFecha(resultado.get(0).getDate("fecha"));
                reserva.setEstadoPago(resultado.get(0).getString("estadoPago"));

                reserva.setFuncion(daoFuncion.leerFuncion(resultado.get(0).getParseObject("funcion").getString("codigo")));

                reserva.setSocio(daoSocio.leerSocio(resultado.get(0).getParseObject("socio").getString("usuario")));

                return reserva;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
