package pe.edu.uni.valegrei.serverlessapp.api;

import java.util.List;

import pe.edu.uni.valegrei.serverlessapp.Articulo;

public class RespLista {
    private List<Articulo> articulos;

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }
}
