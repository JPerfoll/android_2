package br.edu.unidavi.professores;

import android.os.Parcel;
import android.os.Parcelable;

public class Professor implements Parcelable {

    private final int id;
    private final String nome;
    private final String email;
    private final String disciplina;
    private final String foto;


    public Professor(int id, String nome, String email, String disciplina, String foto) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.disciplina = disciplina;
        this.foto = foto;
    }

    protected Professor(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        email = in.readString();
        disciplina = in.readString();
        foto = in.readString();
    }

    public static final Creator<Professor> CREATOR = new Creator<Professor>() {
        @Override
        public Professor createFromParcel(Parcel in) {
            return new Professor(in);
        }

        @Override
        public Professor[] newArray(int size) {
            return new Professor[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public String getFoto() {
        return foto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
        dest.writeString(email);
        dest.writeString(disciplina);
        dest.writeString(foto);
    }

}