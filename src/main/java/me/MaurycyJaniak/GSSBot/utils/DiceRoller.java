package me.MaurycyJaniak.GSSBot.utils;

import java.util.Random;

public class DiceRoller {

    public static int rollForPlayers(int dice) {

        int roll = 0;

        Random rand = new Random();
        roll = rand.nextInt(dice + 1);
        if (roll > dice) {
            roll = dice;
        }
        if (roll < 0) {
            roll = 0;
        }

        return roll;

    }

    public static int rollForSystem(int dice) {

        Random rand = new Random();
        int roll = rand.nextInt(dice + 1);
        if (roll > dice) {
            roll = dice;
        }
        if (roll < 0) {
            roll = 0;
        }
        return roll;

    }

}
