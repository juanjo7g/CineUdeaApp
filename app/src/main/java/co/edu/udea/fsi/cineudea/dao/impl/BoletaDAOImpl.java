package co.edu.udea.fsi.cineudea.dao.impl;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import co.edu.udea.fsi.cineudea.dao.BoletaDAO;
import co.edu.udea.fsi.cineudea.dao.SillaDAO;
import co.edu.udea.fsi.cineudea.dto.Boleta;

/**
 * Created by Juan on 17/01/2016.
 */
//TODO: BoletaDAOImpl
public class BoletaDAOImpl implements BoletaDAO {

    @Override
    public void crearBoleta(Boleta boleta) {
        ParseObject b;

        ParseQuery<ParseObject> querySilla;
        ParseQuery<ParseObject> querySala;
        ParseQuery<ParseObject> queryCine;
        ParseQuery<ParseObject> queryReserva;
        ParseQuery<ParseObject> queryTipoCompra;

        try {
            b = new ParseObject("Boleta");

            b.put("codigo", boleta.getId());
            b.put("codigoPIN", boleta.getCodigoPIN());

//            querySilla = ParseQuery.getQuery("Silla");
//            querySilla.whereEqualTo("numero", boleta.getSilla().getNumero());
//
//            querySala = ParseQuery.getQuery("Sala");
//            querySala.whereEqualTo("numero", boleta.getSilla().getSala().getNumero());
//
//            queryCine = ParseQuery.getQuery("Cine");
//            queryCine.whereEqualTo("codigo", boleta.getSilla().getSala().getCine().getCodigo());
//
//            querySala.whereEqualTo("cine", queryCine.find().get(0));
//            querySilla.whereEqualTo("sala", querySala.find().get(0));
//
//            b.put("silla", querySilla.find().get(0));

            queryReserva = ParseQuery.getQuery("Reserva");
            queryReserva.whereEqualTo("codigo", boleta.getReserva().getId());

            b.put("reserva", queryReserva.find().get(0));

            if (boleta.getTipoCompra()!=null) {
                queryTipoCompra = ParseQuery.getQuery("TipoCompra");
                queryTipoCompra.whereEqualTo("codigo", boleta.getTipoCompra().getId());

                b.put("tipoCompra", queryTipoCompra.find().get(0));
            }
            b.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boleta leerBoleta(String id) {
        return null;
    }
}
