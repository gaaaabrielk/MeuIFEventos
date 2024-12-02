package com.example.meuifeventos;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class SolicitacaoListActivity extends AppCompatActivity implements SolicitacaoAdapter.OnSolicitacaoActionListener {

    private RecyclerView recyclerView;
    private SolicitacaoAdapter adapter;
    private List<Solicitacao> solicitacoesList;
    private FirebaseFirestore db;
    private CollectionReference solicitacoesRef;
    private ListenerRegistration listenerRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitacao_list);

        recyclerView = findViewById(R.id.recyclerViewSolicitacoes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        solicitacoesList = new ArrayList<>();
        adapter = new SolicitacaoAdapter(solicitacoesList, this);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();
        solicitacoesRef = db.collection("solicitacoes");

        // Carregar as solicitações do Firestore
        carregarSolicitacoes();
    }

    @Override
    public void onAction(Solicitacao solicitacao, boolean aprovar) {
        // Atualiza o status da solicitação
        String status = aprovar ? "Aprovado" : "Rejeitado";
        DocumentReference documentRef = db.collection("solicitacoes").document(solicitacao.getId());

        // Atualiza o status no Firestore
        documentRef.update("status", status)
                .addOnSuccessListener(aVoid -> {
                    // Após atualizar, remova o item da lista e do Firestore
                    removerSolicitacao(solicitacao);
                    Toast.makeText(SolicitacaoListActivity.this, "Solicitação " + status, Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> Toast.makeText(SolicitacaoListActivity.this, "Erro ao atualizar solicitação.", Toast.LENGTH_SHORT).show());
    }

    private void removerSolicitacao(Solicitacao solicitacao) {
        // Remover a solicitação da lista local
        int position = solicitacoesList.indexOf(solicitacao);
        if (position != -1) {
            solicitacoesList.remove(position);
            adapter.notifyItemRemoved(position);
        }

        // Excluir a solicitação do Firestore
        DocumentReference documentRef = db.collection("solicitacoes").document(solicitacao.getId());
        documentRef.delete()
                .addOnSuccessListener(aVoid -> {
                    // Solicitação removida com sucesso do Firestore
                    Toast.makeText(SolicitacaoListActivity.this, "Solicitação excluída com sucesso!", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    // Caso haja erro ao remover
                    Toast.makeText(SolicitacaoListActivity.this, "Erro ao excluir solicitação.", Toast.LENGTH_SHORT).show();
                });
    }

    private void carregarSolicitacoes() {
        solicitacoesRef.get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    solicitacoesList.clear();
                    for (DocumentSnapshot document : queryDocumentSnapshots) {
                        Solicitacao solicitacao = document.toObject(Solicitacao.class);
                        solicitacao.setId(document.getId());
                        solicitacoesList.add(solicitacao);
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(SolicitacaoListActivity.this, "Erro ao carregar solicitações.", Toast.LENGTH_SHORT).show();
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (listenerRegistration != null) {
            listenerRegistration.remove();
        }
    }
}
