package com.hai.test;

/**
 * Created by Administrator on 2017/9/26.
 */
public class JavaErrorTest {
    private static double volume(String solidom, double alturam, double areaBasem, double raiom) {
        double vol;
        if (solidom.equalsIgnoreCase("esfera")) {
            vol = (4.0 / 3) * Math.PI * Math.pow(raiom, 3);
        } else {
            if (solidom.equalsIgnoreCase("cilindro")) {
                vol = Math.PI * Math.pow(raiom, 2) * alturam;
            } else {
                vol = (1.0 / 3) * Math.PI * Math.pow(raiom, 2) * alturam;
            }
        }
        return vol;
    }
}

abstract class NFLPlayersReference {
    private static Runningback[] nflplayersreference;
    private static Quarterback[] players;
    private static WideReceiver[] nflplayers;

    public static void main(String args[]) {
        Runningback r = new Runningback("Thomlinsion");
        Quarterback q = new Quarterback("Tom Brady");
        WideReceiver w = new WideReceiver("Steve Smith");
        NFLPlayersReference[] NFLPlayersReference;
        Run();// {
        NFLPlayersReference = new NFLPlayersReference[3];
        nflplayersreference[0] = r;
        players[1] = q;
        nflplayers[2] = w;
        for (int i = 0; i < nflplayersreference.length; i++) {
            System.out.println("My name is " + " nflplayersreference[i].getName())" +
                    nflplayersreference[i].run());
            nflplayersreference[i].run();
            nflplayersreference[i].run();
            System.out.println("NFL offensive threats have great running abilities!");
        }
    }

    private static void Run() {
        System.out.println("Not yet implemented");
    }

    private static class Runningback {
        public Runningback(String thomlinsion) {
            System.out.println(thomlinsion);
        }

        public String run() {
            return "";
        }
    }

    private static class Quarterback {
        public Quarterback(String s) {
            System.out.println(s);
        }
    }

    private static class WideReceiver {
        public WideReceiver(String s) {
            System.out.println(s);
        }
    }
}