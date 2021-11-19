package com.ciclo4_moviles_g4_2.pappseando.list_adapter;

/* Código Java del adaptador usado en el RecyclerView para listar los lugares
   Implementado por: Mauricio Moreno
*/

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ciclo4_moviles_g4_2.pappseando.R;

import java.util.List;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder> {

    private final List<Place> listaLugares;
    private final LayoutInflater inflater;

    public PlacesAdapter(List<Place> listaLugares, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.listaLugares = listaLugares;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.place_card_view, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(listaLugares.get(position));
    }

    @Override
    public int getItemCount() {
        return listaLugares.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtNombre;
        private final TextView txtDescripcion;
        private final ImageView imgFoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.tv_place_name);
            txtDescripcion = itemView.findViewById(R.id.tv_place_desc);
            imgFoto = itemView.findViewById(R.id.iv_place_image);
        }

        void bindData(Place item) {
            txtNombre.setText(item.getNombre());
            txtDescripcion.setText(item.getDescripcion());
            imgFoto.setImageResource(item.getFoto());
        }
    }
}
