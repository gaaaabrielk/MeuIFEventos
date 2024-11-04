package com.example.meuifeventos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class TurmasAdapter extends RecyclerView.Adapter<TurmasAdapter.TurmasViewHolder> {
    private final List<Object> objects;

    public TurmasAdapter(List<Object> objects) {
        this.objects = objects;

    }

    @NonNull
    @Override
    public TurmasAdapter.TurmasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_turmas, parent, false);
        return new TurmasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TurmasAdapter.TurmasViewHolder holder, int position) {
        Object object = objects.get(position);
        holder.bind(object);

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    class TurmasViewHolder extends RecyclerView.ViewHolder {
        public TurmasViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Object object) {
        }
    }
}
