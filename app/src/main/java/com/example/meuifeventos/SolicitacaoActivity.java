package com.example.meuifeventos;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class SolicitacaoActivity extends AppCompatActivity {

    private TextView tvAlunoId;
    private Button btnSolicitar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitacao);

        // Recuperando os dados passados pela Intent
        String alunoId = getIntent().getStringExtra("ALUNO_ID");

        // Inicializando as Views
        tvAlunoId = findViewById(R.id.tvAlunoId);
        btnSolicitar = findViewById(R.id.btnSolicitar);

        // Exibindo as informações na tela
        tvAlunoId.setText("Aluno ID: " + alunoId);

        // Configurando o botão para fazer a solicitação
        btnSolicitar.setOnClickListener(v -> {
            registrarSolicitacao(alunoId);
        });
    }

    // Método para registrar a solicitação no Firebase
    private void registrarSolicitacao(String alunoId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Criando o objeto de solicitação
        Solicitacao solicitacao = new Solicitacao(alunoId, "Pendente");

        // Adicionando a solicitação na coleção "solicitacoes"
        db.collection("solicitacoes")
                .add(solicitacao)
                .addOnSuccessListener(documentReference -> {
                    // Exibe uma mensagem de sucesso
                    Toast.makeText(SolicitacaoActivity.this, "Solicitação registrada com sucesso!", Toast.LENGTH_SHORT).show();
                    finish(); // Finaliza a Activity e volta para a tela anterior
                })
                .addOnFailureListener(e -> {
                    // Exibe uma mensagem de erro
                    Toast.makeText(SolicitacaoActivity.this, "Erro ao registrar solicitação.", Toast.LENGTH_SHORT).show();
                });
    }
}
