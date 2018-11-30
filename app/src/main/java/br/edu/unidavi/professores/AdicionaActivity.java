package br.edu.unidavi.professores;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class AdicionaActivity extends AppCompatActivity {

    private DatabaseHelper helper;
    private File file;
    private ImageView imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona);

        setTitle("Cadastro de professor");

        helper = new DatabaseHelper(this);

        Button buttonSave = findViewById(R.id.button_save);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText inputNome = findViewById(R.id.input_nome_professor);
                EditText inputEmail = findViewById(R.id.input_email_professor);
                EditText inputDisciplina = findViewById(R.id.input_disciplina_professor);
                ImageView imgFoto = findViewById(R.id.img_foto_cad);

                String valueNome =  inputNome.getText().toString();
                String valueEmail =  inputEmail.getText().toString();
                String valueDisciplina =  inputDisciplina.getText().toString();
                String valueFoto =  imgFoto.toString();

                if (!valueNome.isEmpty()){
                    helper.createProfessor(valueNome,valueEmail,valueDisciplina,valueFoto);
                    finish();
                }

            }
        });

        if (savedInstanceState != null) {
            file = new File(savedInstanceState.getString("file"));
        }

        imagem = findViewById(R.id.img_foto_cad);
        Button buttonFoto = findViewById(R.id.button_foto);

        buttonFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (harCameraPermission()){
                    file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "photo.jpg");
                    Uri outputDir = FileProvider.getUriForFile(AdicionaActivity.this, BuildConfig.APPLICATION_ID, file);

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, outputDir);

                    startActivityForResult(
                            intent,
                            1_000);

                    return;
                };

//                // verificar se não marcou a opção não mostrar novamente.
//                if (ActivityCompat.shouldShowRequestPermissionRationale(AdicionaActivity.this, Manifest.permission.CAMERA)) {
//
//                } else { // verificar se marcou a opção não mostrar novamente.
//                    new AlertDialog.Builder(AdicionaActivity.this)
//                            .setPositiveButton("OK", null)
//                            .show();
//                }

                requesPermission();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1_000) {
            if (data != null && data.hasExtra("data")) {
                Bitmap thumbnail = data.getParcelableExtra("data");
                imagem.setImageBitmap(thumbnail);
            } else {
                int width = 300;
                int height = 300;

                BitmapFactory.Options factoryOptions = new BitmapFactory.Options();
                factoryOptions.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(file.getPath(), factoryOptions);

                int imageWidth = factoryOptions.outWidth;
                int imageHeight = factoryOptions.outHeight;

                // Verificar o quanto precisamos escalar a imagem
                int scaleFactor = Math.min(imageWidth / width,
                        imageHeight / height);

                factoryOptions.inJustDecodeBounds = false;
                factoryOptions.inSampleSize = scaleFactor;

                Bitmap image = BitmapFactory.decodeFile(file.getPath(),
                        factoryOptions);
                imagem.setImageBitmap(image);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if ((requestCode == 1000) && (permissions[0].equals(Manifest.permission.CAMERA))){
            if (grantResults[0] == PackageManager.PERMISSION_DENIED){
                Toast.makeText(this, "Permissão Câmera Negada", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permissão concedida! Clique novamente.", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void requesPermission(){
        ActivityCompat.requestPermissions(AdicionaActivity.this,
                new String[]{Manifest.permission.CAMERA},
                1000);
    }

    boolean harCameraPermission(){
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }
}
