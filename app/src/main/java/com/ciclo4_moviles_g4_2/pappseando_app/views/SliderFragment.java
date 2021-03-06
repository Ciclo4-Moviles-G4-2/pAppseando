package com.ciclo4_moviles_g4_2.pappseando_app.views;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.ciclo4_moviles_g4_2.pappseando_app.R;

public class SliderFragment extends Fragment {
    View viewAlejo;
    ImageView imageAlejo;
    TextView titleAlejo,contentAlejo;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewAlejo= inflater.inflate(R.layout.fragment_slider,container, false);
        imageAlejo = viewAlejo.findViewById(R.id.image);
        titleAlejo = viewAlejo.findViewById(R.id.textTitle);
        contentAlejo = viewAlejo.findViewById(R.id.textContent);
        ConstraintLayout background=viewAlejo.findViewById(R.id.Background);
        if(getArguments()!=null){
            titleAlejo.setText(getArguments().getString("title"));
            contentAlejo.setText(getArguments().getString("content"));
            imageAlejo.setImageResource(getArguments().getInt("image"));
            background.setBackgroundColor(getArguments().getInt("color"));
        }

        return viewAlejo;
    }
}