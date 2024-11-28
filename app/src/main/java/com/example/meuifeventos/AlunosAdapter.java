package com.example.meuifeventos;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class AlunosAdapter extends RecyclerView.Adapter<AlunosAdapter.AlunoViewHolder> {

    private List<Aluno> alunosList;

    public AlunosAdapter(List<Aluno> alunosList) {
        this.alunosList = alunosList;
    }

    @Override
    public AlunoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_aluno, parent, false);
        return new AlunoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlunoViewHolder holder, int position) {
        Aluno aluno = alunosList.get(position);
        holder.textViewNome.setText(aluno.getNome());
        holder.textViewMatricula.setText(aluno.getMatricula());
    }

    @Override
    public int getItemCount() {
        return alunosList.size();
    }

    public static class AlunoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNome;
        TextView textViewMatricula;

        public AlunoViewHolder(View itemView) {
            super(itemView);
            textViewNome = itemView.findViewById(R.id.textViewNome);
            textViewMatricula = itemView.findViewById(R.id.textViewMatricula);
        }
    }
}
