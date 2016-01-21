package co.edu.udea.fsi.cineudea.vista.pelicula;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
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

import co.edu.udea.fsi.cineudea.R;
import co.edu.udea.fsi.cineudea.dao.FuncionDAO;
import co.edu.udea.fsi.cineudea.dao.PeliculaDAO;
import co.edu.udea.fsi.cineudea.dao.impl.FuncionDAOImpl;
import co.edu.udea.fsi.cineudea.dao.impl.PeliculaDAOImpl;
import co.edu.udea.fsi.cineudea.dto.Boleta;
import co.edu.udea.fsi.cineudea.dto.Funcion;
import co.edu.udea.fsi.cineudea.dto.Pelicula;
import co.edu.udea.fsi.cineudea.dto.TipoPelicula;
import co.edu.udea.fsi.cineudea.vista.reserva.ReservaActivity;
import co.edu.udea.fsi.cineudea.vista.socio.LoginActivity;

public class PeliculaActivity extends AppCompatActivity {

    Pelicula pelicula;
    PeliculaDAO daoPelicula;

    TextView tvNombre;
    TextView tvDescripcion;
    ImageView ivPortada;
    Spinner sTiposPeli;
    Spinner sFechas;
    Spinner sHoras;
    Button bReservar;

    List<Funcion> listaFunciones;
    FuncionDAO daoFuncion;

    List<TipoPelicula> listaTiposPeliculas;
    List<Funcion> listaFuncionesDeEstaPelicula;
    List<Funcion> listaFuncionesDeEstaPeliculaYFormato;
    List<Date> listaFechas;

    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelicula);

        tvNombre = (TextView) findViewById(R.id.tvNombrePeli);
        tvDescripcion = (TextView) findViewById(R.id.tvDescripcionPeli);
        ivPortada = (ImageView) findViewById(R.id.ivPortadaPeli);
        sTiposPeli = (Spinner) findViewById(R.id.spinnerTipoPeli);
        sFechas = (Spinner) findViewById(R.id.spinnerFechaPeli);
        bReservar = (Button) findViewById(R.id.bReservarPeli);

        daoPelicula = new PeliculaDAOImpl();
        pelicula = daoPelicula.leerPelicula(this.getIntent().getExtras().getString("idPelicula"));

        tvNombre.setText(pelicula.getNombre());
        tvDescripcion.setText(pelicula.getDescripcion());
        if (pelicula.getPortada() != null) {
            ivPortada.setImageBitmap(BitmapFactory.decodeByteArray(pelicula.getPortada(),
                    0, pelicula.getPortada().length));
        }

        daoFuncion = new FuncionDAOImpl();

        listaFunciones = daoFuncion.leerListaFunciones();

        listaTiposPeliculas = new ArrayList<>();

        listaFuncionesDeEstaPelicula = new ArrayList<>();

        boolean esta = false;

        for (int i = 0; i < listaFunciones.size(); i++) {
            if (listaFunciones.get(i).getPelicula().getId().equals(this.getIntent().getExtras().getString("idPelicula"))) {
                listaFuncionesDeEstaPelicula.add(listaFunciones.get(i));
                for (int j = 0; j < listaTiposPeliculas.size(); j++) {
                    if (listaTiposPeliculas.get(j).getId().equals(listaFunciones.get(i).getTipoPelicula().getId())) {
                        esta = true;
                    }
                }
                if (!esta) {
                    listaTiposPeliculas.add(listaFunciones.get(i).getTipoPelicula());
                }
                esta = false;
            }
        }

        ArrayList<String> tiposPeli = new ArrayList<String>();
        for (int i = 0; i < listaTiposPeliculas.size(); i++) {
            tiposPeli.add(listaTiposPeliculas.get(i).getDescripcion());
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, tiposPeli);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sTiposPeli.setAdapter(adapter);

        listaFuncionesDeEstaPeliculaYFormato = new ArrayList<>();

        listaFechas = new ArrayList<>();

        for (int i = 0; i < listaFuncionesDeEstaPelicula.size(); i++) {
            if (listaFuncionesDeEstaPelicula.get(i).getTipoPelicula().getId().equals(listaTiposPeliculas.get(0).getId())) {

                listaFuncionesDeEstaPeliculaYFormato.add(listaFuncionesDeEstaPelicula.get(i));

                for (int j = 0; j < listaFechas.size(); j++) {
                    if (listaFechas.get(j) == listaFuncionesDeEstaPelicula.get(i).getFecha()) {
                        esta = true;
                    }
                }
                if (!esta) {
                    listaFechas.add(listaFuncionesDeEstaPelicula.get(i).getFecha());
                }
                esta = false;
            }
        }

        ArrayList<String> fechas = new ArrayList<String>();


        for (int i = 0; i < listaFechas.size(); i++) {
            fechas.add(formatoFecha.format(listaFechas.get(i)));
        }
        ArrayAdapter adapterFechas = new ArrayAdapter(this, android.R.layout.simple_spinner_item, fechas);
        adapterFechas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sFechas.setAdapter(adapterFechas);

        sTiposPeli.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                actualizarListaFechas(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fechaS = (String) sFechas.getSelectedItem();
                Funcion f = new Funcion();
                if (listaFuncionesDeEstaPeliculaYFormato.size() > 1) {
                    for (int i = 0; i < listaFuncionesDeEstaPeliculaYFormato.size(); i++) {
                        if (formatoFecha.format(listaFuncionesDeEstaPeliculaYFormato.get(i).getFecha()).equals(fechaS)) {
                            f = listaFuncionesDeEstaPeliculaYFormato.get(i);
                        }
                    }
                } else {
                    f = listaFuncionesDeEstaPeliculaYFormato.get(0);
                }

                ParseQuery<ParseObject> querySocio = ParseQuery.getQuery("Socio");
                querySocio.fromLocalDatastore();
                List<ParseObject> resultado = null;
                try {
                    resultado = querySocio.find();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (resultado.size() == 0) {
                    Toast.makeText(PeliculaActivity.this, "¡Debe loguearse!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(PeliculaActivity.this, LoginActivity.class);
                    startActivity(i);

                } else {
                    Toast.makeText(PeliculaActivity.this, "Logueado como: " + resultado.get(0).getString("usuario"), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(PeliculaActivity.this, ReservaActivity.class);
                    i.putExtra("codigoFuncion", f.getId());
                    i.putExtra("usuarioSocio", resultado.get(0).getString("usuario"));
                    startActivity(i);
                }
            }

        });

    }

    private void actualizarListaFechas(int position) {
        listaFechas = new ArrayList<>();
        listaFuncionesDeEstaPeliculaYFormato = new ArrayList<>();
        boolean esta = false;
        for (int i = 0; i < listaFuncionesDeEstaPelicula.size(); i++) {
            if (listaFuncionesDeEstaPelicula.get(i).getTipoPelicula().getId().equals(listaTiposPeliculas.get(position).getId())) {
                listaFuncionesDeEstaPeliculaYFormato.add(listaFuncionesDeEstaPelicula.get(i));
                for (int j = 0; j < listaFechas.size(); j++) {
                    if (listaFechas.get(j) == listaFuncionesDeEstaPelicula.get(i).getFecha()) {
                        esta = true;
                    }
                }
                if (!esta) {
                    listaFechas.add(listaFuncionesDeEstaPelicula.get(i).getFecha());
                }
                esta = false;
            }
        }

        ArrayList<String> fechas = new ArrayList<String>();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.US);

        for (int i = 0; i < listaFechas.size(); i++) {
            fechas.add(formatoFecha.format(listaFechas.get(i)));
        }
        ArrayAdapter adapterFechas = new ArrayAdapter(this, android.R.layout.simple_spinner_item, fechas);
        adapterFechas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sFechas.setAdapter(adapterFechas);


    }
}
