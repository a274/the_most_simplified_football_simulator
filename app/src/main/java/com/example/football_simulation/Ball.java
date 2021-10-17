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
        vBall = new Coordinates((float)(Math.random() * 10 - 3), (float)(Math.random() * 10 - 3));
    }

    public void moveBall() {
        ball.x += vBall.x;
        ball.y += vBall.y;
        vBall.x -= 0.01;
        vBall.y -= 0.01;
        if (ball.x < 0 || ball.x > view.x) {
            vBall.x = -vBall.x;
            vBall.x += 0.02;
        }
        if (ball.y < 0 || ball.y > view.y) {
            vBall.y = -vBall.y;
            vBall.y += 0.02;
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
