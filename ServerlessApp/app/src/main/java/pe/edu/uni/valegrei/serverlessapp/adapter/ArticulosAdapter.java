package pe.edu.uni.valegrei.serverlessapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import pe.edu.uni.valegrei.serverlessapp.Articulo;
import pe.edu.uni.valegrei.serverlessapp.databinding.ItemArticuloBinding;

public class ArticulosAdapter extends RecyclerView.Adapter<ArticulosAdapter.ViewHolder> {
    private Context context;
    private List<Articulo> articulos;
    private OnItemInteraction listener;

    public ArticulosAdapter(Context context, OnItemInteraction listener) {
        this.listener = listener;
        this.context = context;
    }

    public void setListener(OnItemInteraction listener) {
        this.listener = listener;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemArticuloBinding binding = ItemArticuloBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Articulo articulo = articulos.get(position);
        holder.binding.tvNombre.setText(articulo.getNombre());
        holder.binding.tvFecha.setText(articulo.getFecha());
        holder.binding.tvDescripcion.setText(articulo.getDescripcion());
        Glide.with(context)
                .load(articulo.getFotoUrl())
                .into(holder.binding.imgFoto);
        holder.binding.btnEditar.setOnClickListener(v -> {
            if (listener != null) {
                listener.editar(articulo);
            }
        });
        holder.binding.btnEliminar.setOnClickListener(v -> {
            if (listener != null) {
                listener.eliminar(articulo);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (articulos == null)
            return 0;
        return articulos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemArticuloBinding binding;

        public ViewHolder(ItemArticuloBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemInteraction {
        void editar(Articulo articulo);

        void eliminar(Articulo articulo);
    }
}
