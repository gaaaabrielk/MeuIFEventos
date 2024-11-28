package com.example.meuifeventos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ExibirEventosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewEventos;
    private EventoAdapter eventoAdapter;
    private List<Evento> eventosList;
    private String turmaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_eventos);

        Button btnVoltarInicial = findViewById(R.id.btnVoltarInicial);
        btnVoltarInicial.setOnClickListener(v -> {
            Intent intent = new Intent(ExibirEventosActivity.this, MainActivity.class);
            startActivity(intent);
        });

        Button btnVoltarTurma = findViewById(R.id.btnVoltarTurma);
        btnVoltarTurma.setOnClickListener(v -> {
            Intent intent = new Intent(ExibirEventosActivity.this, ListaTurmasActivity.class);
            startActivity(intent);
        });




        turmaId = getIntent().getStringExtra("TURMA_ID");

        recyclerViewEventos = findViewById(R.id.recyclerViewEventos);
        recyclerViewEventos.setLayoutManager(new LinearLayoutManager(this));

        eventosList = new ArrayList<>();
        eventoAdapter = new EventoAdapter(eventosList);
        recyclerViewEventos.setAdapter(eventoAdapter);


        carregarEventos();
    }

    private void carregarEventos() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Turmas")
                .document(turmaId)
                .collection("eventos")  // Subcoleção de eventos da turma
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Evento evento = document.toObject(Evento.class);
                            eventosList.add(evento);
                        }
                        eventoAdapter.notifyDataSetChanged();
                    } else {
                        Log.w("ExibirEventosActivity", "Erro ao carregar eventos.", task.getException());
                        Toast.makeText(ExibirEventosActivity.this, "Erro ao carregar eventos.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

