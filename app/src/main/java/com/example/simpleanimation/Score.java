package com.example.simpleanimation;

public class Score {
    private int countTeamRED = 0;
    private int countTeamBLUE = 0;

    public int getCountTeamRED() {
        return countTeamRED;
    }

    public int getCountTeamBLUE() {
        return countTeamBLUE;
    }

    public int incR() {
        return ++countTeamRED;
    }

    public int incB() {
        return ++countTeamBLUE;
    }

    private Score() {}
    private static class SingletonHolder {
        public static final Score HOLDER_INSTANCE = new Score();
    }

    public static Score getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }
}