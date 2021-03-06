package com.castraining.castraining.fragment;


import static com.castraining.castraining.MainActivity.URL_BASE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.castraining.castraining.adapter.listadoCursosAdapter;
import com.castraining.castraining.api.Interface.ApiCasTraining;
import com.castraining.castraining.api.cursos.CursosResponse;
import com.castraining.castraining.api.cursos.RcvListadoDatos;
import com.castraining.castraining.api.cursos.YoastHeadJson;
import com.castraining.castraining.databinding.FragmentListadoCursosBinding;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link listadoCursos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class listadoCursos extends Fragment implements SearchView.OnQueryTextListener{

    private listadoCursosAdapter adapterListadoCurso = null;
    private FragmentListadoCursosBinding listadoCursosBinding;

    //Retrofit
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;
    private Retrofit retrofit;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";


    // TODO: Rename and change types of parameters
    private ArrayList<RcvListadoDatos> mParam1;

    public listadoCursos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment listadoCursos.
     */
    // TODO: Rename and change types and number of parameters
    public static listadoCursos newInstance(ArrayList<RcvListadoDatos> param1) {
        listadoCursos fragment = new listadoCursos();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getParcelable("listadoCursos");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        listadoCursosBinding = FragmentListadoCursosBinding. inflate(getLayoutInflater());

        listadoCursosBinding.serchView.setOnQueryTextListener(this);

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
                ArrayList<RcvListadoDatos> listaCursos = new ArrayList<RcvListadoDatos>();
                for (int i = 0; i<response.body().size();i++){
                    CursosResponse cursosResponse = cursosResponses.get(i);
                    String title = cursosResponse.getTitle().getRendered();
                    String descripcion = cursosResponse.getYoastHeadjson().getDescripcion();
                    int id = cursosResponse.getId();
                    String skuCurso = cursosResponse.getAcfCursos().getSkuCurso();
                    String logo = img(cursosResponse.getYoastHeadjson());
                    //String logo = "imagen 1";
                    listaCursos.add(new RcvListadoDatos(title, id, descripcion, logo, skuCurso));
                }
                adapterListadoCurso = new listadoCursosAdapter(listaCursos);
                listadoCursosBinding.rcvListadoCurso.setLayoutManager(new LinearLayoutManager(getContext()));
                listadoCursosBinding.rcvListadoCurso.setAdapter(adapterListadoCurso);

            }

            @Override
            public void onFailure(Call<List<CursosResponse>> call, Throwable t) {
                Log.d("Call getCursos:", t.getMessage());
            }
        });
        //return inflater.inflate(R.layout.fragment_listado_cursos, container, false);
        return listadoCursosBinding.getRoot();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapterListadoCurso.filtrado(s);
        return false;
    }

    public String img(YoastHeadJson yoastHeadJson){
        String imageApi = SelectImg(yoastHeadJson);
        String imagenCurso = "";
        if (imageApi == "imagenOk"){
            imagenCurso = yoastHeadJson.getOg_image().get(0).getUrl();
            /*Object objeto = yoastHeadJson.getOg_image();
            LinkedTreeMap<Object, Object> arbolYoastHead = (LinkedTreeMap) objeto;
            imagenCurso = arbolYoastHead.get("url").toString();*/
        }else imagenCurso = "R.drawable.ic_launcher";
        return imagenCurso;
    }
    public String SelectImg(YoastHeadJson yoastHeadJson){
        //Metodo para seleccionar si el campo og_img viene
        String select = "";
        if (yoastHeadJson.getOg_image() == null) {
            //Si el campo imagen no viene
            select = "noImagen";
        }else { select = "imagenOk"; } //Campo imagen viene con url
        return select;
    }
}