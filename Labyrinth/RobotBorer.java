package Labyrinth;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
public class RobotBorer{
    int x;
    int y;
    Labyrinth labyrinth;
    ArrayList<String> way;
    {
        x = 1;
        y = 1;
        labyrinth = new Labyrinth();
        labyrinth.cells[y][x].setRobot();
        labyrinth.cells[y][x].toVisit();
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
    String getNewSide(){

        ArrayList<String> sideList = new ArrayList<>();

        if (labyrinth.cells[y][x + 1].getCondition().equals("стена") && labyrinth.cells[y][x + 2].isNotVisited()) sideList.add("вправо");
        if (labyrinth.cells[y][x - 1].getCondition().equals("стена") && labyrinth.cells[y][x - 2].isNotVisited()) sideList.add("влево");
        if (labyrinth.cells[y + 1][x].getCondition().equals("стена") && labyrinth.cells[y + 2][x].isNotVisited()) sideList.add("вниз");
        if (labyrinth.cells[y - 1][x].getCondition().equals("стена") && labyrinth.cells[y - 2][x].isNotVisited()) sideList.add("вверх");
        
        if (!sideList.isEmpty()) return sideList.get((int)(Math.random() * sideList.size()));
        else return null;

    }
    void move(String side){
        labyrinth.cells[y][x].clear();
        switch (side){
            case "вправо":
                x += 2;
                way.add("вправо");
                break;
            case "влево":
                x -= 2;
                way.add("влево");
                break;
            case "вниз":
                y += 2;
                way.add("вниз");
                break;
            case "вверх":
                y -= 2;
                way.add("вверх");
                break;
        }
        labyrinth.cells[y][x].setRobot();
        labyrinth.cells[y][x].toVisit();
    }
    void moveBack(){
        labyrinth.cells[y][x].clear();
        switch (way.getLast()){
            case "вправо":
                x -= 2;
                break;
            case "влево":
                x += 2;
                break;
            case "вниз":
                y -= 2;
                break;
            case "вверх":
                y += 2;
                break;
        }
        way.removeLast();
        labyrinth.cells[y][x].setRobot();
    }
    void bore(String side){
        switch (side){
            case "вправо":
                labyrinth.cells[y][x + 1].clear();
                break;
            case "влево":
                labyrinth.cells[y][x - 1].clear();
                break;
            case "вниз":
                labyrinth.cells[y + 1][x].clear();
                break;
            case "вверх":
                labyrinth.cells[y - 1][x].clear();
                break;
        }
        move(side);
    }
    void movement() {
        String side = getNewSide();
        while(x != 1 || y != 1 || side != null){
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            side = getNewSide();
            if (side != null) bore(side);
            else moveBack();
            labyrinth.print();
            System.out.println();
        }
    }
}