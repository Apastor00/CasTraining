package com.castraining.castraining;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.castraining.castraining.databinding.ActivityMainBinding;
import com.castraining.castraining.fragment.accountFragment;
import com.castraining.castraining.fragment.explorar;
import com.castraining.castraining.fragment.listadoCursos;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity{

    public static final String URL_BASE = "https://cas-training.com/wp-json/wp/v2/";

    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        NavController navController = NavHostFragment.findNavController(mainBinding.fragmentNavHost.getFragment());
        NavigationUI.setupWithNavController(mainBinding.navView, navController);

        mainBinding.navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {

                if (menuitem.getItemId() == R.id.listado_cursos){
                    selectFragment(new listadoCursos());
                }
                if (menuitem.getItemId() == R.id.cuenta_usuario){
                    selectFragment(new accountFragment());
                }
                if (menuitem.getItemId() == R.id.explorar){
                    selectFragment(new explorar());
                }
                return true;
            }
        });
    }
    //Método que permite seleccionar el Fragment a mostrar
    private void selectFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_nav_host, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

}