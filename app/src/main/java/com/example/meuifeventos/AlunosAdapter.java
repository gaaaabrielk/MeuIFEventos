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
    private List<Aluno> alunos;

    // Construtor que recebe a lista de alunos
    public AlunosAdapter(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    // Atualiza a lista de alunos e notifica o RecyclerView
    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
        notifyDataSetChanged(); // Notifica o RecyclerView para atualizar a exibição
    }

    @NonNull
    @Override
    public AlunoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla o layout de item para cada aluno
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_aluno, parent, false);
        return new AlunoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlunoViewHolder holder, int position) {
        // Popula a view com os dados do aluno
        Aluno aluno = alunos.get(position);
        holder.nomeTextView.setText(aluno.getNome());

        Log.d("AdapterBinding", "Aluno exibido: Nome = " + aluno.getNome() + ", Matrícula = " + aluno.getMatricula());
    }

    @Override
    public int getItemCount() {
        return alunos.size(); // Retorna o número de alunos
    }

    public static class AlunoViewHolder extends RecyclerView.ViewHolder {
        TextView nomeTextView;

        public AlunoViewHolder(View itemView) {
            super(itemView);
            nomeTextView = itemView.findViewById(R.id.alunoName);
        }
    }
}
