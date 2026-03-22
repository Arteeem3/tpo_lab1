package task3;

import static task3.MetalState.LIQUID;
import static task3.MetalState.SOLID;

public class Metal extends Protection {
    private final ComputerBank computerBank;
    private MetalState state = SOLID;

    public Metal(ComputerBank computerBank, int health) {
        super(health);
        this.computerBank = computerBank;
    }

    @Override
    public void takeDamage(int damage) {
        int diff = damage - health;
        health = Math.max((health - damage), 0);

        if (health == 0) {
            state = LIQUID;
        }

        if (diff > 0) {
            melt(diff);
        }
    }

    private void melt(int damage) {
        computerBank.getRoom().getCorner().takeDamage(damage);
    }

    public MetalState getState() {
        return state;
    }
}