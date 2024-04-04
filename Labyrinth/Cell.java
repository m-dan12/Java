package Labyrinth;

public class Cell {
    private String condition; // Состояние: пусто / стена / какашка / робот / граница
    private boolean visited;
    public Cell() { condition = "пусто"; }
    public Cell(String condition) { this.condition = condition; }
    public void setRobot() { condition = "робот"; }
    public void setWall() { condition = "стена"; }
    public void setBorder() { condition = "граница"; }
    public void setPoo() { condition = "какашка"; }
    public void clear() { condition = "пусто"; }
    public boolean isNotVisited() { return !visited; }
    public void toVisit() { visited = true; }
    public String getCondition() { return condition; }
    public String getPerformance(){
        return switch (condition){
            case "стена" -> " # ";
            case "граница" -> " % ";
            case "какашка" -> " к ";
            case "робот" -> " Р ";
            default -> "   ";
        };
    }
}