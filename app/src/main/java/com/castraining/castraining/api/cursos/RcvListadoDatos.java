package com.castraining.castraining.api.cursos;

import android.os.Parcelable;

public class RcvListadoDatos {
    /** Clase que nos sirve para obtener el título y el id del curso */

    private String title;
    private int id;
    private String descripcion;
    private String logo;
    private String skuCurso;

    public RcvListadoDatos (){}

    public RcvListadoDatos(String title, int id, String descripcion, String logo, String skuCurso) {
        this.title = title;
        this.id = id;
        this.descripcion = descripcion;
        this.logo = logo;
        this.skuCurso = skuCurso;
    }

    public String getSkuCurso() {
        return skuCurso;
    }

    public void setSkuCurso(String skuCurso) {
        this.skuCurso = skuCurso;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
