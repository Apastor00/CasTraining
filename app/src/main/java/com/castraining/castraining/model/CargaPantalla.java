package com.castraining.castraining.model;

import static com.castraining.castraining.view.Cursos.URL_BASE;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.castraining.castraining.adapter.listadoCursosAdapter;
import com.castraining.castraining.api.Interface.ApiCasTraining;
import com.castraining.castraining.api.cursos.CursosResponse;
import com.castraining.castraining.api.cursos.RcvListadoDatos;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CargaPantalla extends AsyncTask <Context, Boolean, ArrayList<RcvListadoDatos>> {

    //Retrofit
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;
    private Retrofit retrofit;
    //Inicializamos ArrayList respuesta
    ArrayList<RcvListadoDatos> listaCursos = null;

    @Override
    protected void onPostExecute(ArrayList<RcvListadoDatos> rcvListadoDatos) {
        super.onPostExecute(rcvListadoDatos);
    }

    @Override
    protected void onProgressUpdate(Boolean... values) {
        super.onProgressUpdate(values);

    }

    @Override
    protected ArrayList<RcvListadoDatos> doInBackground(Context... contexts) {
        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();
        ApiCasTraining apiCasTraining = retrofit.create(ApiCasTraining.class);
        Call<List<CursosResponse>> call = apiCasTraining.getCursos();
        call.enqueue(new Callback<List<CursosResponse>>() {

            @Override
            public void onResponse(Call<List<CursosResponse>> call, Response<List<CursosResponse>> response) {
                List<CursosResponse> cursosResponses= response.body();
                //ArrayList<RcvListadoDatos> listaCursos = new ArrayList<RcvListadoDatos>();
                for (int i = 0; i<response.body().size();i++){
                    CursosResponse cursosResponse = cursosResponses.get(i);
                    String title = cursosResponse.getTitle().getRendered();
                    String descripcion = cursosResponse.getYoastHeadjson().getDescripcion();
                    int id = cursosResponse.getId();
                    //String logo = cursosResponse.getYoastHeadjson().getOg_image().get(0).getUrl();
                    String logo = "imagen 1";
                    listaCursos.add(new RcvListadoDatos(title, id, descripcion, logo));
                }
            }
            @Override
            public void onFailure(Call<List<CursosResponse>> call, Throwable t) {
                Log.d("Call getCursos:", t.getMessage());
            }
        });
        return listaCursos;
    }
}
