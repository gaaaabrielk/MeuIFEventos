package com.example.meuifeventos;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class AlunosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewAlunos;
    private AlunosAdapter alunosAdapter;
    private List<Aluno> alunosList = new ArrayList<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alunos);

        recyclerViewAlunos = findViewById(R.id.recyclerViewAlunos);
        recyclerViewAlunos.setLayoutManager(new LinearLayoutManager(this));

        // Recuperando o ID da turma passado pela Intent
        String turmaId = getIntent().getStringExtra("TURMA_ID");
        Log.d("AlunosActivity", "Turma ID: " + turmaId);  // Para verificar se o ID foi passado corretamente

        // Carregar os alunos da turma selecionada
        loadAlunosFromFirebase(turmaId);
    }

    // Método para carregar os alunos da turma
    private void loadAlunosFromFirebase(String turmaId) {
        db.collection("Turmas")
                .document(turmaId) // Usando o ID da turma
                .collection("Alunos") // Subcoleção de alunos
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        alunosList.clear();
                        QuerySnapshot querySnapshot = task.getResult();
                        if (querySnapshot != null) {
                            for (QueryDocumentSnapshot document : querySnapshot) {
                                Aluno aluno = document.toObject(Aluno.class);
                                alunosList.add(aluno);
                            }
                            alunosAdapter = new AlunosAdapter(alunosList);
                            recyclerViewAlunos.setAdapter(alunosAdapter);
                        }
                    }
                });
    }
}
