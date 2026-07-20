package com.klebercs84.purrfectmatch;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mostrarTelaInicial();
    }

    private void mostrarTelaInicial() {
        TelaInicialView telaInicial = new TelaInicialView(this,
                () -> mostrarJogo());
        setContentView(telaInicial);
    }

    public void mostrarJogo() {
        GameView gameView = new GameView(this, this);
        setContentView(gameView);
    }

    public void mostrarVitoria(int pontuacao) {
        TelaResultadoView tela = new TelaResultadoView(
                this,
                TelaResultadoView.Resultado.VITORIA,
                pontuacao,
                new TelaResultadoView.OnResultadoListener() {
                    @Override
                    public void onTentarNovamente() {
                        mostrarJogo();
                    }
                    @Override
                    public void onProximaFase() {
                        mostrarJogo(); // por enquanto reinicia a fase
                    }
                });
        setContentView(tela);
    }

    public void mostrarGameOver(int pontuacao) {
        TelaResultadoView tela = new TelaResultadoView(
                this,
                TelaResultadoView.Resultado.GAME_OVER,
                pontuacao,
                new TelaResultadoView.OnResultadoListener() {
                    @Override
                    public void onTentarNovamente() {
                        mostrarJogo();
                    }
                    @Override
                    public void onProximaFase() {
                        mostrarJogo();
                    }
                });
        setContentView(tela);
    }
}