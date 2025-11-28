import java.util.Scanner;

public class Main {

    private static final String CYAN = "\u001B[36m";
    private static final String RESET = "\u001B[0m";

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("\n\n");
        }
    }

    private static void pressEnter() {
        System.out.print("\nPress ENTER to continue..." + RESET);
        new Scanner(System.in).nextLine();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // =============================
        // TITLE SCREEN
        // =============================
        clearScreen();

        System.out.println(CYAN);
        System.out.println("");
        System.out.println("\t\t\t     ━─━─━━─━━─━─━━─━━─━─━━─━「₪」━━─━─━─━━─━─━━─━━─━─━━─━" + //
                        "");
        System.out.println("\t\t\t\t\t _____  ___________  _____ _ \r\n" + //
                        "\t\t\t\t\t|  _  ||  _  | ___ \\/  ___| |\r\n" + //
                        "\t\t\t\t\t| | | || | | | |_/ /\\ `--.| |\r\n" + //
                        "\t\t\t\t\t| | | || | | |  __/  `--. \\ |\r\n" + //
                        "\t\t\t\t\t\\ \\_/ /\\ \\_/ / |    /\\__/ /_|\r\n" + //
                        "\t\t\t\t\t \\___/  \\___/\\_|    \\____/(_)\r");
        System.out.println("\t\t\t\t\t= A Quiz Bee Simulation Game =\n");
        System.out.print("\t\t\t     ━─━─━━─━━─━─━━─━━─━─━━─━━─━─━━─━━─━─━━─━━─━─━━─━━─━─━" + //
                        "");
        
        System.out.println(RESET);

        pressEnter();
        clearScreen();

        // Start with a temporary scientist (will be replaced per-level)
        Scientists placeholderScientist =
                new Scientists("None", 50, Scientists.Ability.NONE, "Placeholder");

        Player player = new Player();   // ✔ original behavior
        player.setScientist(placeholderScientist); // keep placeholder

        GameLevels engine = new GameLevels(player);

        // =============================
        // LEVEL SELECTION / GAME LOOP
        // =============================
        engine.showLevelMenu();

        clearScreen();
        System.out.println(CYAN + "Thanks for playing");
        System.out.println(" _____  ___________  _____ _ \r\n" + //
                        "|  _  ||  _  | ___ \\/  ___| |\r\n" + //
                        "| | | || | | | |_/ /\\ `--.| |\r\n" + //
                        "| | | || | | |  __/  `--. \\ |\r\n" + //
                        "\\ \\_/ /\\ \\_/ / |    /\\__/ /_|\r\n" + //
                        " \\___/  \\___/\\_|    \\____/(_)\r\n" + //
                        "                             \r");
        System.out.print("Goodbye!\n\n" + RESET);

        sc.close();
    }
}
