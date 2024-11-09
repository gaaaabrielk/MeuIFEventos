package com.example.meuifeventos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;

import java.io.FileReader;


public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            FirebaseApp.initializeApp(this);


            Button btnProfessor = findViewById(R.id.btnProfessor);
            Button btnAluno = findViewById(R.id.btnAluno);

            btnProfessor.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, menu_activity.class);
                    startActivity(intent);

                }
            });

            btnAluno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), ListaTurmasActivity.class);
                    intent.putExtra("TURMA_ID", "TURMA");
                    startActivity(intent);



                }
            });





    }
}




