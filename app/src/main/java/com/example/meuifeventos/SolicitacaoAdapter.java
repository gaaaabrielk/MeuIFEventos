package com.example.meuifeventos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SolicitacaoAdapter extends RecyclerView.Adapter<SolicitacaoAdapter.ViewHolder> {

    private final List<Solicitacao> solicitacoes;
    private final OnSolicitacaoActionListener actionListener;

    public interface OnSolicitacaoActionListener {
        void onAction(Solicitacao solicitacao, boolean aprovar); // true para aprovar, false para rejeitar
    }

    public SolicitacaoAdapter(List<Solicitacao> solicitacoes, OnSolicitacaoActionListener actionListener) {
        this.solicitacoes = solicitacoes;
        this.actionListener = actionListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_solicitacao, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Solicitacao solicitacao = solicitacoes.get(position);

        holder.tvAlunoId.setText("Aluno ID: " + solicitacao.getAlunoId());
        holder.tvStatus.setText("Status: " + solicitacao.getStatus());

        // Botão "Aprovar"
        holder.btnAprovar.setOnClickListener(v -> {
            if (actionListener != null) {
                actionListener.onAction(solicitacao, true); // true para aprovar
            }
        });

        // Botão "Rejeitar"
        holder.btnRejeitar.setOnClickListener(v -> {
            if (actionListener != null) {
                actionListener.onAction(solicitacao, false); // false para rejeitar
            }
        });
    }

    @Override
    public int getItemCount() {
        return solicitacoes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvAlunoId, tvStatus;
        Button btnAprovar, btnRejeitar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAlunoId = itemView.findViewById(R.id.tvAlunoId);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            btnAprovar = itemView.findViewById(R.id.btnAprovar);
            btnRejeitar = itemView.findViewById(R.id.btnRejeitar);
        }
    }
}
