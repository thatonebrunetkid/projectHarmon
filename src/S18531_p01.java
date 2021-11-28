import java.util.Scanner;

public class S18531_p01 {

    public static Boolean isWhiteTurn = true;
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String[][] checkerBoard = new String[9][9];
        createCheckboard(checkerBoard);
        drawCheckboard(checkerBoard);

        while(true)
        {
            System.out.println("Ruch " + (isWhiteTurn ? "Białych" : "Czarnych"));
            System.out.println("Podaj pozycję początkową");
            String start = scanner.nextLine();
            System.out.println("Podaj pozycję końcową");
            String end = scanner.nextLine();
            makeMove(start,end, checkerBoard, isWhiteTurn);
            drawCheckboard(checkerBoard);
        }

    }


    private static void drawCheckboard(String[][] tab)
    {
      for(String[] item : tab)
      {
          for(String value : item)
          {
              System.out.print(value);
          }
          System.out.println();
      }
    }

    private static void createCheckboard(String[][] tab)
    {
        tab[0][0] = "X ";
        for(int j = 1; j < tab.length; j++) {
            tab[j][0] = String.valueOf((char) ('9' - j)) + " ";
        }
        for(int j = 1; j < tab.length; j++)
        {
            tab[0][j] = String.valueOf("|" + (char) ('A' + j-1)) + " |";
        }

        for(int j = 1; j < tab.length; j++)
        {
            for(int i = 1; i<tab[j].length; i++)
            {
                tab[j][i] = "|  |";
            }
        }

        String[] whiteFigures = new String[]{"|WP|", "|WP|", "|WP|", "|WP|", "|WP|", "|WP|", "|WP|", "|WP|", "|WW|", "|WS|", "|WL|", "|WQ|", "|WK|", "|WL|", "|Ws|", "|WW|"};
        String[] blackFigures = new String[]{"|BW|", "|BS|", "|BL|", "|BQ|", "|BK|", "|BL|", "|BS|", "|BW|", "|BP|", "|BP|", "|BP|", "|BP|", "|BP|", "|BP|", "|BP|", "|BP|"};
        int counter = 0;
        for(int j = 1; j<=2; j++)
        {
            for(int i = 1; i<tab[j].length; i++)
            {
                tab[j][i] = blackFigures[counter];
                counter++;
            }
        }

        counter = 0;
        for(int j = 7; j <= 8; j++)
        {
            for(int i = 1; i < tab[j].length; i++)
            {
                tab[j][i] = whiteFigures[counter];
                counter++;
            }
        }

    }

    private static String[][] makeMove(String startPosition, String endPosition, String[][] tab, Boolean isWhiteTurn)
    {
        int startColumn = startPosition.toCharArray()[0] - 64;
        int startRow = 9 - Integer.parseInt(String.valueOf(startPosition.toCharArray()[1]));
        int finalColumn = endPosition.toCharArray()[0] - 64;
        int finalRow = 9 - Integer.parseInt(String.valueOf(endPosition.toCharArray()[1]));

        char figure = tab[startRow][startColumn].toCharArray()[2];
        char color = tab[startRow][startColumn].toCharArray()[1];

        boolean correctMove = false;

        switch(figure)
        {
            case 'P':
            {
                if(color == 'B')
                {
                    if(!isWhiteTurn) {
                        //ruch
                        if (finalColumn == startColumn && (finalRow == startRow + 1 || finalRow == startRow + 2) && tab[finalRow][finalColumn].equals("|  |")) {
                            correctMove = true;
                        }

                        //bicie
                        if ((finalColumn == startColumn + 1 || finalColumn == startColumn - 1) && finalRow == startRow + 1 && tab[finalRow][finalColumn].toCharArray()[1] == 'W') {
                            correctMove = true;
                        }else {
                            System.out.println("Nieprawidlowy ruch");
                        }
                    } else {
                        System.out.println("Nie twoja kolej!");
                    }
                } else
                {
                    if(isWhiteTurn)
                    {
                        //ruch
                        if(finalColumn == startColumn && (finalRow == startRow - 1 || finalRow == startRow - 2) && tab[finalRow][finalColumn].equals("|  |"))
                        {
                            correctMove = true;
                        }

                        //bicie
                        if((finalColumn == startColumn - 1 || finalColumn == startColumn - 2) && finalRow == startColumn - 1 && tab[finalRow][finalColumn].toCharArray()[1] == 'B')
                        {
                            correctMove = true;
                        }else
                        {
                            System.out.println("Nieprawidlowy ruch");
                        }
                    } else {
                        System.out.println("Nie twoj ruch!");
                    }
                }
            }
        }



        if(correctMove) {
            tab[finalRow][finalColumn] = tab[startRow][startColumn];
            tab[startRow][startColumn] = "|  |";

            isWhiteTurn = !isWhiteTurn;
        }

        return tab;
    }

}
