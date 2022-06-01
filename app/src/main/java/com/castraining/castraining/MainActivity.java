package com.castraining.castraining;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.castraining.castraining.databinding.ActivityMainBinding;
import com.castraining.castraining.view.Cursos;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityMainBinding mainBinding;
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        mainBinding.btnConvocatoria.setOnClickListener(this);
        mainBinding.btnCursos.setOnClickListener(this);
        mainBinding.btnExamenes.setOnClickListener(this);
        mainBinding.btnMensaje.setOnClickListener(this);
        mainBinding.btnInicioSesion.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_convocatoria:
                /*Intent intent = new Intent(this, Convocatoria.class);
                startActivity(intent);*/
                break;
            case R.id.btn_cursos:
                Intent intentCursos = new Intent(this, Cursos.class);
                startActivity(intentCursos);
                break;
            case R.id.btn_examenes:
                /*Intent intentExamenes = new Intent(this, Examenes.class);
                startActivity(intentExamenes);
                break;*/
            default:
                break;
        }

    }
}