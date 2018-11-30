package br.edu.unidavi.professores;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class ListaActivity extends AppCompatActivity {

    private ProfessoresAdapter adapter = new ProfessoresAdapter(new ProfessoresAdapter.OnProfessorClickListener() {
        @Override
        public void onClick(Professor professor) {
            //Toast.makeText(getApplicationContext(),task.getTitle(),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),DetalheActivity.class);

            intent.putExtra("professor", professor);
            startActivity(intent);
        }
    });
    private DatabaseHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        helper = new DatabaseHelper(this);

        RecyclerView taskList =  findViewById(R.id.professor_list);


        taskList.setLayoutManager(new LinearLayoutManager(this));
        taskList.setAdapter(adapter);
        FloatingActionButton buttonCreate = findViewById(R.id.button_create);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), AdicionaActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Professor> professores = helper.fetchProfessores();
        adapter.setup(professores);
    }
}
