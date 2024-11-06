package com.example.meuifeventos;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class addEvento extends AppCompatActivity {

    private List<Evento> eventos = new ArrayList<>();
    private EventoAdapter eventoAdapter;
    private EditText etTitulo, etDescricao, etData, etLocal;
    private Button btnAddEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_evento);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        eventoAdapter = new EventoAdapter(eventos);
        recyclerView.setAdapter(eventoAdapter);

        etTitulo = findViewById(R.id.etTitulo);
        etDescricao = findViewById(R.id.etDescricao);
        etData = findViewById(R.id.etData);
        etLocal = findViewById(R.id.etLocal);
        btnAddEvento = findViewById(R.id.btnAddEvento);

        etData.setOnClickListener(view -> showDatePickerDialog());

        btnAddEvento.setOnClickListener(view -> addEvento());
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
            String data = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
            etData.setText(data);
        }, year, month, day);

        datePickerDialog.show();
    }

    private void addEvento() {
        String titulo = etTitulo.getText().toString();
        String descricao = etDescricao.getText().toString();
        String data = etData.getText().toString();
        String local = etLocal.getText().toString();

        if (!titulo.isEmpty() && !descricao.isEmpty() && !data.isEmpty() && !local.isEmpty()) {
            Evento evento = new Evento(titulo, descricao, data, local);
            eventos.add(evento);
            eventoAdapter.notifyItemInserted(eventos.size() - 1);


            etTitulo.setText("");
            etDescricao.setText("");
            etData.setText("");
            etLocal.setText("");
        }
    }


}
