package br.edu.unidavi.professores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsuario;
    private EditText edtSenha;
    private final List<Usuario> usuarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsuario = findViewById(R.id.edtUsuario);
        edtSenha = findViewById(R.id.edtSenha);
        Button btnEntrar = findViewById(R.id.btnEntrar);

        usuarios.add(new Usuario("jeanperfoll@gmail.com", "123"));
        usuarios.add(new Usuario("giotone@gmail.com", "123"));
        usuarios.add(new Usuario("rafaeltoledo@gmail.com", "123"));

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean bLogou = false;
                if ((!edtUsuario.getText().toString().trim().equals("")) && (!edtSenha.getText().toString().trim().equals(""))) {
                    for (Usuario u : usuarios) {
                        if ((u.getUsuario().equals(edtUsuario.getText().toString())) && (u.getSenha().equals(edtSenha.getText().toString()))) {
                            bLogou = true;
                            startActivity(new Intent(getApplicationContext(), ListaActivity.class));
                        }
                    }

                    if (!bLogou) {
                        Toast.makeText(LoginActivity.this, "Usuário ou senha incorretos!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Informe o usuário e senha!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
