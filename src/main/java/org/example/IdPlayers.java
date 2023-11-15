package org.example;

import java.util.Random;

public class IdPlayers {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;

    private int IDj;

    private String s;

    public int IdPlayers(){
        Random aR = new Random();
        a= aR.nextInt((10 - 1) + 1)+1;
        Random bR = new Random();
        b= bR.nextInt((10 - 1) + 1)+1;
        Random cR = new Random();
        c= cR.nextInt((10 - 1) + 1)+1;
        Random dR = new Random();
        d= dR.nextInt((10 - 1) + 1)+1;
        Random eR = new Random();
        e= eR.nextInt((10 - 1) + 1)+1;
        Random fR = new Random();
        f= fR.nextInt((10 - 1) + 1)+1;

        s = String.valueOf(a) + String.valueOf(b)+ String.valueOf(c)+ String.valueOf(d)+ String.valueOf(e)+ String.valueOf(f);

        IDj= Integer.valueOf(s);

        return IDj;





    }
}
