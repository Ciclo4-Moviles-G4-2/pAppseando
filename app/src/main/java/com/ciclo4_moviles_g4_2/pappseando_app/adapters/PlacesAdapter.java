package com.ciclo4_moviles_g4_2.pappseando_app.adapters;

/* Código Java del adaptador usado en el RecyclerView para listar los lugares
   Implementado por: Mauricio Moreno
*/

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ciclo4_moviles_g4_2.pappseando_app.R;
import com.ciclo4_moviles_g4_2.pappseando_app.models.PlaceVO;
import com.ciclo4_moviles_g4_2.pappseando_app.views.FormPlaceActivity;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder> implements View.OnClickListener {

    private final List<PlaceVO> listaLugares;
    private final int resource;
    private View.OnClickListener listener;

    public PlacesAdapter(List<PlaceVO> listaLugares, int resource) {
        this.resource = resource;
        this.listaLugares = listaLugares;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        view.setOnClickListener(this);
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

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    public void addPlace(PlaceVO place, int position) {
        listaLugares.add(position, place);
        this.notifyItemInserted(position);
    }

    public void removePlace(int position) {
        listaLugares.remove(position);
        this.notifyItemRemoved(position);
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

        void bindData(PlaceVO item) {
            txtNombre.setText(item.getNombre());
            txtDescripcion.setText(item.getDescripcion());
            StorageReference photoRef = item.obtainImgRef();
            if (item.getUrl() != null)
                Glide.with(itemView.getContext())
                        .load(item.getUrl())
                        .fitCenter()
                        .centerCrop()
                        .circleCrop()
                        .into(imgFoto);
            else
                Glide.with(itemView.getContext())
                        .load(R.drawable.place)
                        .fitCenter()
                        .centerCrop()
                        .circleCrop()
                        .into(imgFoto);
        }
    }
}
