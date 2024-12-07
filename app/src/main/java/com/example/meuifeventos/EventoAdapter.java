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

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.EventoViewHolder> {
    private List<Evento> eventos;
    private String turmaId;

    public EventoAdapter(List<Evento> eventos, String turmaId) {
        this.eventos = eventos;
        this.turmaId = turmaId;  // Recebe o ID da turma
    }

    @NonNull
    @Override
    public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_evento, parent, false);
        return new EventoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventoViewHolder holder, int position) {
        Evento evento = eventos.get(position);
        holder.tvTitulo.setText(evento.getTitulo());
        holder.tvDescricao.setText(evento.getDescricao());
        holder.tvData.setText(evento.getData());
        holder.tvLocal.setText(evento.getLocal());
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public class EventoViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvDescricao, tvData, tvLocal;
        Button btExcluir;

        public EventoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvDescricao = itemView.findViewById(R.id.tvDescricao);
            tvData = itemView.findViewById(R.id.tvData);
            tvLocal = itemView.findViewById(R.id.tvLocal);
            btExcluir = itemView.findViewById(R.id.btExcluir);

            btExcluir.setOnClickListener(v -> {
                int position = getBindingAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Evento evento = eventos.get(position);
                    String eventoId = evento.getId(); // Pega o ID do evento (deve ser passado no Evento)

                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    // Deletar o evento da subcoleção de eventos dentro da turma
                    db.collection("Turmas")
                            .document(turmaId)  // ID da turma
                            .collection("eventos")  // Subcoleção de eventos
                            .document(eventoId)  // ID do evento
                            .delete()
                            .addOnSuccessListener(aVoid -> {
                                eventos.remove(position); // Remove o evento da lista
                                notifyItemRemoved(position); // Atualiza a RecyclerView
                                Toast.makeText(v.getContext(), "Evento excluído com sucesso!", Toast.LENGTH_SHORT).show();
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(v.getContext(), "Erro ao excluir evento: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                }
            });
        }
    }
}

