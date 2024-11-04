package com.example.meuifeventos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class turmas_activity extends AppCompatActivity {

    private TurmasAdapter turmasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_turmas);

        turmasAdapter = new TurmasAdapter(new ArrayList<>());

        RecyclerView salame = findViewById(R.id.recyclerViewItems);
        salame.setAdapter(turmasAdapter());



    }

    private RecyclerView.Adapter turmasAdapter() {
        return null;
    }
}