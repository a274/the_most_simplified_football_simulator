package com.example.football_simulation;

public class Score {
    private int countTeamRED = 0;
    private int countTeamBLUE = 0;

    public int getCountTeamRED() {
        return countTeamRED;
    }

    public int getCountTeamBLUE() {
        return countTeamBLUE;
    }

    public void incR() {
        ++countTeamRED;
    }

    public void incB() {
        ++countTeamBLUE;
    }

    private Score() {}
    private static class SingletonHolder {
        public static final Score HOLDER_INSTANCE = new Score();
    }

    public static Score getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }
}