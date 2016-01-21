package co.edu.udea.fsi.cineudea.dto;

/**
 * Created by Juan on 17/01/2016.
 */
public class TipoCompra {
    private String id;
    private float valorEnDinero;
    private int valorEnPuntos;
    private TipoPelicula tipoPelicula;
    private Localidad localidad;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getValorEnDinero() {
        return valorEnDinero;
    }

    public void setValorEnDinero(float valorEnDinero) {
        this.valorEnDinero = valorEnDinero;
    }

    public int getValorEnPuntos() {
        return valorEnPuntos;
    }

    public void setValorEnPuntos(int valorEnPuntos) {
        this.valorEnPuntos = valorEnPuntos;
    }

    public TipoPelicula getTipoPelicula() {
        return tipoPelicula;
    }

    public void setTipoPelicula(TipoPelicula tipoPelicula) {
        this.tipoPelicula = tipoPelicula;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }
}
