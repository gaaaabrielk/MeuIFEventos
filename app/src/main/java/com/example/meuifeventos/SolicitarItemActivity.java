package com.example.meuifeventos;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class SolicitarItemActivity extends AppCompatActivity {

    private EditText etAlunoId, etItemId;
    private Button btnSolicitarItem;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_item);

        etAlunoId = findViewById(R.id.etAlunoId);
        etItemId = findViewById(R.id.etItemId);
        btnSolicitarItem = findViewById(R.id.btnSolicitarItem);

        db = FirebaseFirestore.getInstance();

        btnSolicitarItem.setOnClickListener(v -> solicitarItem());
    }

    private void solicitarItem() {
        String alunoId = etAlunoId.getText().toString();
        String itemId = etItemId.getText().toString();

        if (alunoId.isEmpty() || itemId.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }


        Solicitacao solicitacao = new Solicitacao(alunoId, itemId, "Pendente");

        db.collection("solicitacoes")
                .add(solicitacao)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Solicitação enviada com sucesso!", Toast.LENGTH_SHORT).show();

                    etAlunoId.setText("");
                    etItemId.setText("");
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Erro ao enviar solicitação.", Toast.LENGTH_SHORT).show();
                    Log.e("Firebase", "Erro ao salvar solicitação", e);
                });
    }
}
