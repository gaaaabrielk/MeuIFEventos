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

public class TurmaSelecaoActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTurmas;
    private TurmaAdapter turmaAdapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<String> turmasList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turma_selecao);


        Button btnVoltarInicial = findViewById(R.id.btnVoltarInicial);
        btnVoltarInicial.setOnClickListener(v -> {
            Intent intent = new Intent(this, menu_activity.class);
            startActivity(intent);
        });

        recyclerViewTurmas = findViewById(R.id.recyclerViewTurmas);
        recyclerViewTurmas.setLayoutManager(new LinearLayoutManager(this));

        loadTurmasFromFirebase();
    }

    private void loadTurmasFromFirebase() {
        db.collection("Turmas") //
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        turmasList.clear();
                        QuerySnapshot querySnapshot = task.getResult();
                        if (querySnapshot != null) {
                            for (QueryDocumentSnapshot document : querySnapshot) {
                                String turmaId = document.getId();
                                turmasList.add(turmaId);
                            }
                            turmaAdapter = new TurmaAdapter(turmasList, turmaId -> {
                                Log.d("TurmaSelecaoActivity", "Turma selecionada: " + turmaId);
                                Intent intent = new Intent(TurmaSelecaoActivity.this, addEvento.class);
                                intent.putExtra("TURMA_ID", turmaId);
                                startActivity(intent);

                            });
                            recyclerViewTurmas.setAdapter(turmaAdapter);
                        }
                    }
                });


    }
}

