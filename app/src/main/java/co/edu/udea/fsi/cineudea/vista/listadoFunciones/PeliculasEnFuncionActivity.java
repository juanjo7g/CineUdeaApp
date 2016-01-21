package co.edu.udea.fsi.cineudea.vista.listadoFunciones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.edu.udea.fsi.cineudea.R;
import co.edu.udea.fsi.cineudea.dao.FuncionDAO;
import co.edu.udea.fsi.cineudea.dao.impl.FuncionDAOImpl;
import co.edu.udea.fsi.cineudea.dto.Funcion;
import co.edu.udea.fsi.cineudea.dto.Pelicula;
import co.edu.udea.fsi.cineudea.vista.pelicula.PeliculaActivity;

public class PeliculasEnFuncionActivity extends AppCompatActivity {

    int codigoCine;

    private List<Funcion> listaFunciones;
    private List<Pelicula> listaPeliculasEnFuncion;

    private ListView lvPeliculasEnFuncion;
    private PeliculasEnFuncionCustomAdapter customAdapter;

    private FuncionDAO daoFuncion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funciones);

        lvPeliculasEnFuncion = (ListView) findViewById(R.id.lvPeliculasEnFuncion);

        daoFuncion = new FuncionDAOImpl();
        listaFunciones = daoFuncion.leerListaFunciones();
        listaPeliculasEnFuncion = new ArrayList<>();

//        Toast.makeText(PeliculasEnFuncionActivity.this, "hola: " + this.getIntent().getExtras().getInt("codigoCine"), Toast.LENGTH_SHORT).show();
        Boolean estaPelicula = false;

        codigoCine = this.getIntent().getExtras().getInt("codigoCine");
        for (int i = 0; i < listaFunciones.size(); i++) {
            if (listaFunciones.get(i).getSala().getCine().getCodigo() == codigoCine) {
                for (int j = 0; j < listaPeliculasEnFuncion.size(); j++) {
                    if (listaPeliculasEnFuncion.get(j).getNombre().equals(listaFunciones.get(i).getPelicula().getNombre())) {
                        estaPelicula = true;
                    }
                }
                if (!estaPelicula) {
                    listaPeliculasEnFuncion.add(listaFunciones.get(i).getPelicula());
                }
                estaPelicula = false;
            }
        }

        customAdapter = new PeliculasEnFuncionCustomAdapter(this, listaPeliculasEnFuncion);
        lvPeliculasEnFuncion.setAdapter(customAdapter);


        lvPeliculasEnFuncion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pelicula p = customAdapter.getItem(position);
                Intent i = new Intent(PeliculasEnFuncionActivity.this, PeliculaActivity.class);
                i.putExtra("codigoCine", codigoCine);
                i.putExtra("idPelicula", p.getId());
                startActivity(i);
            }
        });
    }
}
