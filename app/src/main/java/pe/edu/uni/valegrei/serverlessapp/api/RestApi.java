package pe.edu.uni.valegrei.serverlessapp.api;

import android.content.Context;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pe.edu.uni.valegrei.serverlessapp.Articulo;
import pe.edu.uni.valegrei.serverlessapp.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApi {

    private static RestApi restApi;
    private final Api api;

    public static RestApi getInstance(Context context) {
        if (restApi == null) {
            restApi = new RestApi(context);
        }
        return restApi;
    }

    private RestApi(Context context) {

        String apiUrl = context.getString(R.string.api_url);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        GsonBuilder gsonBuilder = new GsonBuilder();
        //gsonBuilder.excludeFieldsWithoutExposeAnnotation();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .build();

        api = retrofit.create(Api.class);
    }

    public void listarArticulos(String token, Callback<RespLista> callback) {
        Call<RespLista> resp = api.listarArticulos(token);
        resp.enqueue(callback);
    }

    public void agregarArticulo(Articulo articulo, String token, Callback<Respuesta> callback) {
        Call<Respuesta> resp = api.agregarArticulo(articulo, token);
        resp.enqueue(callback);
    }

    public void editarArticulo(Articulo articulo, String token, Callback<Respuesta> callback) {
        Call<Respuesta> resp = api.editarArticulo(articulo, token);
        resp.enqueue(callback);
    }

    public void eliminarArticulos(Articulo articulo, String token, Callback<Respuesta> callback) {
        Call<Respuesta> resp = api.eliminarArticulo(articulo, token);
        resp.enqueue(callback);
    }
}
