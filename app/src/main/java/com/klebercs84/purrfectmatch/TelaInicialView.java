package com.klebercs84.purrfectmatch;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
public class TelaInicialView extends View {

    private Bitmap bgTelaInicial;
    private Bitmap logoCat;
    private Bitmap btnPlay;
    private Bitmap btnLevels;

    // Animação do logo
    private float logoY;
    private float logoYFinal;
    private boolean logoChegou = false;

    // Animação dos botôes (fade-in)
    private int btnAlpha = 0;

    // Posições dos botões para detectar toque
    private int btnPlayTop, btnPlayBottom;
    private int btnLevelsTop, btnLevelsBottom;
    private int btnX, btnW, btnH;

    private final Handler handler = new Handler();
    private Paint alphaPaint;

    public interface OnPlayListener {
        void onPlay();
    }

    private OnPlayListener listener;

    public TelaInicialView(Context context, OnPlayListener listener) {
        super(context);
        this.listener = listener;
        carregarAssets(context);
        alphaPaint = new Paint();
    }

    private void carregarAssets (Context context) {
        bgTelaInicial = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg_tela_inicial);
        logoCat = BitmapFactory.decodeResource(context.getResources(), R.drawable.logo_cat_crush);
        btnPlay = BitmapFactory.decodeResource(context.getResources(), R.drawable.btn_play);
        btnLevels = BitmapFactory.decodeResource(context.getResources(), R.drawable.btn_levels);
    }

    @Override
    protected void onSizeChanged (int w, int h, int oldw, int oldh) {
        // Logo começa abaixo da tela e sobe até o terço superior
        logoY = h + 100;
        logoYFinal = (int)(h * 0.12f);

        // Botões
        btnW = (int) (w * 0.70f);
        btnH = (int) (btnW * 0.22f);
        btnX = (w - btnW) / 2;

        btnPlayTop = (int) (h * 0.3f);
        btnPlayBottom = btnPlayTop + btnH;
        btnLevelsTop = btnPlayBottom + (int) (h * 0.025f);
        btnPlayBottom = btnLevelsTop + btnH;

        // Inicia i loop de animação
        iniciarAnimacao();
    }

    private void iniciarAnimacao () {
        handler.post(new Runnable() {
            @Override
            public void run() {
                boolean precisaAtualizar = false;

                // Animação do logo subindo
                if (logoY > logoYFinal){
                    logoY -= (logoY - logoYFinal) * 0.08f + 2;
                    if (logoY <= logoYFinal){
                        logoY = logoYFinal;
                        logoChegou = true;
                    }
                    precisaAtualizar = true;
                }

                // Fade-in dos botões após o logo chegar
                if (logoChegou && btnAlpha < 255){
                    btnAlpha = Math.min(255, btnAlpha + 6);
                    precisaAtualizar = true;
                }

                if (precisaAtualizar){
                    invalidate();
                    handler.postDelayed(this,160); // ~60fps
                }
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        int w = getWidth();
        int h = getHeight();

        // Background
        if (bgTelaInicial != null){
            canvas.drawBitmap(Bitmap.createScaledBitmap(bgTelaInicial, w, h, true), 0,0,null);
        } else {
            canvas.drawColor(Color.rgb(12,11,42));
        }

        // Logo animado
        if (logoCat != null){
            int logoW = (int) (w * 0.80f);
            int logoH = (int) (logoW * 0.28f);
            int logoX = (w - logoW) / 2;
            alphaPaint.setAlpha(255);
            canvas.drawBitmap(Bitmap.createScaledBitmap(logoCat, logoW, logoH, true), logoX, logoY, alphaPaint);
        }

        // Botões com fade-in
        alphaPaint.setAlpha(btnAlpha);

        if (btnPlay != null){
            canvas.drawBitmap(Bitmap.createScaledBitmap(btnPlay, btnW, btnH, true), btnX, btnPlayTop, alphaPaint);
        }

        if (btnLevels != null){
            canvas.drawBitmap(Bitmap.createScaledBitmap(btnLevels, btnW, btnH, true), btnX, btnLevelsTop, alphaPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (event.getAction() != MotionEvent.ACTION_DOWN) return true;

        // Só responde toque após os botôes aparecerem
        if (btnAlpha < 200) return true;

        float y = event.getY();

        if (y >= btnPlayTop && y <= btnPlayBottom){
            if (listener != null) listener.onPlay();
        }
        return true;
    }

    @Override
    protected void  onDetachedFromWindow(){
        super.onDetachedFromWindow();
        handler.removeCallbacksAndMessages(null);
    }

}
