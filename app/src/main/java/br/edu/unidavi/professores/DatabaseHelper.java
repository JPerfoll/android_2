package br.edu.unidavi.professores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Professores.db";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE professores(" +
                "_id integer primary key autoincrement," +
                "nome text," +
                "email text," +
                "disciplina text," +
                "foto text" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createTask(String title) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("title", title);
        values.put("done", false);
        db.insert("tasks", null, values);


    }

    public void createProfessor(String nome, String email, String disciplina, String foto) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("nome", nome);
        values.put("email", email);
        values.put("disciplina", disciplina);
        values.put("foto", foto);

        db.insert("professores", null, values);

    }

    public List<Professor> fetchProfessores(){

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query("professores", new String[]{"_id", "nome", "email", "disciplina", "foto"},null,null,null, null, null);{
            cursor.moveToFirst();
            List<Professor> professores = new ArrayList<>();
            while (!cursor.isAfterLast()){
                Professor professor = new Professor(
                        cursor.getInt(cursor.getColumnIndex("_id")),
                        cursor.getString(cursor.getColumnIndex("nome")),
                        cursor.getString(cursor.getColumnIndex("email")),
                        cursor.getString(cursor.getColumnIndex("disciplina")),
                        cursor.getString(cursor.getColumnIndex("foto"))
                );

                professores.add(professor);
                cursor.moveToNext();
            }

            cursor.close();
            return professores;
        }
    }


    public void deleteProfessor(Professor professor){
        SQLiteDatabase db = getWritableDatabase();

        db.delete("professores", "_id=" + professor.getId(), null);
    }
}
