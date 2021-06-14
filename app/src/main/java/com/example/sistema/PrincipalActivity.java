package com.example.sistema;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.usage.NetworkStatsManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sistema.config.ConfiguracaoFirebase;
import com.example.sistema.modelo.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity implements AlunoAdapter.ClickListener {

    List<Usuario> usuarios;
    RecyclerView recyclerView;
    AlunoAdapter alunoAdapter;
    DatabaseReference databaseReference;
    AlunoAdapter.ClickListener clickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        clickListener = this;
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        usuarios = new ArrayList<>();
        databaseReference = ConfiguracaoFirebase.getFirebaseDatabase();
        databaseReference.child("Usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dn:snapshot.getChildren()){
                    Usuario u = dn.getValue(Usuario.class);
                    usuarios.add(u);
                    alunoAdapter = new AlunoAdapter(usuarios,clickListener);
                    recyclerView.setAdapter(alunoAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onItemClick(int position, View v) {
        Usuario u = usuarios.get(position);
        Intent intent = new Intent(this,ProfessorActivity.class);
        intent.putExtra("id",u.getId());
        startActivity(intent);
        //Toast.makeText(this,"Cliquei na posição: "+position,Toast.LENGTH_SHORT).show();
    }
}