package co.edu.udea.fsi.cineudea.vista.listadoCines;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import co.edu.udea.fsi.cineudea.R;
import co.edu.udea.fsi.cineudea.dao.CineDAO;
import co.edu.udea.fsi.cineudea.dao.impl.CineDAOImpl;
import co.edu.udea.fsi.cineudea.dto.Cine;
import co.edu.udea.fsi.cineudea.vista.listadoFunciones.PeliculasEnFuncionActivity;

public class CinesActivity extends AppCompatActivity {

    private List<Cine> listaCines;
    private ListView lvCines;
    private CineCustomAdapter customAdapter;

    private CineDAO daoCine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cines);

        lvCines = (ListView) findViewById(R.id.lvCines);

        daoCine = new CineDAOImpl();
        listaCines = daoCine.leerListaCines();

        customAdapter = new CineCustomAdapter(this, listaCines);
        lvCines.setAdapter(customAdapter);


        lvCines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cine c = customAdapter.getItem(position);
                Intent i = new Intent(CinesActivity.this, PeliculasEnFuncionActivity.class);
                i.putExtra("codigoCine", c.getCodigo());
                startActivity(i);
            }
        });

    }
}
