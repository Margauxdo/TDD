package org.example;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private int score;
    private boolean lastFrame;
    private IGenerateur generateur;
    private List<Roll> rolls;

    public Frame(IGenerateur generateur, boolean lastFrame) {
        this.lastFrame = lastFrame;
        this.generateur = generateur;
        this.rolls = new ArrayList<Roll>();

    }

    public boolean makeRoll(){
        int pins = generateur.randomPin(10);
        rolls.add(new Roll(pins));
        score += pins;

        if (!lastFrame) {
            if(rolls.size() >= 2 || rolls.size() == 1 && rolls.get(0).getPins() == 10){
                return false;
            }
            return true;
        } else if (rolls.size() < 2 || (rolls.size() == 2 && this.score >= 10)) {
            return true;
        }else{
            return false;
        }



    }


    public int getScore() {
        return score;
    }

}

