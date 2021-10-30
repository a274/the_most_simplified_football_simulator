package com.example.football_simulation;

public class Ball {
    private final Coordinates ball;
    private Coordinates vBall;
    private final Coordinates view;
    private boolean moved;

    public Ball(float width, float height) {
        view = new Coordinates(width, height);
        ball = new Coordinates(view.x / 2f, view.y / 2f);
    }

    public void kickTheBall() {
        vBall = new Coordinates((float)(Math.random() * 10 - 5), (float)(Math.random() * 10 - 5));
    }

    public void moveBall() {
        ball.x += vBall.x;
        ball.y += vBall.y;
        vBall.x -= 0.01;
        vBall.y -= 0.01;
        if (ball.x > view.x) {
            vBall.x = -Math.abs(vBall.x);
            vBall.x += 0.02;
        } else if (ball.x < 0) {
            vBall.x = Math.abs(vBall.x);
        }
        if (ball.y > view.y) {
            vBall.y = -Math.abs(vBall.y);
            vBall.y += 0.02;
        } else if (ball.y < 0) {
            vBall.y = Math.abs(vBall.y);
        }
        stopTheBall();
    }

    public void stopTheBall() {
        if (vBall.x < 0.000001 && vBall.y < 0.000001) moved = false;
    }

    public float getX() {
        return ball.x;
    }

    public float getY() {
        return ball.y;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved() {
        moved = true;
    }
}
