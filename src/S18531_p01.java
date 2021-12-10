import java.util.Scanner;

public class S18531_p01 {

    public static boolean[] isWhiteTurn = new boolean[]{true};
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String[][] checkerBoard = new String[9][9];
        createCheckboard(checkerBoard);
        drawCheckboard(checkerBoard);

        while(true)
        {
            System.out.println("Ruch " + (isWhiteTurn[0] ? "Białych" : "Czarnych"));
            System.out.println("Podaj pozycję początkową");
            String start = scanner.nextLine();
            System.out.println("Podaj pozycję końcową");
            String end = scanner.nextLine();
            makeMove(start,end,checkerBoard,isWhiteTurn);
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

    public static String[][] makeMove(String startPosition, String endPosition, String[][] tab, boolean[] isWhiteTurn)
    {
        int startColumn = startPosition.toCharArray()[0] - 64;
        int startRow = 9 - Integer.parseInt(String.valueOf(startPosition.toCharArray()[1]));
        int finalColumn = endPosition.toCharArray()[0] - 64;
        int finalRow = 9 - Integer.parseInt(String.valueOf(endPosition.toCharArray()[1]));

        char figure = tab[startRow][startColumn].toCharArray()[2];
        char color = tab[startRow][startColumn].toCharArray()[1];

        boolean correctMove = false;

        switch(figure) {
            case 'P': {
                if (color == 'B') {
                    if (!isWhiteTurn[0]) {
                        //ruch
                        if (finalColumn == startColumn && (finalRow == startRow + 1 || finalRow == startRow + 2) && tab[finalRow][finalColumn].equals("|  |")) {
                            correctMove = true;
                        }

                        //bicie
                        if ((finalColumn == startColumn + 1 || finalColumn == startColumn - 1) && finalRow == startRow + 1 && tab[finalRow][finalColumn].toCharArray()[1] == 'W') {
                            correctMove = true;
                        } else {
                            System.out.println("Nieprawidlowy ruch");
                        }
                    } else {
                        System.out.println("Nie twoja kolej!");
                    }
                } else {
                    if (isWhiteTurn[0]) {
                        //ruch
                        if (finalColumn == startColumn && (finalRow == startRow - 1 || finalRow == startRow - 2) && tab[finalRow][finalColumn].equals("|  |")) {
                            correctMove = true;
                        }

                        //bicie
                        if ((finalColumn == startColumn - 1 || finalColumn == startColumn + 1) && finalRow == startRow - 1 && tab[finalRow][finalColumn].toCharArray()[1] == 'B') {
                            correctMove = true;
                        } else {
                            System.out.println("Nieprawidlowy ruch");
                        }
                    } else {
                        System.out.println("Nie twoj ruch!");
                    }
                }
                break;
            }

            case 'W': {
                 if(color == 'W' && isWhiteTurn[0])
                 {
                     //ruch wertykalny
                     if((finalColumn == startColumn) && (finalRow != finalColumn))
                     {
                         //w gore
                         if(finalRow < startRow)
                         {
                             for(int j = startRow - 1; j >= finalRow; j--)
                             {
                                 if(tab[j][finalColumn].equals("|  |"))
                                 {
                                     correctMove = true;
                                 }else
                                 {
                                     if(j == finalRow && tab[finalRow][finalColumn].toCharArray()[1] == 'B')
                                     {
                                         correctMove = true;
                                     } else {
                                         correctMove = false;
                                         break;
                                     }

                                 }
                             }

                         } else {
                             // w dol
                             for(int j = startRow + 1; j <= finalRow; j++)
                             {
                                 if(tab[j][finalColumn].equals("|  |"))
                                 {
                                     correctMove = true;
                                 }else
                                 {
                                     if(j == finalRow && tab[finalRow][finalColumn].toCharArray()[1] == 'B')
                                     {
                                         correctMove = true;
                                     }
                                     else{
                                         correctMove = false;
                                         break;
                                     }

                                 }
                             }
                         }

                     }
                     // ruch horyzontalny
                     if((finalRow == startRow) && (finalColumn != startColumn))
                     {
                         //w prawo
                         if(finalColumn > startColumn)
                         {
                             for(int j = startColumn + 1; j <= finalColumn; j++)
                             {
                                 if(tab[startRow][j].equals("|  |"))
                                 {
                                     correctMove = true;
                                 }else{
                                     if(j == finalColumn && tab[finalRow][finalColumn].toCharArray()[1] == 'B')
                                     {
                                         correctMove = true;
                                     }else
                                     {
                                         correctMove = false;
                                         break;
                                     }

                                 }
                             }
                         } else {
                             //w lewo
                             for(int j = startColumn - 1; j >= finalColumn; j--)
                             {
                                 if(tab[startRow][j].equals("|  |"))
                                 {
                                     correctMove = true;
                                 }else{
                                     if(j == finalColumn && tab[finalRow][finalColumn].toCharArray()[1] == 'B')
                                     {
                                         correctMove = true;
                                     } else
                                     {
                                         correctMove = false;
                                         break;
                                     }

                                 }
                             }
                         }
                     }
                 } else
                 {
                     if(!isWhiteTurn[0])
                     System.out.println("Nie Twoj ruch");

                 }

                 if(color == 'B' && !isWhiteTurn[0])
                 {
                     //ruch wertykalny
                     if(finalColumn == startColumn && finalRow != startRow)
                     {
                         // w dol
                         if(finalRow > startRow)
                         {
                             for(int j = startRow + 1; j <= finalRow; j++)
                             {
                                 if(tab[j][startColumn].equals("|  |"))
                                 {
                                     correctMove = true;
                                 }else{
                                     if(j == finalRow && tab[finalRow][finalColumn].toCharArray()[1] == 'W')
                                     {
                                         correctMove = true;
                                     }else{
                                         correctMove = false;
                                         break;
                                     }

                                 }
                             }
                         }else{
                             // w gore
                             for(int j = startRow - 1; j >= finalRow; j--)
                             {
                                 if(tab[j][startColumn].equals("|  |"))
                                 {
                                     correctMove = true;
                                 }else{
                                     if(j == finalRow && tab[finalRow][finalColumn].toCharArray()[1] == 'W')
                                     {
                                         correctMove = true;
                                     }
                                     else
                                     {
                                         correctMove = false;
                                         break;
                                     }

                                 }
                             }
                         }
                     }

                     //ruch horyzontalny
                     if(finalRow == startRow && finalColumn != startColumn)
                     {
                         //w lewo
                         if(finalColumn < startColumn)
                         {
                             for(int j = startColumn - 1; j >= finalColumn; j--)
                             {
                                 if(tab[startRow][j].equals("|  |"))
                                 {
                                     correctMove = true;
                                 }else
                                 {
                                     if(j == finalColumn && tab[finalRow][finalColumn].toCharArray()[1] == 'W')
                                     {
                                         correctMove = true;
                                     }
                                     else {
                                         correctMove = false;
                                         break;
                                     }
                                 }
                             }
                         }else{
                             //w prawo
                             for(int j = startColumn + 1; j <= finalColumn; j++)
                             {
                                 if(tab[startRow][j].equals("|  |"))
                                 {
                                     correctMove = true;
                                 }else
                                 {
                                     if(j == finalColumn && tab[finalRow][finalColumn].toCharArray()[1] == 'W')
                                     {
                                         correctMove = true;
                                     }else {
                                         correctMove = false;
                                         break;
                                     }
                                 }
                             }
                         }
                     }
                 }else
                 {
                     if(isWhiteTurn[0])
                         System.out.println("Nie Twoja kolej");
                 }


                }

            case 'S': {
                if(color == 'W' && isWhiteTurn[0])
                {
                    if(finalRow == startRow + 2 || finalRow == startRow + 1 || finalRow == startRow - 2 || finalRow == startRow - 1)
                    {
                        if(finalColumn == startColumn - 1 || finalColumn == startColumn + 1 || finalColumn == startColumn - 2 || finalColumn == startColumn + 2)
                        {
                           if(tab[finalRow][finalColumn].equals("|  |"))
                           {
                               correctMove = true;
                           }else
                           {
                               if(tab[finalRow][finalColumn].toCharArray()[1] == 'B')
                               {
                                   correctMove = true;
                               }
                           }
                        }
                    }

                }else
                {
                    if(isWhiteTurn[0])
                        System.out.println("Nie Twoja kolej");
                }

                if(color == 'B' && !isWhiteTurn[0])
                {

                    if(finalRow == startRow - 2 || finalRow == startRow - 1 || finalRow == startRow + 1 || finalRow == startRow + 2)
                    {
                        if(finalColumn == startColumn + 1 || finalColumn == startColumn + 2 || finalColumn == startColumn - 1 || finalColumn == startColumn - 2)
                        {
                            if(tab[finalRow][finalColumn].equals("|  |"))
                            {
                                correctMove = true;
                            }else
                            {
                                if(tab[finalRow][finalColumn].toCharArray()[1] == 'W')
                                {
                                    correctMove = true;
                                }else
                                {
                                    correctMove = false;
                                }
                            }
                        }
                    }
                }else
                {
                    if(isWhiteTurn[0])
                        System.out.println("Nie Twoja kolej");
                }
            }

            case 'K': {

                if(color == 'W' && isWhiteTurn[0])
                {
                    if(finalRow == startRow + 1 || finalRow == startRow - 1 || finalRow == startRow)
                    {
                        if(finalColumn == startColumn || finalColumn == startColumn - 1 || finalColumn == startColumn + 1)
                        {
                            if(tab[finalRow][finalColumn].equals("|  |") || tab[finalRow][finalColumn].toCharArray()[1] == 'B')
                                correctMove = true;
                        }
                    }
                }else
                {
                    if(!isWhiteTurn[0])
                        System.out.println("Nie Twoja kolej");
                }

                if(color == 'B' && !isWhiteTurn[0])
                {
                    if(finalRow == startRow + 1 || finalRow == startRow - 1 || finalRow == startRow)
                    {
                        if(finalColumn == startColumn || finalColumn == startColumn - 1 || finalColumn == startColumn + 1)
                        {
                            if(tab[finalRow][finalColumn].equals("|  |") || tab[finalRow][finalColumn].toCharArray()[1] == 'W')
                                correctMove = true;
                        }
                    }
                } else {
                    if(isWhiteTurn[0])
                        System.out.println("Nie Twoja kolej");
                }

            }

            }




        if(correctMove) {
            tab[finalRow][finalColumn] = tab[startRow][startColumn];
            tab[startRow][startColumn] = "|  |";

            isWhiteTurn[0] = !isWhiteTurn[0];
        } else
        {
            System.out.println("Nieprawidlowy ruch");
        }

        return tab;
    }



}
