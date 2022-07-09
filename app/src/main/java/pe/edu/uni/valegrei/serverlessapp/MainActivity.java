package pe.edu.uni.valegrei.serverlessapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import pe.edu.uni.valegrei.serverlessapp.adapter.ArticulosAdapter;
import pe.edu.uni.valegrei.serverlessapp.api.RespLista;
import pe.edu.uni.valegrei.serverlessapp.api.Respuesta;
import pe.edu.uni.valegrei.serverlessapp.api.RestApi;
import pe.edu.uni.valegrei.serverlessapp.databinding.ActivityMainBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ArticulosAdapter.OnItemInteraction {
    private ArticulosAdapter adapter;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new ArticulosAdapter(this, this);
        binding.rvArticulos.setLayoutManager(new LinearLayoutManager(this));
        binding.rvArticulos.setAdapter(adapter);

        binding.btnNuevo.setOnClickListener(v -> nuevo());

        token = "";

    }

    @Override
    protected void onResume() {
        super.onResume();

        cargarArticulos();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void cargarArticulos() {
        RestApi.getInstance(this).listarArticulos(token, new Callback<RespLista>() {

            @Override
            public void onResponse(@NonNull Call<RespLista> call, @NonNull Response<RespLista> response) {
                if (response.isSuccessful()) {
                    RespLista respLista = response.body();
                    if (respLista != null) {
                        //Toast.makeText(MainActivity.this, "Hola, length: " + respLista.getArticulos().size(), Toast.LENGTH_SHORT).show();
                        actualizarLista(respLista.getArticulos());
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<RespLista> call, @NonNull Throwable t) {

            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void actualizarLista(List<Articulo> articulos) {
        adapter.setArticulos(articulos);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void editar(Articulo articulo) {
        Intent intent = new Intent(this, FormArticuloActivity.class);
        intent.putExtra(FormArticuloActivity.ARTICULO, articulo);
        startActivity(intent);
    }

    @Override
    public void eliminar(Articulo articulo) {
        RestApi.getInstance(this).eliminarArticulos(articulo, token, new Callback<Respuesta>() {
            @Override
            public void onResponse(@NonNull Call<Respuesta> call, @NonNull Response<Respuesta> response) {
                if (response.isSuccessful()) {
                    cargarArticulos();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Respuesta> call, @NonNull Throwable t) {

            }
        });
    }

    private void nuevo() {
        Intent intent = new Intent(this, FormArticuloActivity.class);
        startActivity(intent);
    }
}