package com.example.sistema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btLogar;
    private Button btCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btLogar = findViewById(R.id.btLogar);
        btCadastrar = findViewById(R.id.btCadastrar);

        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telaLogar();
            }
        });

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telaCadastrar();
            }
        });

    }

    private void telaCadastrar() {
        startActivity(new Intent(this, CadastroActivity.class));
    }

    private void telaLogar() {
        startActivity(new Intent(this,LoginActivity.class));
    }
}