package com.klebercs84.purrfectmatch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
public class GameView extends View{
    private Tabuleiro tabuleiro;
    private Paint paint;
    private int tamanhoCell; // tamanho de cada célula em pixels

    // Cores de cada tipo de gato
    private static final int[] CORES = {
            Color.rgb(255,140, 0), // LARANJA
            Color.rgb(30,30,30), // PRETO
            Color.rgb(240, 240,240), // BRANCO
            Color.rgb(150,150,150), // CINZA
            Color.rgb(180, 120, 60), // RAJADO
            Color.rgb(210,160,80), // CARAMELO
    };

    public GameView (Context context){
        super(context);
        tabuleiro = new Tabuleiro();
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        // Calcula o tamanho de cada célula baseado na largura da tela
        tamanhoCell = w / Tabuleiro.TAMANHO;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        // Fundo escuro
        canvas.drawColor(Color.rgb(20,20,40));

        for (int linha = 0; linha < Tabuleiro.TAMANHO; linha++){
            for (int coluna = 0; coluna < Tabuleiro.TAMANHO;coluna++){
                Gato gato = tabuleiro.getGato(linha, coluna);

                // Posição do círculo na tela
                float cx = coluna * tamanhoCell + tamanhoCell / 2f;
                float cy = linha * tamanhoCell + tamanhoCell / 2f;
                float raio = tamanhoCell / 2f - 6;

                // Desenha o círculo colorido (corpo do gato)
                paint.setColor(CORES[gato.getTipo()]);
                paint.setStyle(Paint.Style.FILL);
                paint.setStrokeWidth(2);
                canvas.drawCircle(cx, cy, raio, paint);
            }
        }
    }
}
