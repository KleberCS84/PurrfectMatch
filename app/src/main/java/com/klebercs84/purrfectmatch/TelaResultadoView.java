package com.klebercs84.purrfectmatch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class TelaResultadoView extends View {

    public enum Resultado { VITORIA, GAME_OVER }

    private Bitmap bgTelaFinal;
    private Bitmap cardVitoria;
    private Bitmap cardGameOver;
    private Bitmap btnNextLevel;
    private Bitmap btnTryAgain;

    private Resultado resultado;
    private int pontuacao;

    // Posição do botão para detectar toque
    private int btnTop, btnBottom, btnLeft, btnRight;

    public interface OnResultadoListener {
        void onTentarNovamente();
        void onProximaFase();
    }

    private OnResultadoListener listener;

    public TelaResultadoView(Context context,
                             Resultado resultado,
                             int pontuacao,
                             OnResultadoListener listener) {
        super(context);
        this.resultado  = resultado;
        this.pontuacao  = pontuacao;
        this.listener   = listener;
        carregarAssets(context);
    }

    private void carregarAssets(Context context) {
        bgTelaFinal = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg_tela_inicial);
        cardVitoria  = BitmapFactory.decodeResource(context.getResources(), R.drawable.card_vitoria);
        cardGameOver = BitmapFactory.decodeResource(context.getResources(), R.drawable.card_game_over);
        btnNextLevel = BitmapFactory.decodeResource(context.getResources(), R.drawable.btn_next_level);
        btnTryAgain  = BitmapFactory.decodeResource(context.getResources(), R.drawable.btn_try_again);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int w = getWidth();
        int h = getHeight();

        // Background
        if (bgTelaFinal != null) {
            canvas.drawBitmap(
                    Bitmap.createScaledBitmap(bgTelaFinal, w, h,true),
                    0, 0, null);
        } else {
            canvas.drawColor(Color.rgb(12, 11, 42));
        }

        // Card central (vitória ou game over)
        Bitmap card = resultado == Resultado.VITORIA ? cardVitoria : cardGameOver;
        int cardW = (int)(w * 1.2f);
        int cardH = (int)(cardW * 0.9f);
        int cardX = (w - cardW) / 2;
        int cardY = (int)(h * 0.08f);

        if (card != null) {
            canvas.drawBitmap(
                    Bitmap.createScaledBitmap(card, cardW, cardH, true),
                    cardX, cardY, null);
        }

        // Pontuação dentro do card
        Paint scorePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        scorePaint.setColor(Color.rgb(240, 238, 255));
        scorePaint.setFakeBoldText(true);
        scorePaint.setTextAlign(Paint.Align.CENTER);
        scorePaint.setTextSize(w * 0.08f);
        canvas.drawText(
                String.valueOf(pontuacao) + " pts",
                w / 2f,
                cardY + cardH * 0.82f,
                scorePaint);

        // Label acima da pontuação
        Paint labelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        labelPaint.setColor(Color.rgb(159, 143, 255));
        labelPaint.setTextAlign(Paint.Align.CENTER);
        labelPaint.setTextSize(w * 0.045f);
        canvas.drawText(
                "SCORE",
                w / 2f,
                cardY + cardH * 0.72f,
                labelPaint);

        // Botão abaixo do card
        Bitmap btn = resultado == Resultado.VITORIA ? btnNextLevel : btnTryAgain;
        int btnW = (int)(w * 0.70f);
        int btnH = (int)(btnW * 0.22f);
        btnLeft  = (w - btnW) / 2;
        btnTop   = cardY + cardH + (int)(h * 0.001f);
        btnRight  = btnLeft + btnW;
        btnBottom = btnTop + btnH;

        if (btn != null) {
            canvas.drawBitmap(
                    Bitmap.createScaledBitmap(btn, btnW, btnH, true),
                    btnLeft, btnTop, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN) return true;

        float x = event.getX();
        float y = event.getY();

        if (x >= btnLeft && x <= btnRight &&
                y >= btnTop  && y <= btnBottom) {
            if (resultado == Resultado.VITORIA) {
                if (listener != null) listener.onProximaFase();
            } else {
                if (listener != null) listener.onTentarNovamente();
            }
        }

        return true;
    }
}