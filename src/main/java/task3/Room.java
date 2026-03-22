package task3;

public class Room extends Protection {
    private Temperature temperature;
    private NoiseLevel noiseLevel;
    private final ComputerBank computerBank;
    private final Corner corner;
    private People people;

    public Room(int initialHealth) {
        super(initialHealth);
        this.computerBank = new ComputerBank(this, health);
        this.corner = new Corner(this, health);
        this.temperature = Temperature.MEDIUM;
        this.noiseLevel = NoiseLevel.CALM;
    }

    @Override
    public void takeDamage(int damage) {
        int oldHealth = this.health;
        this.health = Math.max(0, this.health - damage);
        int diff = Math.max(0, damage - oldHealth);

        if ((double) health / initialHealth <= 0.5) {
            this.noiseLevel = NoiseLevel.MEDIUM;
        }

        if (this.health == 0) {
            this.noiseLevel = NoiseLevel.LOUD;
            this.temperature = Temperature.HOT;

            if (damage >= 300 && this.people != null) {
                this.people.seeDanger();
            }
        }

        if (diff > 0) {
            this.computerBank.getFrontSide().takeDamage(diff);
        }
    }

    public Temperature getTemperature() {
        return this.temperature;
    }

    public NoiseLevel getNoiseLevel() {
        return this.noiseLevel;
    }

    public Corner getCorner() {
        return this.corner;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public ComputerBank getComputerBank() {
        return this.computerBank;
    }

    public People getPeople() {
        return this.people;
    }
}