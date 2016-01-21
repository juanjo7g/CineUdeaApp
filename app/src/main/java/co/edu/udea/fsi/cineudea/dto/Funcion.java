package co.edu.udea.fsi.cineudea.dto;

import java.util.Date;

/**
 * Created by Juan on 17/01/2016.
 */
public class Funcion {
    private String id;
    private Date fecha;
    private Date horaInicio;
    private Date horaFin;
    private Sala sala;
    private Pelicula pelicula;
    private TipoPelicula tipoPelicula;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public TipoPelicula getTipoPelicula() {
        return tipoPelicula;
    }

    public void setTipoPelicula(TipoPelicula tipoPelicula) {
        this.tipoPelicula = tipoPelicula;
    }
}
