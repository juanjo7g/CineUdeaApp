package co.edu.udea.fsi.cineudea.dto;

/**
 * Created by Juan on 17/01/2016.
 */
public class Boleta {
    private String id;
    private String codigoPIN;
    private Silla silla;
    private Reserva reserva;
    private TipoCompra tipoCompra;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigoPIN() {
        return codigoPIN;
    }

    public void setCodigoPIN(String codigoPIN) {
        this.codigoPIN = codigoPIN;
    }

    public Silla getSilla() {
        return silla;
    }

    public void setSilla(Silla silla) {
        this.silla = silla;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public TipoCompra getTipoCompra() {
        return tipoCompra;
    }

    public void setTipoCompra(TipoCompra tipoCompra) {
        this.tipoCompra = tipoCompra;
    }
}
