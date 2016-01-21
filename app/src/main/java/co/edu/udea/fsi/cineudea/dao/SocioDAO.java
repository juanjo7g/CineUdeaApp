package co.edu.udea.fsi.cineudea.dao;

import co.edu.udea.fsi.cineudea.dto.Socio;

/**
 * Created by Juan on 17/01/2016.
 */
public interface SocioDAO {
    void crearSocio(Socio socio);
    Socio leerSocio(String usuario);
}
