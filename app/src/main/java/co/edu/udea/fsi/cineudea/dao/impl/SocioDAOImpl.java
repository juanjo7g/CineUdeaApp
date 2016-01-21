package co.edu.udea.fsi.cineudea.dao.impl;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import co.edu.udea.fsi.cineudea.dao.SocioDAO;
import co.edu.udea.fsi.cineudea.dto.Localidad;
import co.edu.udea.fsi.cineudea.dto.Socio;

/**
 * Created by Juan on 18/01/2016.
 */
public class SocioDAOImpl implements SocioDAO {
    @Override
    public void crearSocio(Socio socio) {
        ParseObject s;

        try {
            s = new ParseObject("Socio");

            s.put("usuario", socio.getUsuario());
            s.put("contrasena", socio.getContraseña());
            s.put("nombreCompleto", socio.getNombreCompleto());
            s.put("correoElectronico", socio.getCorreoElectronico());
            s.put("puntosAcumulados", socio.getPuntosAcumulados());

            s.save();

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Socio leerSocio(String usuario) {
        ParseQuery<ParseObject> querySocio = null;
        List<ParseObject> resultado;
        Socio socio;

        try {
            querySocio = ParseQuery.getQuery("Socio");
            querySocio.whereEqualTo("usuario", usuario);
            resultado = querySocio.find();

            if (resultado.size() != 0) {
                socio = new Socio();
                socio.setUsuario(resultado.get(0).getString("usuario"));
                socio.setContraseña(resultado.get(0).getString("contrasena"));
                socio.setNombreCompleto(resultado.get(0).getString("nombreCompleto"));
                socio.setCorreoElectronico(resultado.get(0).getString("correoElectronico"));
                socio.setPuntosAcumulados(resultado.get(0).getInt("puntosAcumulados"));

                return socio;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
