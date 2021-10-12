package com.example.simpleanimation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import static java.lang.Thread.sleep;

public class MyView extends View {
    int N = 21; // количество шариков
    float[] x = new float[N];
    float[] y = new float[N];
    float[] vx = new float[N];
    float[] vy = new float[N];
    Paint paint = new Paint();
    int pause = 0;

    float xBall;
    float yBall;
    float vxBall;
    float vyBall;

    int countTeamRED = 0, countTeamBLUE = 0;

    boolean moved = false;

    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        setBackgroundColor(0xFF009900);
        dotsInit();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //поле
        fieldInit(canvas);
        // отрисовываем все шарики
        paintDots(canvas);

        //мяч
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(xBall, yBall, 16, paint);

        initPause();

        //обработка нажатия
        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kickTheBall();
                moved = true;
            }
        });

        if (moved) moveBall();
        if (isKicked()) {
            kickTheBall();
            moved = true;
        }
        getCountTeamRED();
        getCountTeamBLUE();
        // готовим массивы x и у для следущего кадра
        changePosition();

        invalidate();
    }

    public void dotsInit() {
        xBall = getWidth() / 2;
        yBall = getHeight() / 2;

        x[0] = getWidth() / 2;
        y[0] = getHeight() * 15 / 16;

        x[1] = getWidth() / 5;
        y[1] = getHeight() * 7 / 8;

        x[2] = getWidth() / 5 * 2;
        y[2] = getHeight() * 7 / 8;

        x[3] = getWidth() / 5 * 3;
        y[3] = getHeight() * 7 / 8;

        x[4] = getWidth() / 5 * 4;
        y[4] = getHeight() * 7 / 8;

        x[5] = getWidth() / 2;
        y[5] = getHeight() * 7 / 9;

        x[6] = getWidth() / 3 * 2;
        y[6] = getHeight() * 6 / 9;

        x[7] = getWidth() / 3;
        y[7] = getHeight() * 6 / 9;

        x[8] = getWidth() / 5;
        y[8] = getHeight() * 5 / 9;

        x[9] = getWidth() / 5 * 4;
        y[9] = getHeight() * 5 / 9;

        x[10] = getWidth() / 2;
        y[10] = getHeight() * 5 / 9;


        x[11] = getWidth() / 2;
        y[11] = getHeight() / 16;

        x[12] = getWidth() / 5;
        y[12] = getHeight() / 8;

        x[13] = getWidth() / 5 * 2;
        y[13] = getHeight() / 8;

        x[14] = getWidth() * 3 / 5;
        y[14] = getHeight() / 8;

        x[15] = getWidth() * 4 / 5;
        y[15] = getHeight() / 8;

        x[16] = getWidth() / 2;
        y[16] = getHeight() / 9 * 2;

        x[17] = getWidth() / 3 * 2;
        y[17] = getHeight() * 3 / 9;

        x[18] = getWidth() / 3;
        y[18] = getHeight() * 3 / 9;

        x[19] = getWidth() / 5;
        y[19] = getHeight() * 4 / 9;

        x[20] = getWidth() / 5 * 4;
        y[20] = getHeight() * 4 / 9;


        for (int i = 0; i < N; i++) {
            vx[i] = (float) (Math.random() * 5);
            vy[i] = (float) (Math.random() * 5);
        }
    }

    public void initPause() {
        if (pause < 3) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pause++;
        }
    }

    public void paintDots(Canvas canvas) {
        for (int i = 0; i < N; i++) {
            if (i > 10) {
                paint.setColor(Color.RED);
            } else {
                paint.setColor(Color.BLUE);
            }
            canvas.drawCircle(x[i], y[i], 10, paint);
        }
    }

    public void changePosition() {
        for (int i = 0; i < N; i++) {
            x[i] += vx[i];
            y[i] += vy[i];
            if (x[i] < 0 || x[i] > this.getWidth()) {
                vx[i] = -vx[i];
            }
            if (y[i] < 0 || y[i] > this.getHeight()) {
                vy[i] = -vy[i];
            }
        }
    }

    public void fieldInit(Canvas canvas) {
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        canvas.drawRect(getWidth() / 4, 0, getWidth() * 3 / 4, getHeight() / 8, paint);
        canvas.drawRect(getWidth() / 4, getHeight() * 7 / 8, getWidth() * 3 / 4, getHeight(), paint);
        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, paint);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 150, paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 10, paint);
    }

    public void moveBall() {
        xBall += vxBall;
        yBall += vyBall;
        vxBall -= 0.01;
        vyBall -= 0.01;
        if (xBall < 0 || xBall > this.getWidth()) {
            vxBall = -vxBall;
            vxBall += 0.02;
        }
        if (yBall < 0 || yBall > this.getHeight()) {
            vyBall = -vyBall;
            vyBall += 0.02;
        }
        stopTheBall();
    }

    public boolean isKicked() {
        for (int i = 0; i < N; i++) {
            if (Math.abs(xBall - x[i]) < 20 && Math.abs(yBall - y[i]) < 20)
                return true;
        }
        return false;
    }

    public void stopTheBall() {
        if (vxBall < 0.000001 && vyBall < 0.000001) moved = false;
    }

    public void kickTheBall() {
        vxBall = (float) (Math.random() * 10 - 6);
        vyBall = (float) (Math.random() * 10 - 6);
    }

    public int getCountTeamRED() {
        if (Math.abs(yBall - getHeight()) < 5 && xBall < getWidth() * 3 / 4 && xBall > getWidth() / 4) countTeamRED++;
        Log.v("RED TEAM ", "goals: " + countTeamRED);
        return countTeamRED;
    }

    public int getCountTeamBLUE() {
        if (yBall < 5 && xBall < getWidth() * 3 / 4 && xBall > getWidth() / 4) countTeamBLUE++;
        Log.v("BLUE TEAM ", "goals: " + countTeamBLUE);
        return countTeamBLUE;
    }
}
