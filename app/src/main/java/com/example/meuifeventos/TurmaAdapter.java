package com.example.meuifeventos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TurmaAdapter extends RecyclerView.Adapter<TurmaAdapter.TurmaViewHolder> {

    private List<String> turmas;
    private OnItemClickListener listener;

    public TurmaAdapter(List<String> turmas, OnItemClickListener listener) {
        this.turmas = turmas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TurmaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_turma, parent, false);
        return new TurmaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TurmaViewHolder holder, int position) {
        String turmaId = turmas.get(position);
        holder.turmaName.setText(turmaId);

        // Ao clicar na turma, a listener é chamada
        holder.itemView.setOnClickListener(v -> listener.onItemClick(turmaId));
    }

    @Override
    public int getItemCount() {
        return turmas.size();
    }

    public static class TurmaViewHolder extends RecyclerView.ViewHolder {
        TextView turmaName;

        public TurmaViewHolder(View itemView) {
            super(itemView);
            turmaName = itemView.findViewById(R.id.turmaName);
        }
    }

    // Interface para capturar o clique no item da turma
    public interface OnItemClickListener {
        void onItemClick(String turmaId); // Passa o ID da turma
    }
}
