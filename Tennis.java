package com.tennis;

import java.util.Scanner;

public class Tennis {

    public static void main(String[] args) {
        ScoreUtil scoreUtil = new ScoreUtil();
        try ( Scanner scan = new Scanner(System.in);){
            while (!scoreUtil.isMatchComplete()) {
                String player = scan.next();
                scoreUtil.pointWonBy(player);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
