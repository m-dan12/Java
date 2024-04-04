package Labyrinth;

public class Main {
    public static void main(String[] args) {
        RobotBorer robotBorer = new RobotBorer();
        robotBorer.getLabyrinth().print();
        robotBorer.movement();
    }
}