package co.edu.udea.fsi.cineudea.dao;

import java.util.List;

import co.edu.udea.fsi.cineudea.dto.Funcion;

/**
 * Created by Juan on 17/01/2016.
 */
public interface FuncionDAO {
    void crearFuncion(Funcion funcion);

    Funcion leerFuncion(String id);

    List<Funcion> leerListaFunciones();
}
