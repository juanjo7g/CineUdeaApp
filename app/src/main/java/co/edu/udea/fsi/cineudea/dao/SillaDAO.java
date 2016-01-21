package co.edu.udea.fsi.cineudea.dao;

import java.util.List;

import co.edu.udea.fsi.cineudea.dto.Silla;

/**
 * Created by Juan on 17/01/2016.
 */
public interface SillaDAO {
    Silla leerSilla(int numero, int numeroSala, int codigoCineSala);

    List<Silla> leerListaSillas();
}
