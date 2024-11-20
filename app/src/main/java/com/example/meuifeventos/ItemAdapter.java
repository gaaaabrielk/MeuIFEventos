package com.example.meuifeventos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<Item> itemList;
    private OnItemClickListener onItemClickListener; // Callback para cliques

    // Construtor atualizado
    public ItemAdapter(List<Item> itemList, OnItemClickListener onItemClickListener) {
        this.itemList = itemList;
        this.onItemClickListener = onItemClickListener;
    }

    public ItemAdapter(Object itemList) {
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.bind(item, onItemClickListener); // Passa o item e o listener para o ViewHolder
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome, tvQuantidade;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvNome);
            tvQuantidade = itemView.findViewById(R.id.tvQuantidade);
        }

        public void bind(Item item, OnItemClickListener onItemClickListener) {
            tvNome.setText(item.getNome());
            tvQuantidade.setText("Quantidade: " + item.getQuantidade());
            itemView.setOnClickListener(v -> onItemClickListener.onItemClick(item));
        }
    }

    // Interface para o callback de clique
    public interface OnItemClickListener {
        void onItemClick(Item item);
    }
}

