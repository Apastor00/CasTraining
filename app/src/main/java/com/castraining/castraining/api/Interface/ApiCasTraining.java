package com.castraining.castraining.api.Interface;

import com.castraining.castraining.api.cursos.CursosResponse;
import com.castraining.castraining.api.cursos.DetallesCurso;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiCasTraining {

    //Definimos los EndPoint
    String URL_CONVOCTATORIA = "convocatoria";
    String URL_CURSOS = "cursos";
    String URL_CONVOCATORIA_ID = "convocatoria/{id}";
    String URL_CURSOS_ID = "cursos/{id}";

    /** Esta parte es para la implentación de la llamada a la api de convocatoria
     * @GET(URL_CONVOCTATORIA) //Listado de las comvocatorias
    Call<List<ConvocatoriaResponse>> getConvocatoria ();

     @GET(URL_CONVOCATORIA_ID)
     Call<List<ConvocatoriaResponse>> getConvocatoriaid (@Path("id")int id);
     */

    @GET(URL_CURSOS) //Listado de los cursos
    Call<List<CursosResponse>> getCursos();

    @GET(URL_CURSOS_ID)
    Call<DetallesCurso> getCursosid (@Path("id") int id);
}
