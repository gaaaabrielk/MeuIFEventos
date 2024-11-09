package com.example.meuifeventos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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

        recyclerViewTurmas = findViewById(R.id.recyclerViewTurmas);
        recyclerViewTurmas.setLayoutManager(new LinearLayoutManager(this));

        // Carregar a lista de turmas do Firestore
        loadTurmasFromFirebase();
    }

    // Método para carregar as turmas do Firebase
    private void loadTurmasFromFirebase() {
        db.collection("Turmas") // Coleção de turmas no Firestore
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
                                Intent intent = new Intent(this, ExibirEventosActivity.class);
                                intent.putExtra("TURMA_ID", turmaId);  // Passando o turmaID
                                startActivity(intent);

                            });
                            recyclerViewTurmas.setAdapter(turmaAdapter);
                        }
                    }
                });


    }
}

