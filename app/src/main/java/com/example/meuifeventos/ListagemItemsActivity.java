package com.example.meuifeventos;

import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListagemItemsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemsAdapter adapter;
    private List<Items> itemsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_items);

        recyclerView = findViewById(R.id.recyclerViewItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemsAdapter(itemsList);
        recyclerView.setAdapter(adapter);

        carregarItems();
    }

    private void carregarItems() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("achados_perdidos")

                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    itemsList.clear();
                    for (com.google.firebase.firestore.DocumentSnapshot document : queryDocumentSnapshots) {
                        Items item = document.toObject(Items.class);
                        itemsList.add(item);
                    }
                    /*Items item = new Items();

                    item.setNome("ITEM EXEMPLO");
                    item.setDescricao("Descrição do item");
                    item.setDescricao("GUGU DADA");
                    item.setAlunoID("1");
                    item.setDataAchado(null);
                    item.setLocalEncontrado("fefefefef");
                    item.setStatus(true);

                    itemsList.add(item);*/

                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Erro ao carregar os itens!", Toast.LENGTH_SHORT).show());
    }
}
