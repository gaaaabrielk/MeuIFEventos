package com.example.meuifeventos;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ItemActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView recyclerViewItens;
    private ItemAdapter itemAdapter;  // Adapter para listar os itens

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        recyclerViewItens = findViewById(R.id.recyclerViewItens);
        recyclerViewItens.setLayoutManager(new LinearLayoutManager(this));


        carregarItens();
    }

    // Carrega os itens do Firebase ou de outra fonte
    private void carregarItens() {
        db.collection("itens")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Item> itens = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Item item = document.toObject(Item.class);
                            itens.add(item);
                        }
                        itemAdapter = new ItemAdapter(itens, item -> {
                            Intent intent = new Intent(ItemActivity.this, SolicitacaoActivity.class);
                            intent.putExtra("ITEM_ID", item.getId()); // Envia o ID do item
                            intent.putExtra("ALUNO_ID", "aluno1");   // Exemplo de aluno ID
                            intent.putExtra("ITEM_NOME", item.getNome());    // Envia o nome do item
                            startActivity(intent);
                        });

                        recyclerViewItens.setAdapter(itemAdapter);

                    }
                });
    }
}
