package com.example.meuifeventos;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class cEvento extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EventoAdapter eventoAdapter;
    private List<Evento> eventoList;
    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cevento);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        eventoList = new ArrayList<>();
        eventoAdapter = new EventoAdapter(eventoList);
        recyclerView.setAdapter(eventoAdapter);

        Button buttonAddEvento = findViewById(R.id.buttonAddEvento);
        buttonAddEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddEventoDialog();
            }
        });
    }

    private void showAddEventoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_add_evento, null);

        EditText editNome = view.findViewById(R.id.editNome);
        EditText editDescricao = view.findViewById(R.id.editDescricao);
        EditText editLocal = view.findViewById(R.id.editLocal);
        Button buttonPickDate = view.findViewById(R.id.buttonPickDate);

        buttonPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        builder.setView(view);
        builder.setPositiveButton("Adicionar", (dialog, which) -> {
            String nome = editNome.getText().toString();
            String descricao = editDescricao.getText().toString();
            String local = editLocal.getText().toString();

            if (selectedDate != null && !nome.isEmpty() && !descricao.isEmpty() && !local.isEmpty()) {
                Evento novoEvento = new Evento(nome, descricao, selectedDate, local);
                eventoList.add(novoEvento);
                eventoAdapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Cancelar", null);
        builder.create().show();
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            }
        }, year, month, day);
        datePickerDialog.show();
    }
}
