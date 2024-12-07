package com.example.meuifeventos;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.Timestamp;
import java.util.Calendar;

public class CadastroItemsActivity extends AppCompatActivity {

    private EditText editTextNome, editTextDescricao, editTextLocal;
    private TextView textViewDataSelecionada;
    private Button buttonSelecionarData, buttonSalvar;
    private String dataSelecionada;
    private Timestamp dataEmprestimo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_items);

        editTextNome = findViewById(R.id.editTextNome);
        editTextDescricao = findViewById(R.id.editTextDescricao);
        editTextLocal = findViewById(R.id.editTextLocal);
        textViewDataSelecionada = findViewById(R.id.textViewDataSelecionada);
        buttonSelecionarData = findViewById(R.id.buttonSelecionarData);
        buttonSalvar = findViewById(R.id.buttonSalvar);

        buttonSelecionarData.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
                dataSelecionada = year1 + "-" + (month1 + 1) + "-" + dayOfMonth;
                textViewDataSelecionada.setText(dataSelecionada);
            }, year, month, day).show();
        });

        buttonSalvar.setOnClickListener(v -> salvarItem());
    }

    private void salvarItem() {
        String nome = editTextNome.getText().toString();
        String descricao = editTextDescricao.getText().toString();
        String local = editTextLocal.getText().toString();

        if (nome.isEmpty() || descricao.isEmpty() || local.isEmpty() || dataSelecionada == null) {
            Toast.makeText(this, "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Converter dataSelecionada para Timestamp (data de empréstimo)
        Calendar calendar = Calendar.getInstance();
        int year = Integer.parseInt(dataSelecionada.split("-")[0]);
        int month = Integer.parseInt(dataSelecionada.split("-")[1]) - 1;  // Corrigir mês (0-11)
        int day = Integer.parseInt(dataSelecionada.split("-")[2]);
        calendar.set(year, month, day, 0, 0, 0);

        dataEmprestimo = new Timestamp(calendar.getTime());

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Items item = new Items();
        item.setNome(nome);
        item.setDescricao(descricao);
        item.setLocalEncontrado(local);
        item.setDataEmprestimo(dataEmprestimo); // Salvando data de empréstimo
        item.setStatus(false);  // Definindo status como booleano

        // Salvando item no Firestore
        db.collection("achados_perdidos").add(item)
                .addOnSuccessListener(documentReference -> {
                    // Adiciona o ID gerado ao objeto item
                    String id = documentReference.getId();
                    item.setId(id);

                    // Atualiza o documento com o ID (caso necessário)
                    db.collection("achados_perdidos")
                            .document(id)
                            .set(item)
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(this, "Item adicionado com ID: " + id, Toast.LENGTH_SHORT).show();
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(this, "Erro ao atualizar ID do item.", Toast.LENGTH_SHORT).show();
                            });
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Erro ao salvar o item.", Toast.LENGTH_SHORT).show();
                });
    }


}
