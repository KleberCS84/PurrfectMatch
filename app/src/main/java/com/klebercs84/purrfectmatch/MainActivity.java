package com.klebercs84.purrfectmatch;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mostrarTelaInicial();
    }

    private void mostrarTelaInicial(){
        TelaInicialView telaInicial = new TelaInicialView(this, () -> mostrarJogo());
        setContentView(telaInicial);
    }

    private void mostrarJogo(){
        GameView gameView = new GameView(this);
        setContentView(gameView);
    }
}