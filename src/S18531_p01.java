import org.xml.sax.ext.Attributes2;

import java.util.Scanner;

public class S18531_p01 {

    public static boolean[] isWhiteTurn = new boolean[]{true};
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String[][] checkerBoard = new String[9][9];
        createCheckboard(checkerBoard);

        while(true)
        {
            System.out.println("Ruch " + (isWhiteTurn[0] ? "Białych" : "Czarnych"));
            sprawdzRoszade(checkerBoard);
            drawCheckboard(checkerBoard);
            System.out.println("Podaj pozycję początkową");
            String start = scanner.nextLine();
            System.out.println("Podaj pozycję końcową");
            String end = scanner.nextLine();
            makeMove(start,end,checkerBoard,isWhiteTurn);

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

        String[] whiteFigures = new String[]{"|WP|", "|WP|", "|WP|", "|WP|", "|WP|", "|WP|", "|WP|", "|WP|", "|WW|", "|WS|", "|WL|", "|WQ|", "|WK|", "|WL|", "|WS|", "|WW|"};
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

    public static String[][] makeMove(String startPosition, String endPosition, String[][] tab, boolean[] isWhiteTurn)
    {
        int startColumn = startPosition.toCharArray()[0] - 64;
        int startRow = 9 - Integer.parseInt(String.valueOf(startPosition.toCharArray()[1]));
        int finalColumn = endPosition.toCharArray()[0] - 64;
        int finalRow = 9 - Integer.parseInt(String.valueOf(endPosition.toCharArray()[1]));

        char figure = tab[startRow][startColumn].toCharArray()[2];
        char color = tab[startRow][startColumn].toCharArray()[1];

        boolean correctMove = false;
        String[] lastDefeated = new String[1];

        if(finalColumn < 1 || finalColumn > 8 || finalRow < 1 || finalRow > 8)
        {
            System.out.println("Ruch poza szachownica");
           correctMove = false;
        }


        if(color == 'W' && isWhiteTurn[0])
        {
            if(tab[finalRow][finalColumn].equals("|  |") || tab[finalRow][finalColumn].toCharArray()[1] == 'B') {
                correctMove = true;
                lastDefeated[0] = tab[finalRow][finalColumn];
            }
        } else if (color == 'B' && !isWhiteTurn[0])
        {
            if(tab[finalRow][finalColumn].equals("|  |") || tab[finalRow][finalColumn].toCharArray()[1] == 'W') {
                correctMove = true;
                lastDefeated[0] = tab[finalRow][finalColumn];
            }
        }else
        {
            System.out.println("Nie Twoja kolej");
        }

        switch(figure) {
            case 'P': {

                if(correctMove)
                {
                    if(Math.abs(finalRow - startRow) == 1 || Math.abs(finalRow - startRow) == 2)
                    {
                        correctMove = true;
                    }else
                    {
                        correctMove = false;
                    }

                    if(Math.abs(finalRow - startRow) == 1 && Math.abs(finalColumn - startColumn) == 1)
                    {
                        if(color == 'W')
                        {
                            if(tab[finalRow][finalColumn].toCharArray()[1] == 'B')
                                correctMove = true;
                            else
                                correctMove = false;
                        } else
                        {
                            if(tab[finalRow][finalColumn].toCharArray()[1] == 'W')
                                correctMove = true;
                            else
                                correctMove = false;
                        }
                    }

                    if(isWhiteTurn[0])
                    {
                        if(finalRow > startRow)
                            correctMove = false;
                    }else
                    {
                        if(finalRow < startRow)
                            correctMove = false;
                    }


                   if(Math.abs(finalRow - startRow) == 2)
                   {
                       if(correctMove)
                       {
                           if(isWhiteTurn[0])
                           {
                               if(startColumn == 1)
                               {
                                   if(tab[finalRow][finalColumn + 1].equals("|BP|"))
                                   {
                                       System.out.println("En passant!");
                                       tab[finalRow][finalColumn] = "|  |";
                                       tab[finalRow][finalColumn + 1] = "|  |";
                                       tab[startRow][startColumn] = "|  |";
                                       tab[finalRow + 1][finalColumn] = "|BP|";
                                       isWhiteTurn[0] = !isWhiteTurn[0];
                                       return tab;
                                   }
                               }else if(startColumn == 8)
                               {
                                   if(tab[finalRow][finalColumn - 1].equals("|BP|"))
                                   {
                                       System.out.println("En passant");
                                       tab[finalRow][finalColumn] = "|  |";
                                       tab[finalRow][finalColumn - 1] = "|  |";
                                       tab[startRow][startColumn] = "|  |";
                                       tab[finalRow + 1][finalColumn] = "|BP|";
                                       isWhiteTurn[0] = !isWhiteTurn[0];
                                       return tab;
                                   }
                               }else
                               {
                                   if(tab[finalRow][finalColumn + 1].equals("|BP|"))
                                   {
                                       System.out.println("En passant!");
                                       tab[finalRow][finalColumn] = "|  |";
                                       tab[finalRow][finalColumn + 1] = "|  |";
                                       tab[startRow][startColumn] = "|  |";
                                       tab[finalRow + 1][finalColumn] = "|BP|";
                                       isWhiteTurn[0] = !isWhiteTurn[0];
                                       return tab;
                                   }
                                   if(tab[finalRow][finalColumn - 1].equals("|BP|"))
                                   {
                                       System.out.println("En passant!");
                                       tab[finalRow][finalColumn] = "|  |";
                                       tab[finalRow][finalColumn - 1] = "|  |";
                                       tab[startRow][startColumn] = "|  |";
                                       tab[finalRow + 1][finalColumn] = "|BP|";
                                       isWhiteTurn[0] = !isWhiteTurn[0];
                                       return tab;
                                   }
                               }
                           }else
                           {
                               if(startColumn == 1)
                               {
                                   if(tab[finalRow][finalColumn + 1].equals("|WP|"))
                                   {
                                       System.out.println("En passant");
                                       tab[startRow][startColumn] = "|  |";
                                       tab[finalRow][finalColumn] = "|  |";
                                       tab[finalRow][finalColumn + 1] = "|  |";
                                       tab[finalRow - 1][finalColumn] = "|WP|";
                                       isWhiteTurn[0] = !isWhiteTurn[0];
                                       return tab;
                                   }
                               }else if(startColumn == 8)
                               {
                                   if(tab[finalRow][finalColumn - 1].equals("|WP|"))
                                   {
                                       System.out.println("En passant!");
                                       tab[startRow][startColumn] = "|  |";
                                       tab[finalRow][finalColumn] = "|  |";
                                       tab[finalRow][finalColumn - 1] = "|  |";
                                       tab[finalRow - 1][finalColumn] = "|WP|";
                                       isWhiteTurn[0] = !isWhiteTurn[0];
                                       return tab;
                                   }
                               }else
                               {
                                   if(tab[finalRow][finalColumn + 1].equals("|WP|"))
                                   {
                                       System.out.println("En passant");
                                       tab[startRow][startColumn] = "|  |";
                                       tab[finalRow][finalColumn] = "|  |";
                                       tab[finalRow][finalColumn + 1] = "|  |";
                                       tab[finalRow - 1][finalColumn] = "|WP|";
                                       isWhiteTurn[0] = !isWhiteTurn[0];
                                       return tab;
                                   }

                                   if(tab[finalRow][finalColumn - 1].equals("|WP|"))
                                   {
                                       System.out.println("En passant!");
                                       tab[startRow][startColumn] = "|  |";
                                       tab[finalRow][finalColumn] = "|  |";
                                       tab[finalRow][finalColumn - 1] = "|  |";
                                       tab[finalRow - 1][finalColumn] = "|WP|";
                                       isWhiteTurn[0] = !isWhiteTurn[0];
                                       return tab;
                                   }
                               }
                           }
                       }
                   }
                }

                break;
            }

            case 'W': {
                if(correctMove)
                {
                    if(finalColumn == startColumn && finalRow != startRow)
                    {
                        if(finalRow < startRow)
                        {
                            int row = startRow - 1;
                            int column = finalColumn;

                            while(row > finalRow)
                            {
                                if(!tab[row][column].equals("|  |"))
                                    correctMove = false;

                                row--;
                            }
                        }
                        if (finalRow > startRow)
                        {
                            int row = startRow + 1;
                            int column = finalColumn;
                            while(row < finalRow)
                            {
                                if(!tab[row][column].equals("|  |"))
                                    correctMove = false;

                                row++;
                            }
                        }
                    }else if(finalRow == startRow && finalColumn != startColumn)
                    {
                        if(finalColumn > startColumn)
                        {
                            int row = startRow;
                            int column = startColumn + 1;
                            while(column < finalColumn)
                            {
                                if(!tab[row][column].equals("|  |"))
                                    correctMove = false;

                                column++;
                            }
                        }

                        if(finalColumn < startColumn)
                        {
                            int row = startRow;
                            int column = startColumn - 1;

                            while(column > finalColumn)
                            {
                                if(!tab[row][column].equals("|  |"))
                                    correctMove = false;

                                column--;
                            }
                        }


                    }else
                        correctMove = false;
                }
                break;
            }

            case 'S': {
              if(correctMove)
              {
                  if(Math.abs(finalRow - startRow) == 1 || Math.abs(finalRow - startRow) == 2)
                  {
                      if(Math.abs(finalColumn - startColumn) == 1 || Math.abs(finalColumn - startColumn) == 2)
                      {
                          correctMove = true;
                      } else
                      {
                          correctMove = false;
                      }
                  } else
                  {
                      correctMove = false;
                  }
              }
              break;
            }

            case 'K': {

                if(correctMove)
                {
                    if(Math.abs(finalColumn - startColumn) == 1 || Math.abs(finalRow - startRow) == 1 || (Math.abs(finalColumn - startColumn) == 1 && Math.abs(finalRow - startRow) == 1))
                        correctMove = true;
                    else
                        correctMove = false;
                }

                break;
            }

            case 'L' : {

                if(Math.abs(finalRow - startRow) == Math.abs(finalColumn - startColumn))
                {
                    if(correctMove)
                    {
                        //ruch w gore
                        if(finalRow < startRow) {
                            //ruch w prawo
                            if (finalColumn > startColumn) {

                                int column = startColumn;
                                int row = startRow;
                                while (column < finalColumn && row > finalRow) {
                                    if (tab[row][column].equals("|  |")) {
                                        correctMove = true;
                                    } else {
                                        correctMove = false;
                                    }
                                    column++;
                                    row--;
                                }
                            }

                            //ruch w lewo
                            if(finalColumn < startColumn)
                            {
                                int column = startColumn;
                                int row = startRow;
                                while(column > finalColumn && row > finalRow)
                                {
                                    if(tab[row][column].equals("|  |"))
                                    {
                                        correctMove = true;
                                    }else
                                    {
                                        correctMove = false;
                                    }
                                    column--;
                                    row--;
                                }
                            }
                        }

                        //ruch w dol
                        if(finalRow > startRow)
                        {
                            //ruch w prawo
                            if(finalColumn > startColumn)
                            {
                               int row = startRow;
                               int column = startColumn;
                               while(column < finalColumn && row < finalRow)
                               {
                                   if(tab[row][column].equals("|  |"))
                                   {
                                       correctMove = true;
                                   }else
                                   {
                                       correctMove = false;
                                   }
                                   row++;
                                   column++;
                               }
                            }

                            //ruch w lewo
                            if(finalColumn < startColumn)
                            {
                                int row = startRow;
                                int column = startColumn;
                                while(column > finalColumn && row < finalRow)
                                {
                                    if(tab[row][column].equals("|  |"))
                                    {
                                        correctMove = true;
                                    }else
                                    {
                                        correctMove = false;
                                    }
                                    column--;
                                    row++;
                                }
                            }
                        }
                    }
                }
                break;

            }

            case 'Q':{
                if(correctMove)
                {
                    if(finalColumn == startColumn && finalRow != startRow)
                    {
                        if(finalRow < startRow)
                        {
                            int row = startRow - 1;
                            int column = finalColumn;

                            while(row > finalRow)
                            {
                                if(!tab[row][column].equals("|  |"))
                                    correctMove = false;
                                row--;
                            }
                        }
                        if(finalRow > startRow)
                        {
                            int row = startRow + 1;
                            int column = startColumn;

                            while(row < finalRow)
                            {
                                if(!tab[row][column].equals("|  |"))
                                    correctMove = false;
                                row++;
                            }
                        }
                    }else if(finalColumn != startColumn && finalRow == startRow)
                    {
                        if(finalColumn > startColumn)
                        {
                            int row = startRow;
                            int column = startColumn + 1;
                            while(column < finalColumn)
                            {
                                if(!tab[row][column].equals("|  |"))
                                    correctMove = false;
                                column++;
                            }
                        }

                        if(finalColumn < startColumn)
                        {
                            int row = startRow;
                            int column = startColumn - 1;
                            while(column > finalColumn)
                            {
                                if(!tab[row][column].equals("|  |"))
                                    correctMove = false;
                                column--;
                            }
                        }
                    }else if(Math.abs(finalColumn - startColumn) == Math.abs(finalRow - startRow))
                    {
                        //w gore
                        if(finalRow < startRow)
                        {
                            //w lewo
                            if(finalColumn < startColumn)
                            {
                                int row = startRow - 1;
                                int column = startColumn - 1;
                                while(row > finalRow && column > finalColumn)
                                {
                                    if(!tab[row][column].equals("|  |"))
                                        correctMove = false;
                                    row--;
                                    column--;
                                }
                            }

                            //w prawo
                            if(finalColumn > startColumn)
                            {
                                int row = startRow - 1;
                                int column = startColumn + 1;
                                while(row > finalRow && column < finalColumn)
                                {
                                   if(!tab[row][column].equals("|  |"))
                                       correctMove = false;
                                   row--;
                                   column++;
                                }
                            }
                        }//w dol
                        else if(finalRow > startRow)
                        {
                            //w prawo
                            if(finalColumn > startColumn)
                            {
                                int row = startRow + 1;
                                int column = startColumn + 1;
                                while(row < finalRow && column < finalColumn)
                                {
                                    if(!tab[row][column].equals("|  |"))
                                        correctMove = false;
                                    row++;
                                    column++;
                                }
                            }

                            //w lewo
                            if(finalColumn < startColumn)
                            {
                                int row = startRow + 1;
                                int column = startColumn - 1;
                                while(row < finalRow && column > finalColumn)
                                {
                                    if(!tab[row][column].equals("|  |"))
                                        correctMove = false;
                                    row++;
                                    column--;
                                }
                            }
                        }else{
                            correctMove = false;
                        }
                    }
                }
                break;
            }

            }






        if(correctMove) {
            tab[finalRow][finalColumn] = tab[startRow][startColumn];
            tab[startRow][startColumn] = "|  |";

            if(lastDefeated[0].toCharArray()[2] == 'K')
            {
                System.out.println("Szach mat. Król poległ");
                System.exit(0);
            }

            isWhiteTurn[0] = !isWhiteTurn[0];
        } else
        {
            System.out.println("Nieprawidlowy ruch");
        }

        return tab;
    }

    public static void sprawdzRoszade(String[][] tab)
    {
        boolean roszada;
        Scanner scanner = new Scanner(System.in);
        String decision;

       if(isWhiteTurn[0])
       {
           if(tab[8][5].toCharArray()[2] == 'K' && tab[8][8].toCharArray()[2] == 'W')
           {
               roszada = true;
               for(int j = 6; j < 8; j++)
               {
                   if(!tab[8][j].equals("|  |"))
                       roszada = false;
               }


               if(roszada)
               {
                   System.out.println("Dostepna roszada w kolorze bialym. Wykonac? Y N");
                   decision = scanner.nextLine();
                   if(decision.equals("Y"))
                   {
                       tab[8][7] = "|WK|";
                       tab[8][6] = "|WW|";
                       tab[8][5] = "|  |";
                       tab[8][8] = "|  |";
                       isWhiteTurn[0] = !isWhiteTurn[0];
                   }
               }
           }

           if(tab[8][1].toCharArray()[2] == 'W' && tab[8][5].toCharArray()[2] == 'K')
           {
               roszada = true;
               for(int j = 2; j < 5; j++)
               {
                   if(!tab[8][j].equals("|  |"))
                       roszada = false;
               }

               if(roszada)
               {
                   System.out.println("Dostepna roszada w kolorze bialym. Wykonac? Y N");
                   decision = scanner.nextLine();
                   if(decision.equals("Y"))
                   {
                       tab[8][3] = "|WK|";
                       tab[8][4] = "|WW|";
                       tab[8][5] = "|  |";
                       tab[8][1] = "|  |";
                       isWhiteTurn[0] = !isWhiteTurn[0];
                   }
               }
           }
       }

       if(!isWhiteTurn[0])
       {
           if(tab[1][1].toCharArray()[2] == 'W' && tab[1][5].toCharArray()[2] == 'K')
           {
               roszada = true;
               for(int j = 2; j < 5; j++)
               {
                   if(!tab[1][j].equals("|  |"))
                       roszada = false;
               }

               if(roszada)
               {
                   System.out.println("Dostepna roszada w kolorze czarnym. Wykonac? Y N");
                   decision = scanner.nextLine();
                   if(decision.equals("Y"))
                   {
                       tab[1][2] = "|WK|";
                       tab[1][3] = "|WW|";
                       tab[1][5] = "|  |";
                       tab[1][1] = "|  |";
                       isWhiteTurn[0] = !isWhiteTurn[0];
                   }
               }
           }

           if(tab[1][5].toCharArray()[2] == 'K' && tab[1][8].toCharArray()[2] == 'W')
           {
               roszada = true;
               for(int j = 6; j < 8; j++)
               {
                   if(!tab[1][j].equals("|  |"))
                       roszada = false;
               }

               if(roszada)
               {
                   System.out.println("Dostepna roszada w kolorze czarnym. Wykonac? Y N");
                   decision = scanner.nextLine();
                   if(decision.equals("Y"))
                   {
                       tab[1][7] = "|WK|";
                       tab[1][6] = "|WW|";
                       tab[1][8] = "|  |";
                       tab[1][5] = "|  |";
                       isWhiteTurn[0] = !isWhiteTurn[0];
                   }
               }
           }
       }
    }
}
