package eatapp.meals;

import java.util.Iterator;
import java.util.List;

public class Instructions {

    private final List<String> instructions;
    private final int minutesToCook;

    public Instructions(List<String> instructions, int minutesToCook) {
        this.instructions = instructions;
        this.minutesToCook = minutesToCook;
    }

    public Iterator<String> getInstructions(){
        return instructions.iterator();
    }

    public int getMinutesToCook() {
        return minutesToCook;
    }
}
