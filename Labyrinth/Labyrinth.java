package Labyrinth;

public class Labyrinth {
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
    void print() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++)
                System.out.print(cells[i][j].getPerformance());
            System.out.println();
        }
    }
}