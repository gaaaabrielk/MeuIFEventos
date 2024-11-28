package com.example.meuifeventos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class menu_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);


        FloatingActionButton btnNavigate = findViewById(R.id.Eventos);
        btnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu_activity.this, TurmaSelecaoActivity.class);
                intent.putExtra("TURMA_ID", "TURMA");
                startActivity(intent);


            }
        });

        FloatingActionButton btnNavigate2 = findViewById(R.id.Itens);
        btnNavigate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ItemActivity.class);
                intent.putExtra("TURMA_ID", "TURMA");
                startActivity(intent);

            }
        });


        FloatingActionButton btnNaviagte3 = findViewById(R.id.Alunos);
        btnNaviagte3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AlunosActivity.class);
                intent.putExtra("TURMA_ID", "TURMA");
                startActivity(intent);
            }
        });




    }
}