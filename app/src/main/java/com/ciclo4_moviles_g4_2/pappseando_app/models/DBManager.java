package com.ciclo4_moviles_g4_2.pappseando_app.models;

import android.net.Uri;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DBManager {
    public static final FirebaseDatabase db = FirebaseDatabase.getInstance();
    public static final DatabaseReference myRef = db.getReference("dbtest");

    public static void putPlaceOnDB(String nombre, String descripcion) {
        DatabaseReference idRef = myRef.push();
        PlaceVO lugar = new PlaceVO(nombre, descripcion);
        idRef.setValue(lugar);
    }

    public static void deletePlaceFromDB(String placeId) {
        myRef.child(placeId).setValue(null);
    }

    public static void putUriPlaceOnDB(String placeId, String uri) {
        myRef.child(placeId).child("url").setValue(uri);
    }

    public static void updatePlaceFromDB(String placeId, String nombre, String descripcion, String url) {
        PlaceVO lugar = new PlaceVO(nombre, descripcion);
        lugar.setUrl(url);
        DatabaseReference idRef = myRef.child(placeId);
        idRef.setValue(null);
        idRef.setValue(lugar);
    }

    /*public static void deleteAllPlacesFromDB() {
        myRef.setValue(null);
    }

    public static String getUriPlaceFromDB(String placeId) {
        final Uri[] uri = new Uri[1];
        myRef.child(placeId).child("uri").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                uri[0] = (Uri) snapshot.getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return uri[0].toString();
    }


    public static PlaceVO getPlaceFromDB(String placeId) {
        final PlaceVO[] lugar = new PlaceVO[1];

        myRef.child(placeId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                    lugar[0] = snapshot.getValue(PlaceVO.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        return lugar[0];
    }*/
}
