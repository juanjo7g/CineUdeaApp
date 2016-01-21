package co.edu.udea.fsi.cineudea.vista.listadoCines;

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
import co.edu.udea.fsi.cineudea.dto.Cine;

/**
 * Created by Juan on 20/09/2015.
 */
public class CineCustomAdapter extends BaseAdapter {

    Context context;
    List<Cine> listaCines;

    public CineCustomAdapter(Context context, List<Cine> listaCines) {
        this.context = context;
        this.listaCines = listaCines;
    }

    // Cambia dependiendo del layout
    private class ViewHolder {
        ImageView ivFoto;
        TextView tvNombre;
        TextView tvCiudad;
        TextView tvDireccion;

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
            holder.ivFoto = (ImageView) convertView.findViewById(R.id.ivFotoCine);
            holder.tvNombre = (TextView) convertView
                    .findViewById(R.id.tvNombreCine);
            holder.tvCiudad = (TextView) convertView.findViewById(R.id.tvCiudadCine);
            holder.tvDireccion = (TextView) convertView
                    .findViewById(R.id.tvDireccionCine);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Cine c = getItem(position);

        holder.tvNombre.setText(c.getNombre());
        holder.tvCiudad.setText(c.getCiudad());
        holder.tvDireccion.setText(c.getDireccion());

        if (c.getFoto() != null) {
            holder.ivFoto.setImageBitmap(BitmapFactory.decodeByteArray(c.getFoto(), 0, c.getFoto().length));
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return listaCines.size();
    }

    @Override
    public Cine getItem(int arg0) {
        return listaCines.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return listaCines.indexOf(getItem(arg0));
    }

}