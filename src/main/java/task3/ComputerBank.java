package task3;

public class ComputerBank extends Protection {
    private final Metal frontSide;
    private final Room room;

    public ComputerBank(Room room, int health) {
        super(health);
        this.room = room;
        this.frontSide = new Metal(this, health);
    }

    @Override
    public void takeDamage(int damage) {
        int diff = damage - health;
        health = Math.max((health - damage), 0);
        if (diff > 0) {
            frontSide.takeDamage(diff);
        }
    }

    public Room getRoom() {
        return this.room;
    }

    public Metal getFrontSide() {
        return this.frontSide;
    }
}