package task3;

public class Corner extends Protection {
    private final Room room;
    private boolean destroyed = false;

    public Corner(Room room, int health) {
        super(health);
        this.room = room;
    }

    @Override
    public void takeDamage(int damage) {
        health = Math.max((health - damage), 0);
        if (health == 0 && !destroyed) {
            destroyed = true;
            if (room.getPeople() != null) {
                room.getPeople().seeDanger();
            }
        }
    }
}