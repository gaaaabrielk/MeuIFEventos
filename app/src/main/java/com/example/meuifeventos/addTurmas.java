package com.example.meuifeventos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class addTurmas extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TurmaAdapter turmaAdapter;
    private List<Turma> listaDeTurmas;
    private EditText editTextTurmaNome;
    private Button buttonAdicionarTurma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_turmas);

        // Inicializa os componentes da interface
        recyclerView = findViewById(R.id.recyclerViewTurmas);
        editTextTurmaNome = findViewById(R.id.editTextTurmaNome);
        buttonAdicionarTurma = findViewById(R.id.buttonAdicionarTurma);

        // Configura a RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializa a lista de turmas
        listaDeTurmas = new ArrayList<>();

        // Cria o adapter e configura a RecyclerView
        turmaAdapter = new TurmaAdapter(listaDeTurmas);
        recyclerView.setAdapter(turmaAdapter);

        // Adicionar turmas com IDs predefinidos (por exemplo, "T001", "T002", "T003")
        adicionarTurma("T001", "Turma A", new ArrayList<String>());
        adicionarTurma("T002", "Turma B", new ArrayList<String>());
        adicionarTurma("T003", "Turma C", new ArrayList<String>());

        // Configura o clique no botão para adicionar uma nova turma
        buttonAdicionarTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Captura o nome da turma inserido pelo usuário
                String nomeTurma = editTextTurmaNome.getText().toString().trim();

                // Verifica se o nome não está vazio
                if (!nomeTurma.isEmpty()) {
                    // Gerar um ID único para a nova turma (aqui você pode definir a lógica para gerar IDs específicos)
                    String novoId = "T" + (listaDeTurmas.size() + 1);  // Exemplo simples para gerar IDs

                    // Cria a nova turma com o ID gerado e o nome inserido pelo usuário
                    adicionarTurma(novoId, nomeTurma, new ArrayList<String>());

                    // Notifica o adapter para atualizar a lista na RecyclerView
                    turmaAdapter.notifyItemInserted(listaDeTurmas.size() - 1);

                    // Limpa o campo de texto
                    editTextTurmaNome.setText("");
                }
            }
        });
    }

    // Método para adicionar uma nova turma com ID específico
    private void adicionarTurma(String id, String nome, List<String> alunos) {
        Turma novaTurma = new Turma(id, nome, alunos);
        listaDeTurmas.add(novaTurma);
    }
}
