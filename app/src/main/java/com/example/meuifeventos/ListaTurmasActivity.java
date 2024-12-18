package com.example.meuifeventos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListaTurmasActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTurmas;
    private TurmaAdapter turmaAdapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<String> turmasList = new ArrayList<>(); // Lista de IDs das turmas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_turmas);

        Button btnVoltarInicial = findViewById(R.id.btnVoltarInicial);
        btnVoltarInicial.setOnClickListener(v -> {
            Intent intent = new Intent(ListaTurmasActivity.this, MainActivity.class);
            startActivity(intent);
        });


        recyclerViewTurmas = findViewById(R.id.recyclerViewTurmas);
        recyclerViewTurmas.setLayoutManager(new LinearLayoutManager(this));

        loadTurmasFromFirebase();
    }

    // Método para carregar as turmas do Firebase
    private void loadTurmasFromFirebase() {
        db.collection("Turmas")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        turmasList.clear();
                        QuerySnapshot querySnapshot = task.getResult();
                        if (querySnapshot != null) {
                            for (QueryDocumentSnapshot document : querySnapshot) {
                                String turmaId = document.getId(); // Pega o ID da turma
                                turmasList.add(turmaId); // Adiciona o ID à lista
                            }
                            turmaAdapter = new TurmaAdapter(turmasList, turmaId -> {
                                // Quando uma turma for clicada, passamos o TURMA_ID
                                Intent intent = new Intent(this, AlunosActivity.class);
                                intent.putExtra("TURMA_ID", turmaId);
                                startActivity(intent);

                            });
                            recyclerViewTurmas.setAdapter(turmaAdapter);
                        }

                    }
                });


    }
}

