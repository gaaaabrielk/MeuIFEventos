package com.example.meuifeventos;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class selecionar extends AppCompatActivity {


    private RecyclerView recyclerViewTurmas;
    private TurmaAdapter turmaAdapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<String> turmasList = new ArrayList<>(); // Lista de IDs das turmas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_selecionar);


        recyclerViewTurmas = findViewById(R.id.recyclerViewTurmas);
        recyclerViewTurmas.setLayoutManager(new LinearLayoutManager(this));

        loadTurmasFromFirebase();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }


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
                                Intent intent = new Intent(this, ExibirEventosActivity.class);
                                intent.putExtra("TURMA_ID", turmaId);
                                startActivity(intent);

                            });
                            recyclerViewTurmas.setAdapter(turmaAdapter);
                        }

                    }
                });
    }
}