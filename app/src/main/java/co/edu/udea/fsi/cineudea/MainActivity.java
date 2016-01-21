package co.edu.udea.fsi.cineudea;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import co.edu.udea.fsi.cineudea.dao.CineDAO;
import co.edu.udea.fsi.cineudea.dao.FuncionDAO;
import co.edu.udea.fsi.cineudea.dao.PeliculaDAO;
import co.edu.udea.fsi.cineudea.dao.ReservaDAO;
import co.edu.udea.fsi.cineudea.dao.SalaDAO;
import co.edu.udea.fsi.cineudea.dao.SillaDAO;
import co.edu.udea.fsi.cineudea.dao.SocioDAO;
import co.edu.udea.fsi.cineudea.dao.impl.CineDAOImpl;
import co.edu.udea.fsi.cineudea.dao.impl.FuncionDAOImpl;
import co.edu.udea.fsi.cineudea.dao.impl.PeliculaDAOImpl;
import co.edu.udea.fsi.cineudea.dao.impl.ReservaDAOImpl;
import co.edu.udea.fsi.cineudea.dao.impl.SalaDAOImpl;
import co.edu.udea.fsi.cineudea.dao.impl.SillaDAOImpl;
import co.edu.udea.fsi.cineudea.dao.impl.SocioDAOImpl;
import co.edu.udea.fsi.cineudea.dto.Sala;
import co.edu.udea.fsi.cineudea.vista.listadoCines.CinesActivity;

public class MainActivity extends AppCompatActivity {

    Button button;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = new Intent(this, CinesActivity.class);
        startActivity(i);
        finish();

        button = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CineDAO daoCine = new CineDAOImpl();
                PeliculaDAO daoPelicula = new PeliculaDAOImpl();
                SocioDAO daoSocio = new SocioDAOImpl();
                SalaDAO daoSala = new SalaDAOImpl();
                SillaDAO daoSilla = new SillaDAOImpl();
                FuncionDAO daoFuncion = new FuncionDAOImpl();
                ReservaDAO daoReserva = new ReservaDAOImpl();

//                byte[] image = daoPelicula.leerPelicula("2").getPortada();
//                imageView.setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));
//                Socio socio = new Socio();
//                socio.setUsuario("yo");
//                socio.setContraseña("yo");
//                socio.setCorreoElectronico("yo@gmail.com");
//                socio.setNombreCompleto("Yo Yo Ma");
//                socio.setPuntosAcumulados(123);
//
//                daoSocio.crearSocio(socio);
//                Sala s = daoSala.leerSala(1, 1);
//                String resultado = "Sala: " + s.getNumero() + " En " + s.getCine().getNombre() + " " + s.getCine().getDireccion();
//                Silla s = daoSilla.leerSilla(1, 1, 1);
//                Silla s;
//                daoSilla = new SillaDAOImpl();
//                List<Silla> listaaaaa = daoSilla.leerListaSillas();
////                String resultado = "Silla " + s.getNumero() + " fila: " + s.getFila() + " columna: "
////                        + s.getColumna() + " cine: " + s.getSala().getCine().getDireccion();
//                for (int i = 0; i < listaaaaa.size(); i++) {
//                    s = new Silla();
//                    s = listaaaaa.get(i);
//                    Toast.makeText(MainActivity.this, "Silla " + s.getNumero() + " fila: " + s.getFila() + " columna: "
//                            + s.getColumna() + " cine: " + s.getSala().getCine().getDireccion(), Toast.LENGTH_SHORT).show();
//                }
//                Toast.makeText(MainActivity.this, resultado, Toast.LENGTH_SHORT).show();
//                Funcion funcion = new Funcion();
//                funcion.setId("1");
//                funcion.setFecha(new Date());
//                funcion.setHoraInicio(new Date());
//                funcion.setHoraFin(new Date());
//                Sala s = new Sala();
//                s.setNumero(1);
//                Cine c = new Cine();
//                c.setCodigo(1);
//                s.setCine(c);
//                funcion.setSala(s);
//                Pelicula p = new Pelicula();
//                p.setId("2");
//                funcion.setPelicula(p);
//                TipoPelicula tp = new TipoPelicula();
//                tp.setId("1");
//                funcion.setTipoPelicula(tp);

//                daoFuncion.crearFuncion(funcion);

//                Reserva reserva = new Reserva();
//                reserva.setId("1");
//                reserva.setFecha(new Date());
//                reserva.setEstadoPago("Pendiente");
//                Funcion f = new Funcion();
//                f.setId("1");
//                reserva.setFuncion(f);
//                Socio s = new Socio();
//                s.setUsuario("yo");
//                reserva.setSocio(s);
//
//                daoReserva.crearReserva(reserva);

//                List<Cine> lista = daoCine.leerListaCines();
//
//                for (int i = 0; i < lista.size(); i++) {
//                    Toast.makeText(MainActivity.this, "Cine -> id: " + lista.get(i).getCodigo() +
//                            " nombre: " + lista.get(i).getNombre()+" direccion: "+lista.get(i).getDireccion()
//                            +" ciudad"+lista.get(i).getCiudad(), Toast.LENGTH_SHORT).show();
//                }
                daoSala = new SalaDAOImpl();
                List<Sala> lista = daoSala.leerListaSalas();
                for (int i = 0; i < lista.size(); i++) {
                    Toast.makeText(MainActivity.this, "Holas: " + lista.get(i).getNumero() + " cine: " +
                            lista.get(i).getCine().getNombre(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
