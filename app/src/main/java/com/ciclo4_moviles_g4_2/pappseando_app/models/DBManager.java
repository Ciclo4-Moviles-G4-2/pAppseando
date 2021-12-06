package com.ciclo4_moviles_g4_2.pappseando_app.models;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DBManager {
    public static final FirebaseDatabase db = FirebaseDatabase.getInstance();
    public static final DatabaseReference myRef = db.getReference("dbtest");

    public static void putPlaceOnDB(String nombre, String descripcion, int foto) {
        PlaceVO lugar = new PlaceVO(nombre, descripcion, foto);
        myRef.push().setValue(lugar);
    }

    public static void deletePlaceFromDB(String placeId) {
        myRef.child(placeId).setValue(null);
    }

    public static void deleteAllPlacesFromDB() {
        myRef.setValue(null);
    }

    public static void updatePlaceFromDB(String placeId, String nombre, String descripcion, int foto) {
        PlaceVO lugar = new PlaceVO(nombre, descripcion, foto);
        DatabaseReference idRef = myRef.child(placeId);
        idRef.setValue(null);
        idRef.setValue(lugar);
    }

    public static PlaceVO getPlaceFromDB(String placeId) {
        final PlaceVO[] lugar = new PlaceVO[1];

        myRef.child(placeId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                    lugar[0] = snapshot.getValue(PlaceVO.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        return lugar[0];
    }
}
