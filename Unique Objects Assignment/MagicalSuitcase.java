// Name: Josh Liu and Jimmy Liu
// Date: February 16, 2021
// File name: MagicalSuitcase.java
// Teacher: Ms. Krasteva
// Description: This is a subclass that represents a magical suitcase based on a fantasy theme

public class MagicalSuitcase extends Baggage {
    private double capacity;
    private int height;
    private boolean isFlying;
    private int visibility;
    private int remainingMissles;
    private boolean hasLegs;
    private int gas;
    private int tankCapacity;

    public MagicalSuitcase() {
        super();
        capacity = 1000.0;
        height = 0;
        isFlying = false;
        visibility = 0;
        remainingMissles = 0;
        hasLegs = false;
        tankCapacity = 1000;
        gas = tankCapacity;
    }

    public MagicalSuitcase(String color, double length, double width, double depth, String material, int pockets,
            String status, double weight, String contents[], String location, double capacity, int height,
            boolean isFlying, int visibility, int remainingMissles, boolean hasLegs, int gas, int tankCapacity) {
            
        super(color, length, width, depth, material, pockets, status, weight, contents, location);
        this.capacity = capacity;
        this.height = height;
        this.isFlying = isFlying;
        this.visibility = visibility;
        this.remainingMissles = remainingMissles;
        this.hasLegs = hasLegs;
        this.gas = gas;
        this.tankCapacity = tankCapacity;
    }

    @Override
    public double getCapacity() {
        return capacity;
    }

    public int getHeight() {
        return height;
    }

    public int getVisibility() {
        return visibility;
    }

    public int getRemainingMissles() {
        return remainingMissles;
    }

    public int getGas() {
        return gas;
    }

    public int getTankCapacity() {
        return tankCapacity;
    }

    public boolean getIsFlying() {
        return isFlying;
    }

    public void fly(int height) {
        this.height = height;
        if (height > 0) {
            isFlying = true;
        } else {
            isFlying = false;
        }
    }

    public void setVisibility(int visibility) {
        // Will be a value from 0% to 100%
        // 100 visible, 0 invisible
        if (visibility >= 0 && visibility <= 100)
            gas -= 10;
        this.visibility = visibility;
    }

    public void reshape(int length, int width, int depth) {
        setLength(length);
        setWidth(width);
        setDepth(depth);
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public boolean fireMissle() {
        // true/false depending it there were any missles left
        if (remainingMissles != 0) {
            gas -= 10;
            remainingMissles--;
            return true;
        }
        return false;
    }

    public void timeTravel(String time) {
        // true/false depending on if it was successful
        if (Math.random() < 0.9) {
            // 90% success rate
            System.out.println("Time travel successful! Traveling to " + time + ".");
        } else {
            System.out.println("Time travel unsuccessful! You stuck right here.");
        }
    }

    public void attack(String thing) {
        gas -= 100;
        System.out.println("The Suitcase Attack. The attack was very effective on " + thing);
    }

    public void extendLegs() {
        hasLegs = true;
    }

    public void retractLegs() {
        hasLegs = false;
    }

    public void run(int distance) {
        if (hasLegs) {
            gas -= distance * 5;
            System.out.println("Running " + distance + " kilometers.");
        } else {
            System.out.println("You have no legs to run with!");
        }
    }

    public void fuelUp() {
        gas = tankCapacity;
    }

    public String toString() {
        return "This " + getColor() + " magical suitcase is being carried at " + getLocation() + ".";
    }
}
