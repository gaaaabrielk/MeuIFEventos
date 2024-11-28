package com.example.meuifeventos;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class addEvento extends AppCompatActivity {

    private EditText etTitulo, etDescricao, etData, etLocal;
    private Button btnAddEvento;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_evento);


        db = FirebaseFirestore.getInstance();


        etTitulo = findViewById(R.id.etTitulo);
        etDescricao = findViewById(R.id.etDescricao);
        etData = findViewById(R.id.etData);
        etLocal = findViewById(R.id.etLocal);
        btnAddEvento = findViewById(R.id.btnAddEvento);





        String turmaId = getIntent().getStringExtra("TURMA_ID");
        if (turmaId == null) {
            Log.e("addEvento", "TURMA_ID não encontrado.");
            finish();
            return;
        }

        etData.setOnClickListener(view -> showDatePickerDialog());


        btnAddEvento.setOnClickListener(view -> addEvento(turmaId));


       FloatingActionButton btnListaTurma = findViewById(R.id.listaTurmas);
        btnListaTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addEvento.this, TurmaSelecaoActivity.class);
                startActivity(intent);
            }
        });


        FloatingActionButton btnNavigate = findViewById(R.id.consultaE);
        btnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addEvento.this, ExibirEventosActivity.class);
                intent.putExtra("TURMA_ID", turmaId);
                startActivity(intent);
            }
        });
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


    private void addEvento(String turmaId) {
        String titulo = etTitulo.getText().toString();
        String descricao = etDescricao.getText().toString();
        String data = etData.getText().toString();
        String local = etLocal.getText().toString();

        // Verifica se todos os campos foram preenchidos
        if (!titulo.isEmpty() && !descricao.isEmpty() && !data.isEmpty() && !local.isEmpty()) {
            // Criando um evento com os dados inseridos
            Evento evento = new Evento(titulo, descricao, data, local);

            // Adiciona o evento na subcoleção "eventos" da turma no Firestore
            db.collection("Turmas")
                    .document(turmaId)
                    .collection("eventos")  // Subcoleção "eventos" dentro da turma
                    .add(evento)  // Adiciona o evento
                    .addOnSuccessListener(documentReference -> {
                        Log.d("addEvento", "Evento adicionado com sucesso.");
                        // Resetar os campos após adicionar o evento
                        etTitulo.setText("");
                        etDescricao.setText("");
                        etData.setText("");
                        etLocal.setText("");
                        Toast.makeText(addEvento.this, "Evento adicionado com sucesso!", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Log.w("addEvento", "Erro ao adicionar evento.", e);
                        Toast.makeText(addEvento.this, "Erro ao adicionar evento.", Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(addEvento.this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
        }
    }
}
