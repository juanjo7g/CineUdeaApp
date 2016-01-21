package co.edu.udea.fsi.cineudea.vista.reserva;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import co.edu.udea.fsi.cineudea.R;
import co.edu.udea.fsi.cineudea.dao.BoletaDAO;
import co.edu.udea.fsi.cineudea.dao.FuncionDAO;
import co.edu.udea.fsi.cineudea.dao.LocalidadDAO;
import co.edu.udea.fsi.cineudea.dao.ReservaDAO;
import co.edu.udea.fsi.cineudea.dao.SocioDAO;
import co.edu.udea.fsi.cineudea.dao.TipoCompraDAO;
import co.edu.udea.fsi.cineudea.dao.impl.BoletaDAOImpl;
import co.edu.udea.fsi.cineudea.dao.impl.FuncionDAOImpl;
import co.edu.udea.fsi.cineudea.dao.impl.LocalidadDAOImpl;
import co.edu.udea.fsi.cineudea.dao.impl.ReservaDAOImpl;
import co.edu.udea.fsi.cineudea.dao.impl.SocioDAOImpl;
import co.edu.udea.fsi.cineudea.dao.impl.TipoCompraDAOImpl;
import co.edu.udea.fsi.cineudea.dto.Boleta;
import co.edu.udea.fsi.cineudea.dto.Funcion;
import co.edu.udea.fsi.cineudea.dto.Localidad;
import co.edu.udea.fsi.cineudea.dto.Reserva;
import co.edu.udea.fsi.cineudea.dto.Socio;
import co.edu.udea.fsi.cineudea.dto.TipoCompra;
import co.edu.udea.fsi.cineudea.vista.listadoFunciones.PeliculasEnFuncionActivity;

public class ReservaActivity extends AppCompatActivity {

    TextView tvSala;
    Spinner sLocalidad;
    Button bPagar;
    Button bReservarSinPagar;
    LocalidadDAO daoLocalidad;
    TextView tvAPagar;

    Funcion funcion;
    Socio socio;

    ReservaDAO daoReserva;
    FuncionDAO daoFuncion;
    SocioDAO daoSocio;

    BoletaDAO daoBoleta;

    Reserva reserva;
    Boleta boleta;

    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.US);

    RadioButton rb1;
    RadioButton rb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        tvSala = (TextView) findViewById(R.id.tvSala);
        sLocalidad = (Spinner) findViewById(R.id.spinnerLocalidad);
        bPagar = (Button) findViewById(R.id.buttonPago);
        bReservarSinPagar = (Button) findViewById(R.id.buttonReservar);
        tvAPagar = (TextView) findViewById(R.id.tvAPagar);

        daoLocalidad = new LocalidadDAOImpl();
        List<Localidad> listaLocalidades = daoLocalidad.leerListaLocalidades();

        ArrayList<String> nombresLocalidades = new ArrayList<String>();

        for (int i = 0; i < listaLocalidades.size(); i++) {
            nombresLocalidades.add(listaLocalidades.get(i).getNombre());
        }
        ArrayAdapter adapterLocalidades = new ArrayAdapter(this, android.R.layout.simple_spinner_item, nombresLocalidades);
        adapterLocalidades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sLocalidad.setAdapter(adapterLocalidades);

        daoReserva = new ReservaDAOImpl();

        daoFuncion = new FuncionDAOImpl();
        daoSocio = new SocioDAOImpl();

        daoBoleta = new BoletaDAOImpl();

        funcion = daoFuncion.leerFuncion(this.getIntent().getExtras().getString("codigoFuncion"));
        socio = daoSocio.leerSocio(this.getIntent().getExtras().getString("usuarioSocio"));

        tvSala.setText("Se presentará en la Sala " + funcion.getSala().getNumero());

        reserva = new Reserva();
        reserva.setId(UUID.randomUUID().toString());
        reserva.setFecha(new Date());
        reserva.setEstadoPago("Pendiente");
        reserva.setFuncion(funcion);
        reserva.setSocio(socio);

        boleta = new Boleta();
        boleta.setId(UUID.randomUUID().toString());
        boleta.setCodigoPIN(UUID.randomUUID().toString());
        boleta.setReserva(reserva);

        bReservarSinPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daoReserva.crearReserva(reserva);
                daoBoleta.crearBoleta(boleta);

                new AlertDialog.Builder(ReservaActivity.this)
                        .setTitle("Reserva guardada")
                        .setMessage("Se almaceno su reserva con éxito. \nPelícula: " +
                                funcion.getPelicula().getNombre()
                                + "\nFecha y Hora: " + formatoFecha.format(funcion.getFecha())
                                + "\nLugar: Sala " + funcion.getSala().getNumero() +
                                " Cine Udea " + funcion.getSala().getCine().getNombre()
                                + " " + funcion.getSala().getCine().getDireccion()
                                + "\n\nSe le asignó el siguiente codigo PIN: " + boleta.getCodigoPIN()
                                + "\n\n ** Recuerde pagar a tiempo para no perder su reserva.")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                Intent i = new Intent(ReservaActivity.this, PeliculasEnFuncionActivity.class);
                                i.putExtra("codigoCine", funcion.getSala().getCine().getCodigo());
                                startActivity(i);
                            }
                        })
                        .setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                finish();
                                Intent i = new Intent(ReservaActivity.this, PeliculasEnFuncionActivity.class);
                                i.putExtra("codigoCine", funcion.getSala().getCine().getCodigo());
                                startActivity(i);
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
            }
        });

        TipoCompraDAO daoTipoCompra = new TipoCompraDAOImpl();
        List<TipoCompra> listaTipoCompra = daoTipoCompra.leerListaTipoCompras();

        TipoCompra tipoCompra = new TipoCompra();
        for (int i = 0; i < listaTipoCompra.size(); i++) {
            if (listaTipoCompra.get(i).getTipoPelicula().getId().equals(funcion.getTipoPelicula().getId())) {
                tipoCompra = listaTipoCompra.get(i);
            }
        }
        tvAPagar.setText("Total a pagar: \n\n$ " + tipoCompra.getValorEnDinero() + " o " + tipoCompra.getValorEnPuntos() + " puntos.");

        rb1 = (RadioButton) findViewById(R.id.radioButtonDinero);
        rb2 = (RadioButton) findViewById(R.id.radioButtonPuntos);

        final TipoCompra finalTipoCompra = tipoCompra;
        bPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rb1.isChecked() && !rb2.isChecked()) {
                    Toast.makeText(ReservaActivity.this, "Seleccione un tipo de compra.", Toast.LENGTH_SHORT).show();
                } else {
                    if (rb2.isChecked() && socio.getPuntosAcumulados() < finalTipoCompra.getValorEnPuntos()) {

                        new AlertDialog.Builder(ReservaActivity.this)
                                .setTitle("Error en pago")
                                .setMessage("No tiene suficientes puntos para realizar esta compra.")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                    @Override
                                    public void onCancel(DialogInterface dialog) {

                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();

                    } else {
                        if (rb2.isChecked()) {
                            ParseQuery<ParseObject> querySocio = ParseQuery.getQuery("Socio");
                            querySocio.whereEqualTo("usuario", socio.getUsuario());
                            ParseObject socio = null;
                            try {
                                socio = querySocio.find().get(0);
                                socio.put("puntosAcumulados", socio.getInt("puntosAcumulados") - finalTipoCompra.getValorEnPuntos());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                        }
                        reserva.setEstadoPago("Realizado");
                        boleta.setTipoCompra(finalTipoCompra);

                        daoReserva.crearReserva(reserva);
                        daoBoleta.crearBoleta(boleta);

                        new AlertDialog.Builder(ReservaActivity.this)
                                .setTitle("Pago realizado con éxito")
                                .setMessage("Se almaceno su reserva y pago con éxito. \nPelícula: " +
                                                funcion.getPelicula().getNombre()
                                                + "\nFecha y Hora: " + formatoFecha.format(funcion.getFecha())
                                                + "\nLugar: Sala " + funcion.getSala().getNumero() +
                                                " Cine Udea " + funcion.getSala().getCine().getNombre()
                                                + " " + funcion.getSala().getCine().getDireccion()
                                                + "\n\nSe le asignó el siguiente codigo PIN: " + boleta.getCodigoPIN()
                                )
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                        Intent i = new Intent(ReservaActivity.this, PeliculasEnFuncionActivity.class);
                                        i.putExtra("codigoCine", funcion.getSala().getCine().getCodigo());
                                        startActivity(i);
                                    }
                                })
                                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                    @Override
                                    public void onCancel(DialogInterface dialog) {
                                        finish();
                                        Intent i = new Intent(ReservaActivity.this, PeliculasEnFuncionActivity.class);
                                        i.putExtra("codigoCine", funcion.getSala().getCine().getCodigo());
                                        startActivity(i);
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_info)
                                .show();
                    }
                }
            }
        });
    }


}
