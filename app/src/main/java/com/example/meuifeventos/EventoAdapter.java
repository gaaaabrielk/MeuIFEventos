package com.example.meuifeventos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.EventoViewHolder> {

    private List<Evento> eventoList;

    public EventoAdapter(List<Evento> eventoList) {
        this.eventoList = eventoList;
    }

    @NonNull
    @Override
    public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_evento, parent, false);
        return new EventoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventoViewHolder holder, int position) {
        Evento evento = eventoList.get(position);
        holder.textTitulo.setText(evento.getTitulo());
        holder.textDescricao.setText(evento.getDescricao());
        holder.textData.setText(evento.getData());
        holder.textLocal.setText(evento.getLocal());
    }

    @Override
    public int getItemCount() {
        return eventoList.size();
    }

    public static class EventoViewHolder extends RecyclerView.ViewHolder {
        TextView textTitulo, textDescricao, textData, textLocal;

        public EventoViewHolder(View itemView) {
            super(itemView);
            textTitulo = itemView.findViewById(R.id.textNome);
            textDescricao = itemView.findViewById(R.id.textDescricao);
            textData = itemView.findViewById(R.id.textData);
            textLocal = itemView.findViewById(R.id.textLocal);
        }
    }
}
