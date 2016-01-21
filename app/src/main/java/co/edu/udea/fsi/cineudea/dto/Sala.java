package co.edu.udea.fsi.cineudea.dto;

/**
 * Created by Juan on 17/01/2016.
 */
public class Sala {
    private int numero;
    private Cine cine;
    private String estado;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Cine getCine() {
        return cine;
    }

    public void setCine(Cine cine) {
        this.cine = cine;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
