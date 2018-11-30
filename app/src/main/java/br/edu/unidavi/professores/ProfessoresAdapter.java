package br.edu.unidavi.professores;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProfessoresAdapter extends RecyclerView.Adapter<ProfessoresAdapter.ViewHolder> {

    public final ProfessoresAdapter.OnProfessorClickListener listener;
    private List<Professor> professores = new ArrayList<>();

    public ProfessoresAdapter(ProfessoresAdapter.OnProfessorClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProfessoresAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());


        return new ProfessoresAdapter.ViewHolder(inflater.inflate(
                android.R.layout.simple_list_item_1,
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessoresAdapter.ViewHolder viewHolder, int i) {

        final Professor professor = professores.get(i);

        viewHolder.nome.setText(professor.getNome());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(professor);
            }
        });


    }

    @Override
    public int getItemCount() {
        return professores.size();
    }

    public void setup(List<Professor> professores) {
        this.professores.clear();
        this.professores.addAll(professores);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView nome;
        TextView email;
        TextView disciplina;
        TextView foto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(android.R.id.text1);
            email = itemView.findViewById(android.R.id.text1);
            disciplina = itemView.findViewById(android.R.id.text1);
            foto = itemView.findViewById(android.R.id.text1);
        }
    }

    interface OnProfessorClickListener {
        void onClick(Professor professor);
    }
}
