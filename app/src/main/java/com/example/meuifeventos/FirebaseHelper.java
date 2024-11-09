package com.example.meuifeventos;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.CollectionReference;

import java.util.List;

public class FirebaseHelper {
    private FirebaseFirestore db;

    public FirebaseHelper() {
        db = FirebaseFirestore.getInstance();
    }

    public void getAlunosByTurma(String turmaId, final OnGetAlunosListener listener) {
        CollectionReference turmaRef = db.collection("Turmas").document(turmaId).collection("Alunos");

        turmaRef.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();
                        List<DocumentSnapshot> alunosList = querySnapshot.getDocuments();
                        listener.onSuccess(alunosList);
                    } else {
                        listener.onFailure(task.getException());
                    }
                });
    }

    public interface OnGetAlunosListener {
        void onSuccess(List<DocumentSnapshot> alunos);
        void onFailure(Exception e);
    }
}
