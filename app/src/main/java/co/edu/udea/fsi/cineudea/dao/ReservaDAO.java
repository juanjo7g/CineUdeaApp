package co.edu.udea.fsi.cineudea.dao;

import co.edu.udea.fsi.cineudea.dto.Reserva;

/**
 * Created by Juan on 17/01/2016.
 */
public interface ReservaDAO {
    void crearReserva(Reserva reserva);

    Reserva leerReserva(String id);
}
