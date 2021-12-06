package com.ciclo4_moviles_g4_2.pappseando_app.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.ciclo4_moviles_g4_2.pappseando_app.R;
import com.ciclo4_moviles_g4_2.pappseando_app.models.DBManager;
import com.ciclo4_moviles_g4_2.pappseando_app.models.PlaceVO;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class FormPlaceActivity extends AppCompatActivity {

    private StorageReference photoRef;
    private ImageView placeImg;
    private ProgressDialog progressMsg;
    private String currentPhotoPath;
    private PlaceVO lugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_place);

        placeImg = findViewById(R.id.imageView);
        Glide.with(this)
                .load(R.drawable.prueba)
                .fitCenter()
                .centerCrop()
                .into(placeImg);

        TextView nombre = findViewById(R.id.tv_nombreLugar);
        TextView descripcion = findViewById(R.id.tv_descLugar);
        EditText nombreEdit = findViewById(R.id.Nombre);
        EditText coordEdit = findViewById(R.id.editTextTextPersonName3);
        EditText descrEdit = findViewById(R.id.editTextTextPersonName4);
        ImageButton btnCamara = findViewById(R.id.btnCamara);
        Button btnGuardar = findViewById(R.id.btnGuardar);
        progressMsg = new ProgressDialog(this);

        lugar = (PlaceVO) getIntent().getSerializableExtra("lugar");

        if (lugar != null) {
            nombre.setText(lugar.getNombre());
            descripcion.setText(lugar.getDescripcion());
            nombreEdit.setText(lugar.getNombre());
            coordEdit.setText(lugar.getLocation());
            descrEdit.setText(lugar.getDescripcion());
            photoRef = lugar.obtainImgRef();
            if (lugar.getUrl() != null)
                putImageOnView(lugar.getUrl());
        }

        btnCamara.setOnClickListener(v -> {
            //Abre el gestor de cámara para sacar una foto
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Ensure that there's a camera activity to handle the intent
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                // Create the File where the photo should go
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                    currentPhotoPath = photoFile.getAbsolutePath();
                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
                // Continue only if the File was successfully created
                if (photoFile != null) {
                    Uri uriPhoto = FileProvider.getUriForFile(this,
                            "com.ciclo4_moviles_g4_2.pappseando_app.fileprovider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriPhoto);
                    startActivityForResult(takePictureIntent, 1);
                }
            }
        });

        btnGuardar.setOnClickListener(v -> {
            String nombreNuevo, coordNuevas, descrNueva;

            nombreNuevo = nombreEdit.getText().toString();
            coordNuevas = coordEdit.getText().toString();
            descrNueva = descrEdit.getText().toString();

            Intent intent = new Intent(FormPlaceActivity.this, ListViewActivity.class);
            if (!nombreNuevo.isEmpty() && !coordNuevas.isEmpty() && !descrNueva.isEmpty()) {
                if (lugar != null)
                    DBManager.updatePlaceFromDB(lugar.getId(), nombreNuevo, descrNueva, lugar.getUrl());
                else
                    DBManager.putPlaceOnDB(nombreNuevo, descrNueva);
                Toast.makeText(getApplicationContext(), "El lugar '" + nombreNuevo + "' se ha guardado exitosamente" , Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(getApplicationContext(), "Hay campos vacíos... No se pudo guardar el lugar", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        //Evitar el uso del botón para volver en esta actividad
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bmpOrig;

        if (resultCode == -1 && requestCode == 1) {
            //Carga la imagen tomada desde la cámara a Firebase Storage
            if (currentPhotoPath != null || photoRef != null) {
                loadProgressMessage();
                bmpOrig = BitmapFactory.decodeFile(currentPhotoPath); //crear bitmap desde ubicación tipo string
                //bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uriPhoto); //crear bitmap desde uri

                photoRef.putBytes(convertBitMapToByteArray(bmpOrig)).addOnSuccessListener(taskSnapshot -> {
                    progressMsg.dismiss();
                    Toast.makeText(getApplicationContext(), "Se ha subido la foto al storage correctamente", Toast.LENGTH_SHORT).show();
                    new File(currentPhotoPath).delete(); //Borra la foto almacenada tras subirla al storage
                    bmpOrig.recycle();
                    getUriFromFirebase();
                });
            }
        }
    }

    public void getUriFromFirebase() {
        //Obtiene la uri de la imagen guardada desde Firebase Storage
        photoRef.getDownloadUrl().addOnSuccessListener(uri -> {
            String url = uri.toString();
            DBManager.putUriPlaceOnDB(lugar.getId(), url);
            lugar.setUrl(url);
            putImageOnView(url);
        })
                .addOnFailureListener(exception -> {
                });
    }

    public void putImageOnView(String url) {
        //Muestra la imagen referenciada en el ImageView
        Glide.with(this)
                .load(url)
                .fitCenter()
                .centerCrop()
                .into(placeImg);
    }

    private void loadProgressMessage() {
        progressMsg.setTitle("Subiendo imagen a Firebase Storage");
        progressMsg.setMessage("Espere un momento, por favor...");
        progressMsg.setCancelable(false);
        progressMsg.show();
    }

    private File createImageFile() throws IOException {
        /*/ Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_"; */

        return File.createTempFile(
                "camera_test",  /* prefix */
                ".jpg",         /* suffix */
                getExternalFilesDir(Environment.DIRECTORY_PICTURES)      /* directory */
        );
    }

    private Bitmap downscaleBitmap(Bitmap bitmap) {
        int bmpWidth = bitmap.getWidth();
        int bmpHeight = bitmap.getHeight();
        Matrix mat = new Matrix();

        float scale = Math.min((float) placeImg.getWidth() / bmpWidth, (float) placeImg.getHeight() / bmpHeight);
        mat.postScale(scale, scale);
        mat.postRotate(90);
        return Bitmap.createBitmap(bitmap, 0, 0, bmpWidth, bmpHeight, mat, true);
    }

    private byte[] convertBitMapToByteArray(Bitmap bitmap) {
        //Convierte el bitmap en un array de bytes
        bitmap = downscaleBitmap(bitmap);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.WEBP, 80, stream);
        byte[] byteArray = stream.toByteArray();
        bitmap.recycle();
        return byteArray;
    }
}