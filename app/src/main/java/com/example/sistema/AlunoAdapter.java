package com.example.sistema;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sistema.modelo.Usuario;

import java.util.List;

public class AlunoAdapter extends RecyclerView.Adapter {

    List<Usuario> usuarios;
    private ClickListener mclickListener;

    public AlunoAdapter(List<Usuario> usuarios, ClickListener clickListener) {
        this.usuarios = usuarios;
        this.mclickListener = clickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_aluno,parent,false);
        ViewHolderClass vhClass = new ViewHolderClass(view, mclickListener);
        return vhClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass vhClass = (ViewHolderClass) holder;
        Usuario usuario = usuarios.get(position);
        vhClass.tvNome.setText(usuario.getNome());
        vhClass.tvEmail.setText(usuario.getEmail());
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvNome;
        TextView tvEmail;
        ClickListener clickListener;

        public ViewHolderClass(@NonNull View itemView, ClickListener clickListener) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvNome);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            this.clickListener = clickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getBindingAdapterPosition(), v);
        }
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}
