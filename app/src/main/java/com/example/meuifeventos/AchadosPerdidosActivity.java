package com.example.meuifeventos;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AchadosPerdidosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemsAdapter itemsAdapter;
    private List<Items> itemsList;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achados_perdidos);

        recyclerView = findViewById(R.id.recyclerViewAchados);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemsList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();

        fetchItemsFromFirebase();
    }

    private void fetchItemsFromFirebase() {
        db.collection("achadosPerdidos")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            // Convertendo os dados do Firestore para a classe Items
                            Items item = document.toObject(Items.class);
                            itemsList.add(item);
                        }
                        // Atualiza a RecyclerView com os dados recuperados
                        itemsAdapter = new ItemsAdapter(itemsList);
                        recyclerView.setAdapter(itemsAdapter);
                    } else {
                        Log.d("AchadosPerdidosActivity", "Erro ao buscar dados", task.getException());
                    }
                });
    }
}
