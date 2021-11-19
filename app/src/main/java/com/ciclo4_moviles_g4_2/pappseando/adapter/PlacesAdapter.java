package com.ciclo4_moviles_g4_2.pappseando.adapter;

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

    private List<Places> listaLugares;
    private Context context;
    private LayoutInflater inflater;

    public PlacesAdapter(List<Places> listaLugares, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.listaLugares = listaLugares;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.place_card_view, null);
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
        private TextView txtNombre;
        private TextView txtDescripcion;
        private ImageView imgFoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.tv_place_name);
            txtDescripcion = itemView.findViewById(R.id.tv_place_desc);
            imgFoto = itemView.findViewById(R.id.iv_place_image);
        }

        void bindData(Places item) {
            txtNombre.setText(item.getNombreLugar());
            txtDescripcion.setText(item.getDescripcionLugar());
            imgFoto.setImageURI(item.getFotoLugar());
        }
    }
}
