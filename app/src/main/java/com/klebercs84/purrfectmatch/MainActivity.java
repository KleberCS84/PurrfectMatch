package com.klebercs84.purrfectmatch;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // Substitui o layout XML pela GameView desenhada no código
        GameView gameView = new GameView(this);
        setContentView(gameView);
    }
}