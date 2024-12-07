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

import com.example.meuifeventos.ItemActivity;
import com.example.meuifeventos.ListaTurmasActivity;
import com.example.meuifeventos.R;
import com.example.meuifeventos.SolicitacaoListActivity;
import com.example.meuifeventos.TurmaSelecaoActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class menu_activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu2);


        FloatingActionButton btnNavigate = findViewById(R.id.Eventos);
        btnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu_activity2.this, selecionar.class);
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





    }
}