package pe.edu.uni.valegrei.serverlessapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Articulo implements Parcelable {

    private String id;
    private String rev;
    private String fecha;
    private String nombre;
    private String descripcion;
    private String fotoUrl;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRev() {
        return rev;
    }

    public void setRev(String rev) {
        this.rev = rev;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.rev);
        dest.writeString(this.nombre);
        dest.writeString(this.descripcion);
        dest.writeString(this.fotoUrl);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readString();
        this.rev = source.readString();
        this.nombre = source.readString();
        this.descripcion = source.readString();
        this.fotoUrl = source.readString();
    }

    public Articulo() {
    }

    protected Articulo(Parcel in) {
        this.id = in.readString();
        this.rev = in.readString();
        this.nombre = in.readString();
        this.descripcion = in.readString();
        this.fotoUrl = in.readString();
    }

    public static final Parcelable.Creator<Articulo> CREATOR = new Parcelable.Creator<Articulo>() {
        @Override
        public Articulo createFromParcel(Parcel source) {
            return new Articulo(source);
        }

        @Override
        public Articulo[] newArray(int size) {
            return new Articulo[size];
        }
    };
}
