package com.castraining.castraining.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.castraining.castraining.adapter.listadoCursosAdapter;
import com.castraining.castraining.api.Interface.ApiCasTraining;
import com.castraining.castraining.api.cursos.CursosResponse;
import com.castraining.castraining.api.cursos.RcvListadoDatos;
import com.castraining.castraining.databinding.ActivityCursosBinding;
import com.castraining.castraining.databinding.FragmentListadoCursosBinding;
import com.castraining.castraining.fragment.listadoCursos;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cursos extends AppCompatActivity {

    public static final String URL_BASE = "https://cas-training.com/wp-json/wp/v2/";

    //Binding mediante vistas
    ActivityCursosBinding cursosBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cursosBinding = ActivityCursosBinding.inflate(getLayoutInflater());
        setContentView(cursosBinding.getRoot());

        listadoCursos listadoCursosFragment = new listadoCursos();
        getSupportFragmentManager().beginTransaction().
                replace(cursosBinding.fragmentListadoCurso.getId(), listadoCursosFragment).commit();
    }
}