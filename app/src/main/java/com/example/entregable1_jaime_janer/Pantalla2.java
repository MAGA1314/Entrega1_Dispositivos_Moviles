package com.example.entregable1_jaime_janer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Pantalla2 extends AppCompatActivity {

    private TextView txtIntent, txtContador;
    private Button btnVolverAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);
        txtIntent = findViewById(R.id.txtIntent);

        String texto2Pantalla1 = getIntent().getStringExtra("texto2_extra");
        String textoPantalla1 = getIntent().getStringExtra("texto_extra");
        txtIntent.setText(textoPantalla1);

        btnVolverAtras = findViewById(R.id.btnVolverAtras);

        btnVolverAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pantalla2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        txtContador = findViewById(R.id.txtContador);
        txtContador.setText(txtContador.getText().toString() + texto2Pantalla1);


    }
}