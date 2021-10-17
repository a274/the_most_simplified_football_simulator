package com.example.simpleanimation;

public class Footballers {
    private final int N = 21; // количество шариков
    private final float[] x = new float[N];
    private final float[] y = new float[N];
    private final float[] vx = new float[N];
    private final float[] vy = new float[N];
    private final Coordinates view;

    public Footballers(float width, float height) {
        view = new Coordinates(width, height);
    }

    public void dotsInit() {
        //blue goalkeeper
        x[0] = view.x / 2f;
        y[0] = view.y * 15f / 16;

        x[1] = view.x / 5f;
        y[1] = view.y * 7f / 8;

        x[2] = view.x / 5f * 2;
        y[2] = view.y * 7f / 8;

        x[3] = view.x / 5f * 3;
        y[3] = view.y * 7f / 8;

        x[4] = view.x / 5f * 4;
        y[4] = view.y * 7f / 8;

        x[5] = view.x / 2f;
        y[5] = view.y * 7f / 9;

        x[6] = view.x / 3f * 2;
        y[6] = view.y * 6f / 9;

        x[7] = view.x / 3f;
        y[7] = view.y * 6f / 9;

        x[8] = view.x / 5f;
        y[8] = view.y * 5f / 9;

        x[9] = view.x / 5f * 4;
        y[9] = view.y * 5f / 9;

        x[10] = view.x / 2f;
        y[10] = view.y * 5f / 9;


        x[11] = view.x / 5f * 4;
        y[11] = view.y * 4f / 9;

        x[12] = view.x / 5f;
        y[12] = view.y / 8f;

        x[13] = view.x / 5f * 2;
        y[13] = view.y / 8f;

        x[14] = view.x * 3f / 5;
        y[14] = view.y / 8f;

        x[15] = view.x * 4f / 5;
        y[15] = view.y / 8f;

        x[16] = view.x / 2f;
        y[16] = view.y / 9f * 2;

        x[17] = view.x / 3f * 2;
        y[17] = view.y * 3f / 9;

        x[18] = view.x / 3f;
        y[18] = view.y * 3f / 9;

        x[19] = view.x / 5f;
        y[19] = view.y * 4f / 9;

        //red goalkeeper
        x[20] = view.x / 2f;
        y[20] = view.y / 16f;

        for (int i = 0; i < N; i++) {
            vx[i] = (float) (Math.random() * 5);
            vy[i] = (float) (Math.random() * 5);
        }
    }

    public void changePosition() {
        for (int i = 1; i < N - 1; i++) {
            x[i] += vx[i];
            y[i] += vy[i];
            if (x[i] < 0 || x[i] > view.x) {
                vx[i] = -vx[i];
            }
            if (y[i] < 0 || y[i] > view.y) {
                vy[i] = -vy[i];
            }
        }
    }

    public void moveBlueGoalkeeper() {
        x[0] += vx[0];
        y[0] += vy[0];
        if (x[0] < view.x / 4f || x[0] > view.x * 3 / 4f) {
            vx[0] = -vx[0];
        }
        if (y[0] < view.y * 7 / 8f || y[0] > view.y) {
            vy[0] = -vy[0];
        }
    }

    public void moveRedGoalkeeper() {
        x[20] += vx[20];
        y[20] += vy[20];
        if (x[20] < view.x / 4f || x[20] > view.x * 3 / 4f) {
            vx[20] = -vx[20];
        }
        if (y[20] < 0 || y[20] > view.y / 8f) {
            vy[20] = -vy[20];
        }
    }

    public float[] getX() {
        return x;
    }

    public float[] getY() {
        return y;
    }
}
