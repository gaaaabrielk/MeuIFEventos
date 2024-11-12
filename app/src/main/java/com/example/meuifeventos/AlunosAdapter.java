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


    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
        notifyDataSetChanged(); // Notifica o RecyclerView para atualizar a exibição
    }

    @NonNull
    @Override
    public AlunoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_aluno, parent, false);
        return new AlunoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlunoViewHolder holder, int position) {

        Aluno aluno = alunos.get(position);
        holder.nomeTextView.setText(aluno.getNome());

        Log.d("AdapterBinding", "Aluno exibido: Nome = " + aluno.getNome() + ", Matrícula = " + aluno.getMatricula());
    }

    @Override
    public int getItemCount() {
        return alunos.size();
    }

    public static class AlunoViewHolder extends RecyclerView.ViewHolder {
        TextView nomeTextView;

        public AlunoViewHolder(View itemView) {
            super(itemView);
            nomeTextView = itemView.findViewById(R.id.alunoName);
        }
    }
}
