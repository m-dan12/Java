import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Predicate;

class Labyrinth {
    class Cell {
        String condition; // Состояние: пусто / стена / какашка / робот / граница
        Cell() { condition = "пусто"; }
        Cell(String condition) { this.condition = condition; }
        void setRobot() { condition = "робот"; }
        void setWall() { condition = "стена"; }
        void setBorder() { condition = "граница"; }
        void setPoo() { condition = "какашка"; }
        void clear() { condition = "пусто"; }
        String getCondition() { return condition; }
        String getPerformance(){
            return switch (condition){
                case "стена" -> " # ";
                case "граница" -> " % ";
                case "какашка" -> " к ";
                case "робот" -> " Р ";
                default -> "   ";
            };
        }
    }
    final int length = 13;
    Cell[][] cells;
    Labyrinth() {
        // Создаем сетку
        cells = new Cell[length][length];
        borderGenerate();
        gridGenerate();
    }
    Labyrinth(Cell[][] cells){
        this.cells = cells;
    }
    void borderGenerate(){
        for(int i = 0; i < length; i++) {
            cells[0][i] = new Cell("граница"); // верхняя граница
            cells[i][0] = new Cell("граница"); // левая граница
            cells[length - 1][i] = new Cell("граница"); // нижняя граница
            cells[i][length - 1] = new Cell("граница"); // правая граница
        }
    }
    void gridGenerate(){
        for (int i = 1; i < length - 1; i++) {
            for (int j = 1; j < length - 1; j++) {
                if (i % 2 == 0 || j % 2 == 0) cells[i][j] = new Cell("стена");
                else cells[i][j] = new Cell();
            }
        }
    }
    void printLabyrinth() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++)
                System.out.print(cells[i][j].getPerformance());
            System.out.println();
        }
    }
}
class RobotBorer{
    int x;
    int y;
    Labyrinth labyrinth;
    ArrayList<String> way;
    {
        x = 1;
        y = 1;
        labyrinth = new Labyrinth();
        way = new ArrayList<>();
    }
    RobotBorer(){}
    RobotBorer(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
    }
    RobotBorer(int x, int y, Labyrinth labyrinth){
        this.x = x;
        this.y = y;
        this.labyrinth = labyrinth;
    }
    Labyrinth getLabyrinth(){
        return labyrinth;
    }
    void moveRight(){
        labyrinth.cells[y][x].clear();
        x += 2;
        labyrinth.cells[y][x].setRobot();
    }
    void moveLeft(){
        labyrinth.cells[y][x].clear();
        x -= 2;
        labyrinth.cells[y][x].setRobot();
    }
    void moveBottom(){
        labyrinth.cells[y][x].clear();
        y += 2;
        labyrinth.cells[y][x].setRobot();
    }
    void moveTop(){
        labyrinth.cells[y][x].clear();
        y -= 2;
        labyrinth.cells[y][x].setRobot();
    }

    void boreRight(){
        labyrinth.cells[y][x + 1].clear();
    }
    void boreLeft(){
        labyrinth.cells[y][x - 1].clear();
    }
    void boreBottom(){
        labyrinth.cells[y + 1][x].clear();
    }
    void boreTop(){
        labyrinth.cells[y - 1][x].clear();
    }
    String whereGo(String cellCondition){
        ArrayList<String> sideList = new ArrayList<String>();
        if (labyrinth.cells[y][x + 1].getCondition().equals(cellCondition)) sideList.add("вправо");
        if (labyrinth.cells[y][x - 1].getCondition().equals(cellCondition)) sideList.add("влево");
        if (labyrinth.cells[y + 1][x].getCondition().equals(cellCondition)) sideList.add("вниз");
        if (labyrinth.cells[y - 1][x].getCondition().equals(cellCondition)) sideList.add("вверх");
        return sideList.get((int)(Math.random() * sideList.size()));
    }
    void move(){
//        switch (x){
//            case "вправо":
//                moveRight();
//                way.add("вправо");
//                break;
//            case "влево":
//                moveLeft();
//                way.add("влево");
//                break;
//            case "вниз":
//                moveBottom();
//                way.add("вниз");
//                break;
//            case "вверх":
//                moveTop();
//                way.add("верх");
//                break;
//        }
    }
    void moveBack(){

    }

}