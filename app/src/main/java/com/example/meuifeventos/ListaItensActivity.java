package com.example.meuifeventos;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListaItensActivity extends AppCompatActivity {
    private RecyclerView recyclerViewItens;
    private ItemAdapter itemAdapter;
    private FirebaseFirestore db;
    private List<Item> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_itens);

        recyclerViewItens = findViewById(R.id.recyclerViewItens);
        recyclerViewItens.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();

        loadItensFromFirebase();
    }

    private void loadItensFromFirebase() {
        db.collection("itens")
                .whereEqualTo("disponibilidade", true) // Filtrar apenas itens disponíveis
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        itemList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Item item = document.toObject(Item.class);
                            item.setId(document.getId());
                            itemList.add(item);
                        }
                        itemAdapter = new ItemAdapter(itemList);
                        recyclerViewItens.setAdapter(itemAdapter);
                    } else {
                        Log.e("ListaItens", "Erro ao carregar itens", task.getException());
                    }
                });
    }
}

