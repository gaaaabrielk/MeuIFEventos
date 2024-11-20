package com.example.meuifeventos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class SolicitacaoActivity extends AppCompatActivity {
    private TextView tvItemNome, tvItemId, tvAlunoId;
    private Button btnSolicitar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitacao);

        // Recuperando os dados passados pela Intent
        String itemNome = getIntent().getStringExtra("ITEM_NOME");
        String itemId = getIntent().getStringExtra("ITEM_ID");
        String alunoId = getIntent().getStringExtra("ALUNO_ID");

        // Inicializando as Views
        tvItemNome = findViewById(R.id.tvItemNome);
        tvItemId = findViewById(R.id.tvItemId);
        tvAlunoId = findViewById(R.id.tvAlunoId);
        btnSolicitar = findViewById(R.id.btnSolicitar);

        // Exibindo as informações na tela
        tvItemNome.setText("Item Solicitado: " + itemNome);
        tvItemId.setText("ID do Item: " + itemId);
        tvAlunoId.setText("Aluno ID: " + alunoId);

        // Configurando o botão para fazer a solicitação
        btnSolicitar.setOnClickListener(v -> {
            registrarSolicitacao(itemId, alunoId, itemNome);
        });
    }

    // Método para registrar a solicitação no Firebase
    private void registrarSolicitacao(String itemId, String alunoId, String itemNome) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Criando o objeto de solicitação
        Solicitacao solicitacao = new Solicitacao(itemId, alunoId, "Pendente"); // Status pode ser "Pendente", "Aprovado", etc.

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
