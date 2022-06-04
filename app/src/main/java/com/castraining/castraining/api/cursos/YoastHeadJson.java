package com.castraining.castraining.api.cursos;

import android.media.tv.TvContract;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class YoastHeadJson {

    @SerializedName("description")
    private String descripcion;
    private List<imageUrl> og_image;

    public List<imageUrl> getOg_image() {
        return og_image;
    }

    public void setOg_image(List<imageUrl> og_image) {
        this.og_image = og_image;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
