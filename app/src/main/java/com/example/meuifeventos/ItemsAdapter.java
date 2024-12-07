package com.example.meuifeventos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {

    private List<Items> itemsList;

    public ItemsAdapter(List<Items> itemsList) {
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista, parent, false);
        return new ItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        Items item = itemsList.get(position);
        holder.textViewNome.setText(item.getNome());
        holder.textViewDescricao.setText(item.getDescricao());
        holder.textViewLocalEncontrado.setText(item.getLocalEncontrado());
        holder.textViewDataEncontrado.setText(item.getDataEncontrado());
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNome, textViewDescricao, textViewLocalEncontrado, textViewDataEncontrado;
        Button devolver;

        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNome = itemView.findViewById(R.id.textViewNome);
            textViewDescricao = itemView.findViewById(R.id.textViewDescricao);
            textViewLocalEncontrado = itemView.findViewById(R.id.textViewLocalEncontrado);
            textViewDataEncontrado = itemView.findViewById(R.id.textViewDataEncontrado);
            devolver = itemView.findViewById(R.id.devolver);

            // Evento do botão devolver
            devolver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        String itemId = itemsList.get(position).getId();
                        db.collection("achados_perdidos")
                                .document(itemId)
                                .delete()
                                .addOnSuccessListener(aVoid -> {
                                    // Remoção bem-sucedida no Firestore
                                    if (position != RecyclerView.NO_POSITION) {
                                        itemsList.remove(position); // Remove o item da lista
                                        notifyItemRemoved(position); // Notifica a RecyclerView sobre a remoção
                                        notifyItemRangeChanged(position, itemsList.size()); // Atualiza os índices dos itens restantes
                                        Toast.makeText(v.getContext(), "Item devolvido com sucesso!", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(e -> {

                                    Toast.makeText(v.getContext(), "Erro ao devolver o item: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                });
                    }
                }
            });


        }
    }
}
