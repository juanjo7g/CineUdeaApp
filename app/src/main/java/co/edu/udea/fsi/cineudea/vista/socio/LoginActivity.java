package co.edu.udea.fsi.cineudea.vista.socio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;

import co.edu.udea.fsi.cineudea.R;
import co.edu.udea.fsi.cineudea.dao.SocioDAO;
import co.edu.udea.fsi.cineudea.dao.impl.SocioDAOImpl;
import co.edu.udea.fsi.cineudea.dto.Socio;

public class LoginActivity extends AppCompatActivity {

    EditText etUsuario;
    EditText etContraseña;
    Button btnIniciar;

    SocioDAO daoSocio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etContraseña = (EditText) findViewById(R.id.etContraseña);
        btnIniciar = (Button) findViewById(R.id.btnIniciar);

        daoSocio = new SocioDAOImpl();

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Socio s = daoSocio.leerSocio(etUsuario.getText().toString());
                if(s == null){
                    Toast.makeText(LoginActivity.this, "Error en el logueo", Toast.LENGTH_SHORT).show();
                }else{
                    if(!s.getContraseña().equals(etContraseña.getText().toString())){
                        Toast.makeText(LoginActivity.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    }else{
                        ParseObject sl = new ParseObject("Socio");
                        sl.put("usuario", etUsuario.getText().toString());
                        try {
                            sl.pin();
                            Toast.makeText(LoginActivity.this, "Logueado correctamente", Toast.LENGTH_SHORT).show();
                            finish();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }
}
