package com.example.entregable1_jaime_janer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnAlarma, btnDescanso, btnCamara, btnCambiarPantalla, btnCerrarApp;
    private TextView txtEstado;

    private String jaime = "Jaimeeeeeeeeeeeee";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAlarma = findViewById(R.id.btnAlarma);
        btnDescanso = findViewById(R.id.btnDescanso);
        btnCamara = findViewById(R.id.btnCamara);
        txtEstado = findViewById(R.id.txtEstado);
        btnCambiarPantalla = findViewById(R.id.btnCambiarPantalla);
        btnCerrarApp =  findViewById(R.id.btnCerrarApp);



        btnAlarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAlarm("Jaimillo", 8, 35);
                Toast.makeText(MainActivity.this, "Se ha creado la alarma correctamente.", Toast.LENGTH_SHORT).show();
            }
            
        });

        btnDescanso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer("Descanso activao", 15);
                Toast.makeText(MainActivity.this, "Se ha iniciado el descanso, amigo.", Toast.LENGTH_SHORT).show();
            }
        });

        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        btnCambiarPantalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Obtener el texto del TextView
                String texto = txtEstado.getText().toString();

                // Crear un Intent para iniciar la actividad de la Pantalla 2
                Intent intent = new Intent(MainActivity.this, Pantalla2.class);

                // Agregar el texto como dato extra al Intent
                intent.putExtra("texto_extra", texto);
                intent.putExtra("texto2_extra", jaime);

                startActivity(intent);
            }
        });

        btnCerrarApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    @Override
    protected void onStop() {
        super.onStop();
        txtEstado.setText("Estado: onStop");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        txtEstado.setText("Estado: onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        txtEstado.setText("Estado: onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        txtEstado.setText("Estado: onPause");

    }

    public void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void startTimer(String message, int seconds) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_LENGTH, seconds)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            // Display error state to the user.
        }
    }
}