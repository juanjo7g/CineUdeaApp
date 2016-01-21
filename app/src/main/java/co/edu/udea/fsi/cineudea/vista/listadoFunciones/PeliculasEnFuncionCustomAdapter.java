package co.edu.udea.fsi.cineudea.vista.listadoFunciones;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.edu.udea.fsi.cineudea.R;
import co.edu.udea.fsi.cineudea.dto.Funcion;
import co.edu.udea.fsi.cineudea.dto.Pelicula;

/**
 * Created by Juan on 20/09/2015.
 */
public class PeliculasEnFuncionCustomAdapter extends BaseAdapter {

    Context context;
    List<Pelicula> listaPeliculasEnFuncion;

    public PeliculasEnFuncionCustomAdapter(Context context, List<Pelicula> listaPeliculasEnFuncion) {
        this.context = context;
        this.listaPeliculasEnFuncion = listaPeliculasEnFuncion;
    }

    // Cambia dependiendo del layout
    private class ViewHolder {
        ImageView ivPortadaPelicula;
        TextView tvNombrePelicula;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.item_cine_lista, null);

            holder = new ViewHolder();

            // No estamos en un activity
            holder.ivPortadaPelicula = (ImageView) convertView.findViewById(R.id.ivFotoCine);
            holder.tvNombrePelicula = (TextView) convertView
                    .findViewById(R.id.tvNombreCine);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Pelicula p = getItem(position);

        holder.tvNombrePelicula.setText(p.getNombre());

//        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
//        SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm a", Locale.US);
//
//        holder.tvFechaHoraInicioFin.setText(formatoFecha.format(f.getFecha()) +
//                " Hora inicio: " + formatoHora.format(f.getHoraInicio()) +
//                " Hora fin: " + formatoHora.format(f.getHoraFin()));
//
//        holder.tvDescripcionTipoPelicula.setText(f.getTipoPelicula().getDescripcion());

        if (p.getPortada() != null) {
            holder.ivPortadaPelicula.setImageBitmap(BitmapFactory.decodeByteArray(p.getPortada(),
                    0, p.getPortada().length));
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return listaPeliculasEnFuncion.size();
    }

    @Override
    public Pelicula getItem(int arg0) {
        return listaPeliculasEnFuncion.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return listaPeliculasEnFuncion.indexOf(getItem(arg0));
    }

}