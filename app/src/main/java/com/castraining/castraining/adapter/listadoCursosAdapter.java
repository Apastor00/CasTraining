package com.castraining.castraining.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.castraining.castraining.R;
import com.castraining.castraining.api.cursos.RcvListadoDatos;
import com.castraining.castraining.databinding.RecyclerItemCursoBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class listadoCursosAdapter extends RecyclerView.Adapter<listadoCursosAdapter.ListadoAdapterHolder> {

    private List<RcvListadoDatos> datosCursos;
    private List<RcvListadoDatos> datosCursosOriginal;

    public listadoCursosAdapter(List<RcvListadoDatos> datosCursos) {
        this.datosCursos = datosCursos;
        datosCursosOriginal = new ArrayList<>();
        datosCursosOriginal.addAll(datosCursos);
    }

    @NonNull
    @Override
    public ListadoAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ListadoAdapterHolder(RecyclerItemCursoBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListadoAdapterHolder holder, int position) {
        RcvListadoDatos datoString = datosCursos.get(position);
        String tit = datoString.getTitle();
        int id = datoString.getId();
        String des = datoString.getDescripcion();
        String img = datoString.getLogo();
        holder.binding.rcvTxvTituloCurso.setText(tit);
        holder.binding.rcvTxvDescripcionCurso.setText(des);
        if (img =="R.drawable.ic_launcher") {
            Picasso.get()
                    .load(R.drawable.ic_launcher)
                    .resize(100, 100)
                    .into(holder.binding.rcvImgCurso);
        }else {
            Picasso.get()
                    .load(img)
                    .resize(100, 100)
                    .error(R.drawable.ic_launcher)
                    .into(holder.binding.rcvImgCurso);
        }
        /*holder.binding.rcvItemCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CursosDetalles.class);
                intent.putExtra("id", id);
                intent.putExtra("title", tit);
                view.getContext().startActivity(intent);
            }
        });*/
    }
    public void filtrado (String txtFiltrado){
        int longitud = txtFiltrado.length();
        if (longitud==0){
            datosCursos.clear();
            datosCursos.addAll(datosCursosOriginal);
        }
        else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<RcvListadoDatos> filtroTitulo = datosCursos.stream()
                        .filter(rcvListadoDatos -> rcvListadoDatos.getTitle().toLowerCase()
                                 .contains(txtFiltrado.toLowerCase()) || rcvListadoDatos.getSkuCurso().toLowerCase()
                                .contains(txtFiltrado.toLowerCase()))
                        .collect(Collectors.toList());
                datosCursos.clear();
                datosCursos.addAll(filtroTitulo);

            }else {
                for (RcvListadoDatos rcv: datosCursos){
                    if (rcv.getTitle().toLowerCase().contains(txtFiltrado.toLowerCase()) || rcv.getSkuCurso()
                            .toLowerCase().contains(txtFiltrado.toLowerCase())){
                        datosCursos.add(rcv);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datosCursos.size();
    }

    public void setData(List<RcvListadoDatos> datosCurso) {
        this.datosCursos = datosCurso;
        notifyDataSetChanged();
    }

    public class ListadoAdapterHolder extends RecyclerView.ViewHolder {

        RecyclerItemCursoBinding binding;

        public ListadoAdapterHolder(@NonNull RecyclerItemCursoBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }
}
