import java.util.Objects;
import java.util.Scanner;

public class S18531_p01 {

    public static void main(String[] args)
    {
        playInitialScreen();
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        if(Objects.equals(option, "p"))
            runGame();
        else
            System.exit(0);

    }

    public static void playInitialScreen()
    {
        System.out.println("                                                        ████████ ███████ ██   ██ ████████      ██████ ██   ██ ███████ ███████ ███████                                                                  \n" +
                "                                                           ██    ██       ██ ██     ██        ██      ██   ██ ██      ██      ██                                                                       \n" +
                "                                                           ██    █████     ███      ██        ██      ███████ █████   ███████ ███████                                                                  \n" +
                "                                                           ██    ██       ██ ██     ██        ██      ██   ██ ██           ██      ██                                                                  \n" +
                "                                                           ██    ███████ ██   ██    ██         ██████ ██   ██ ███████ ███████ ███████                                                                  \n" +
                "                                                                                                                                                                                                       \n" +
                "                                                                                                                                                                                                       \n" +
                "███    ███  █████  ████████ ███████ ██    ██ ███████ ███████      █████  ██    ██  ██████  ██    ██ ███████ ████████ ██    ██ ███    ██ ██  █████  ██   ██     ███████  ██  █████  ███████ ██████   ██ \n" +
                "████  ████ ██   ██    ██    ██      ██    ██ ██         ███      ██   ██ ██    ██ ██       ██    ██ ██         ██     ██  ██  ████   ██ ██ ██   ██ ██  ██      ██      ███ ██   ██ ██           ██ ███ \n" +
                "██ ████ ██ ███████    ██    █████   ██    ██ ███████   ███       ███████ ██    ██ ██   ███ ██    ██ ███████    ██      ████   ██ ██  ██ ██ ███████ █████       ███████  ██  █████  ███████  █████   ██ \n" +
                "██  ██  ██ ██   ██    ██    ██      ██    ██      ██  ███        ██   ██ ██    ██ ██    ██ ██    ██      ██    ██       ██    ██  ██ ██ ██ ██   ██ ██  ██           ██  ██ ██   ██      ██      ██  ██ \n" +
                "██      ██ ██   ██    ██    ███████  ██████  ███████ ███████     ██   ██  ██████   ██████   ██████  ███████    ██       ██    ██   ████ ██ ██   ██ ██   ██     ███████  ██  █████  ███████ ██████   ██ \n" +
                "                                                                                                                                                                                                       \n" +
                "                                                                                                                                                                                                       \n" +
                "                                                                                                                                                                                                       \n" +
                "                                                                                                                                                                                                       \n" +
                "                                                                                                                                                                                                       \n" +
                "                                                                                                                                                                                                       \n" +
                "                                                                                                                                                                                                       \n" +
                "                                                                                                                                                                                                       \n" +
                "                            Press P to play                                                                                              Press X to exit                                                                                                                                          ");
    }

    public static void runGame()
    {
        char[][] checker = new char[9][9];
        drawCheckboard(checker);
    }

    private static void drawCheckboard(char[][] tab)
    {
      createCheckboard(tab);
      for(int j = 0; j < tab.length; j++)
      {
          for(int i = 0; i < tab[j].length; i++)
          {
              System.out.print(tab[j][i]);
          }
          System.out.println();
      }

    }

    private static char[][] createCheckboard(char[][] tab)
    {
        for(int j = 1; j < tab.length; j++) {
            tab[j][0] = (char) ('9' - j);
        }
        for(int j = 1; j < tab.length; j++)
        {
            tab[0][j] = (char) ('A' + j-1);
        }


        return tab;
    }



}
