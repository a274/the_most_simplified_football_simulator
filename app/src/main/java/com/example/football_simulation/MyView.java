package com.example.football_simulation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


@SuppressLint("ViewConstructor")
public class MyView extends View {
    Score score = Score.getInstance();
    private static final int N = 21;
    private final Paint paint = new Paint();
    private int pause = 0;
    private Ball ball;
    private Footballers footballers;
    private boolean countOnceR = false;
    private boolean countOnceB = false;

    private final MutableLiveData<Score> liveData = new MutableLiveData<>();

    LiveData<Score> getData() {
        return liveData;
    }

    public MyView(Context context) {
        super(context);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        ball = new Ball(this.getWidth(), this.getHeight());
        footballers = new Footballers(this.getWidth(), this.getHeight());
        setBackgroundColor(0xFF009900);
        footballers.dotsInit();
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
        canvas.drawCircle(ball.getX(), ball.getY(), 16, paint);

        initPause();

        //обработка нажатия
        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ball.kickTheBall();
                ball.setMoved();
            }
        });

        if (ball.isMoved()) ball.moveBall();
        if (isKicked()) {
            ball.kickTheBall();
            ball.setMoved();
        }
        goalsRED();
        goalsBLUE();
        liveData.setValue(score);
        // готовим массивы x и у для следущего кадра
        footballers.changePosition();
        footballers.moveBlueGoalkeeper();
        footballers.moveRedGoalkeeper();
        invalidate();
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
            canvas.drawCircle(footballers.getX()[i], footballers.getY()[i], 10, paint);
        }
    }

    public void fieldInit(Canvas canvas) {
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        canvas.drawRect(getWidth() / 4f, 0, getWidth() * 3 / 4f, getHeight() / 8f, paint);
        canvas.drawRect(getWidth() / 4f, getHeight() * 7f / 8, getWidth() * 3 / 4f, getHeight(), paint);
        canvas.drawLine(0, getHeight() / 2f, getWidth(), getHeight() / 2f, paint);
        canvas.drawCircle(getWidth() / 2f, getHeight() / 2f, 150, paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(getWidth() / 2f, getHeight() / 2f, 10, paint);
    }

    public boolean isKicked() {
        for (int i = 0; i < N; i++) {
            if (Math.abs(ball.getX() - footballers.getX()[i]) < 20 && Math.abs(ball.getY() - footballers.getY()[i]) < 20)
                return true;
        }
        return false;
    }

    public void goalsRED() {
        if (Math.abs(ball.getY() - getHeight()) < 5 && ball.getX() < getWidth() * 3f / 4 && ball.getX() > getWidth() / 4f) {
            if (!countOnceR) {
                score.incR();
                countOnceR = true;
            }
        } else {
            countOnceR = false;
        }
    }

    public void goalsBLUE() {
        if (ball.getY() < 5 && ball.getX() < getWidth() * 3f / 4 && ball.getX() > getWidth() / 4f) {
            if (!countOnceB) {
                score.incB();
                countOnceB = true;
            }
        } else {
            countOnceB = false;
        }
    }
}
