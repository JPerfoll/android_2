package br.edu.unidavi.professores;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class DetalheActivity extends AppCompatActivity {

    private Professor professor;
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);
        helper = new DatabaseHelper(this);

        professor = getIntent().getParcelableExtra("professor");

        setTitle("Consulta de professor");

        EditText inpuNome = findViewById(R.id.input_nome_professor_con);
        EditText inpuEmail = findViewById(R.id.input_email_professor_con);
        EditText inpuDisciplina = findViewById(R.id.input_disciplina_professor_con);
        //ImageView foto = findViewById(R.id.foto_professor);

        inpuNome.setText(professor.getNome());
        inpuEmail.setText(professor.getEmail());
        inpuDisciplina.setText(professor.getDisciplina());

        Button buttonDelete = findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.deleteProfessor(professor);
                finish();
            }
        });
    }
}
