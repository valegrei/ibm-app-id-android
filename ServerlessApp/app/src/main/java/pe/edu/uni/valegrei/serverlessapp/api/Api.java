package pe.edu.uni.valegrei.serverlessapp.api;

import pe.edu.uni.valegrei.serverlessapp.Articulo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Api {
    @GET("articulos")
    Call<RespLista> listarArticulos(@Header("Authorization") String token);

    @POST("articulos")
    Call<Respuesta> agregarArticulo(@Body Articulo articulo, @Header("Authorization") String token);

    @PUT("articulos")
    Call<Respuesta> editarArticulo(@Body Articulo articulo, @Header("Authorization") String token);

    @HTTP(method = "DELETE", path = "articulos", hasBody = true)
    Call<Respuesta> eliminarArticulo(@Body Articulo articulo, @Header("Authorization") String token);
}
