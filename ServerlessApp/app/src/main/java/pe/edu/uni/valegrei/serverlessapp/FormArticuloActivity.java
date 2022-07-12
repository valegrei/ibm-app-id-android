package pe.edu.uni.valegrei.serverlessapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import pe.edu.uni.valegrei.serverlessapp.api.Respuesta;
import pe.edu.uni.valegrei.serverlessapp.api.RestApi;
import pe.edu.uni.valegrei.serverlessapp.databinding.ActivityFormArticuloBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormArticuloActivity extends AppCompatActivity {
    public static final String ARTICULO = "ARTICULO";

    private ActivityFormArticuloBinding binding;
    private Articulo articulo = null;
    private String token;
    private boolean esNuevo = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormArticuloBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent().getExtras() != null) {
            articulo = getIntent().getExtras().getParcelable(ARTICULO);
            esNuevo = false;
        }
        token = new TokensManager(this).getAccessToken();

        if (!esNuevo) {
            setTitle("Modificar Artículo");
            binding.edtNombre.append(articulo.getNombre());
            binding.edtDescripcion.append(articulo.getDescripcion());
            binding.edtFotoUrl.append(articulo.getFotoUrl());
        }else{
            setTitle("Nuevo Artículo");
        }
        binding.btnGuardar.setOnClickListener(v -> guardarArticulo());
    }

    private void guardarArticulo() {
        if (articulo == null) articulo = new Articulo();
        articulo.setNombre(binding.edtNombre.getText().toString());
        articulo.setDescripcion(binding.edtDescripcion.getText().toString());
        articulo.setFotoUrl(binding.edtFotoUrl.getText().toString());

        if (esNuevo) {
            RestApi.getInstance(this).agregarArticulo(articulo, token, callback);
        } else {
            RestApi.getInstance(this).editarArticulo(articulo, token, callback);
        }
    }

    Callback<Respuesta> callback = new Callback<Respuesta>() {
        @Override
        public void onResponse(@NonNull Call<Respuesta> call, @NonNull Response<Respuesta> response) {
            if (response.isSuccessful()) {
                salir();
            }
        }

        @Override
        public void onFailure(@NonNull Call<Respuesta> call, @NonNull Throwable t) {
            salir();
        }
    };

    private void salir() {
        finish();
    }

}