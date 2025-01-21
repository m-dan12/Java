package Labyrinth;

import java.util.ArrayList;

public class LP_Main {
    static void print(String[][] arr) {
        for (String[] strings : arr) {
            for (String str : strings)
                System.out.print(str);
            System.out.println();
            String a = "";
        }
    }
    static String[][] generateLabyrinth(int length){

        String[][] Labyrinth = new String[length][length]; // Сам лабиринт
        boolean[][] IsVisited = new boolean[length][length]; // Храним знания о том, какие ячейки посещали
        ArrayList<String> History = new ArrayList<>(); // Храним историю шагов

        //Генерация границ
        for(int i = 0; i < length; i++){
            Labyrinth[0][i] = " % ";
            Labyrinth[i][0] = " % ";
            Labyrinth[length - 1][i] = " % ";
            Labyrinth[i][length - 1] = " % ";
        }

        //Генерация сетки
        for(int i = 1; i < length - 1; i++){
            for(int j = 1; j < length - 1; j++){
                if(i % 2 == 0 || j % 2 == 0){
                    Labyrinth[i][j] = " # ";
                } else {
                    Labyrinth[i][j] = "   ";
                }
            }
        }

        //Спавним робота
        int x = 1;
        int y = 1;
        Labyrinth[y][x] = " R ";

        //Ходим по лабиринту, ломаем стены
        do{

            ArrayList<String> sideList = new ArrayList<>();

            if(Labyrinth[y][x + 1].equals(" # ") && !IsVisited[y][x + 2]) sideList.add("вправо");
            if(Labyrinth[y][x - 1].equals(" # ") && !IsVisited[y][x - 2]) sideList.add("влево");
            if(Labyrinth[y + 1][x].equals(" # ") && !IsVisited[y + 2][x]) sideList.add("вниз");
            if(Labyrinth[y - 1][x].equals(" # ") && !IsVisited[y - 2][x]) sideList.add("вверх");

            String side = !sideList.isEmpty() ? sideList.get((int)(Math.random() * sideList.size())) : null;

            Labyrinth[y][x] = "   ";
            IsVisited[y][x] = true;
            if(side != null) {
                switch(side){
                    case "вправо":
                        Labyrinth[y][x + 1] = "   ";
                        x += 2;
                        break;
                    case "влево":
                        Labyrinth[y][x - 1] = "   ";
                        x -= 2;
                        break;
                    case "вниз":
                        Labyrinth[y + 1][x] = "   ";
                        y += 2;
                        break;
                    case "вверх":
                        Labyrinth[y - 1][x] = "   ";
                        y -= 2;
                        break;
                    default:
                        break;
                }
                History.add(side);
            } else {
                switch(History.getLast()){
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
                    default:
                        break;
                }
                History.removeLast();
            }
            Labyrinth[y][x] = " R ";

        } while (!History.isEmpty());

        return Labyrinth;

    }
    static void generateK(int length, int numberOfK, String[][] Labyrinth){

        for (int i = 0; i < numberOfK; i++){
            int x, y;
            do {
                int randNumber = (int) (Math.random() * (length * length));
                y = randNumber / length;
                x = randNumber % length;
            } while(Labyrinth[y][x].equals(" # ") ||
                    Labyrinth[y][x].equals(" % ") ||
                    Labyrinth[y][x].equals(" R ") );
            Labyrinth[y][x] = " K ";
        }

    }
    public static void main(String[] args) {

        int length = 13;
        int numberOfK = 5;
        var Labyrinth = generateLabyrinth(length);
        generateK(length, numberOfK, Labyrinth);
        print(Labyrinth);

    }
}
