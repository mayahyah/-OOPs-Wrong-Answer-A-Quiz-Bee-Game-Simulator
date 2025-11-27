import java.util.*;

class GameLevels {

    private Player player;
    private Scanner scanner = new Scanner(System.in);

    // ANSI COLORS
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    private static final String MAGENTA = "\u001B[35m";
    private static final String BLUE = "\u001B[34m";

    public GameLevels(Player player) {
        this.player = player;
    }

    // ==========================
    // CLEAR SCREEN METHOD
    // ==========================
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // failsafe
            System.out.println("\n\n");
        }
    }

    // ==========================
    // LEVEL SELECTION MENU
    // ==========================
    public void showLevelMenu() {
        while (true) {

            clearScreen();
            printlnC(YELLOW, " __    ____  _  _  ____  __        ___  ____  __    ____   ___  ____  ____  _____  _  _ \r\n" + //
                                "(  )  ( ___)( \\/ )( ___)(  )      / __)( ___)(  )  ( ___) / __)(_  _)(_  _)(  _  )( \\( )\r\n" + //
                                " )(__  )__)  \\  /  )__)  )(__     \\__ \\ )__)  )(__  )__) ( (__   )(   _)(_  )(_)(  )  ( \r\n" + //
                                "(____)(____)  \\/  (____)(____)    (___/(____)(____)(____) \\___) (__) (____)(_____)(_)\\_)\r\n" + //
                                "");

            printlnC(YELLOW, "Unlocked Levels: " + player.getUnlockedLevels());
            System.out.print(RESET + "\nChoose a level (1-20) or 0 to quit: ");

            int level = readInt();

            if (level == 0) {
                printlnC(CYAN, "\nExiting game...");
                return;
            }

            if (!player.isUnlocked(level)) {
                printlnC(RED, "\nThis level is locked. Clear the previous one first.");
                pressEnter();
                continue;
            }

            // ▼ Scientist selection screen
            Scientists scientist = selectScientist();

            player.setScientist(scientist);

            boolean cleared = playLevel(level);

            if (cleared) {
                printlnC(GREEN, "\nLevel " + level + " cleared!");

                if (level < 20)
                    player.unlock(level + 1);

                pressEnter();
            } else {
                printlnC(RED, "\nYou failed the level...");
                pressEnter();
            }
        }
    }

    // ==========================
    // SCIENTIST SELECTION
    // ==========================
    private Scientists selectScientist() {
        while (true) {
            clearScreen();
            printlnC(MAGENTA, "  ___  _   _  _____  _____  ___  ____      _  _  _____  __  __  ____      ___   ___  ____  ____  _  _  ____  ____  ___  ____ \r\n" + //
                                " / __)( )_( )(  _  )(  _  )/ __)( ___)    ( \\/ )(  _  )(  )(  )(  _ \\    / __) / __)(_  _)( ___)( \\( )(_  _)(_  _)/ __)(_  _)\r\n" + //
                                "( (__  ) _ (  )(_)(  )(_)( \\__ \\ )__)      \\  /  )(_)(  )(__)(  )   /    \\__ \\( (__  _)(_  )__)  )  (   )(   _)(_ \\__ \\  )(  \r\n" + //
                                " \\___)(_) (_)(_____)(_____)(___/(____)     (__) (_____)(______)(_)\\_)    (___/ \\___)(____)(____)(_)\\_) (__) (____)(___/ (__) \r\n" + //
                                "");

            System.out.println(
                CYAN +
                "\t\t\t\t\t1.) Galileo Galilei - Astronomy Hints\n" +
                "\t\t\t\t\t2.) Isaac Newton - Skip Physics (2 uses)\n" +
                "\t\t\t\t\t3.) Charles Darwin - Mimics random ability every question\n" +
                "\t\t\t\t\t4.) Sigmund Freud - Reduces 5 questions per level\n" +
                "\t\t\t\t\t5.) Marie Curie - +30 Health at start of level\n" +
                "\t\t\t\t\t6.) Ada Lovelace - +5 Health on correct answer\n" +
                RESET
            );

            System.out.print("Enter choice (1-5): ");
            int c = readInt();

            switch (c) {
                case 1:
                    clearScreen();
                    System.out.println(GREEN + "Special Ability Activated:\n");
                    System.out.println(GREEN + "     ▀█▀ ██▀ █   ██▀ ▄▀▀ ▄▀▀ ▄▀▄ █▀▄ ██▀   ▄▀▄ █▀   ▀█▀ █▀▄ █ █ ▀█▀ █▄█\r\n" + //
                                                "      █  █▄▄ █▄▄ █▄▄ ▄██ ▀▄▄ ▀▄▀ █▀  █▄▄   ▀▄▀ █▀    █  █▀▄ ▀▄█  █  █ █\r" + //
                                                "");
                    System.out.println(GREEN + "\"As the great Galileo Galilei, you will receive hints for astronomy questions!\"" + RESET);
                    pressEnter();
                    return new Scientists("Galileo Galilei", 100,
                        Scientists.Ability.HINT,
                        "Provides hints for astronomy questions.");
                case 2:
                    clearScreen();
                    System.out.println(GREEN + "Special Ability Activated:\n");
                    System.out.println(GREEN + "             █▄ █ ▄▀▄ ▀█▀ █ █ █▀▄ ██▀ ▀ ▄▀▀     █ █ █ █▀▄ ▄▀  ██▀\r\n" + //
                                                "             █ ▀█ █▀█  █  ▀▄█ █▀▄ █▄▄  ▄██    ▀▄█ ▀▄█ █▄▀ ▀▄█ █▄▄\r" + //
                                                "");
                    System.out.println(GREEN + "\"With excellent mastery of physics, you can skip physics questions twice per level!\"" + RESET);
                    pressEnter();
                    return new Scientists("Isaac Newton", 100,
                        Scientists.Ability.SKIP_QUESTION,
                        "Can skip physics questions (2x per level).");
                case 3:
                    clearScreen();
                    System.out.println(GREEN + "Special Ability Activated:\n");
                    System.out.println(GREEN + "                  ██▀ █ █ ▄▀▄ █   █ █ ▀█▀ █ ▄▀▄ █▄ █ ▄▀▄ █▀▄ ▀▄▀\r\n" + //
                                                "                  █▄▄ ▀▄▀ ▀▄▀ █▄▄ ▀▄█  █  █ ▀▄▀ █ ▀█ █▀█ █▀▄  █ \r" + //
                                                "");
                    System.out.println(GREEN + "\"The power of evolution grants you to copy another scientist's ability each question!\"" + RESET);
                    pressEnter();
                    return new Scientists("Charles Darwin", 100,
                        Scientists.Ability.COPY_ABILITY,
                        "Randomly copies another scientist’s ability each question.");
                case 4:
                    clearScreen();
                    System.out.println(GREEN + "Special Ability Activated:\n");
                    System.out.println(GREEN + "     ██▄ █▀▄ ▄▀▄ █ █▄ █   ▄▀▄ █▀   ▀█▀ ▄▀▄ █▄ ▄█ ▄▀▄ █▀▄ █▀▄ ▄▀▄ █   █\r\n" + //
                                                "     █▄█ █▀▄ █▀█ █ █ ▀█   ▀▄▀ █▀    █  ▀▄▀ █ ▀ █ ▀▄▀ █▀▄ █▀▄ ▀▄▀ ▀▄▀▄▀\r" + //
                                                "");
                    System.out.println(GREEN + "\"The number of questions are decreased by 5 as you gain the power of Freud!\"" + RESET);
                    pressEnter();
                    return new Scientists("Sigmund Freud", 100,
                        Scientists.Ability.REDUCE_QUESTION,
                        "Reduces the total number of questions by 5.");
                case 5:
                     clearScreen();
                    System.out.println(GREEN + "Special Ability Activated:\n");
                    System.out.println(GREEN + "             █▀▄ ▄▀▄ █▀▄ █ ▄▀▄ █▄ █ ▄▀▀ ██▀   ▄▀▀ █ █ █▀▄ █ ▄▀▄ █ █ ▄▀▀ █ ▀█▀ ▀▄▀\r\n" + //
                                                "             █▀▄ █▀█ █▄▀ █ █▀█ █ ▀█ ▀▄▄ █▄▄   ▀▄▄ ▀▄█ █▀▄ █ ▀▄▀ ▀▄█ ▄██ █  █   █ \r" + //
                                                "");
                    System.out.println(GREEN + "\"With Marie Curie's brilliance and curiousity, you gain +30 health at the start of each level!\"" + RESET);
                    pressEnter();
                    return new Scientists("Marie Curie", 100,
                        Scientists.Ability.EXTRA_LIFE,
                        "Adds +30 health at the start of each level.");
                case 6:
                    clearScreen();
                    System.out.println(GREEN + "Special Ability Activated:\n");
                    System.out.println(GREEN + "                    █   ▄▀▄ █ █ ██▀   █   ▄▀▄ ▄▀▄ █▀▄ ▄▀▀\r\n" + //
                                                "                    █▄▄ ▀▄▀ ▀▄▀ █▄▄   █▄▄ ▀▄▀ ▀▄▀ █▀  ▄██\r" + //
                                                ""); 
                    System.out.println(GREEN + "\"With Ada Lovelace's intellect, you heal 5 health for every correct answer!\"" + RESET);
                    pressEnter();
                    return new Scientists("Ada Lovelace", 100,
                        Scientists.Ability.HEAL,
                        "Heals +5 health on each correct answer."); 
                default:
                    printlnC(RED, "\nInvalid choice.");
                    pressEnter();
            }
        }
    }

    // ==========================
    // PLAY LEVEL
    // ==========================
    public boolean playLevel(int level) {

        clearScreen();

        Scientists s = player.getScientist();
        s.resetPerLevelFlags();

        // Determine number of questions
        int questionCount = (level == 5 || level == 10 ? 20 : 10);

        // Freud effect
        if (s.getAbility() == Scientists.Ability.REDUCE_QUESTION) {
            questionCount = Math.max(5, questionCount - 5);
        }

        // Curie effect (+30 health)
        if (s.getAbility() == Scientists.Ability.EXTRA_LIFE &&
            !s.isExtraLifeGrantedThisLevel()) {

            s.modifyHealth(+30);
            s.setExtraLifeGranted(true);
        }

        // Build question list
        List<Question> pool = QuestionBank.allQuestions();
        Collections.shuffle(pool);

        List<Question> questions = QuestionBank.generateLevel(pool, questionCount);

        int number = 1;

        for (Question q : questions) {

            clearScreen();
            //For Marie Curie to display extra health activation
            if (s.getAbility() == Scientists.Ability.EXTRA_LIFE) {
                printlnC(YELLOW, "Radiance Curiosity Activated!");
                printlnC(GREEN, "Current Health: " + s.getHealth());
            }

            printlnC(YELLOW, "\nQuestion " + number + " [" + q.getCategory() + "]");
            System.out.println(q.getText());
            for (String c : q.getChoices()) {
                printlnC(CYAN, c);
            }

            // ------------------------
            // Darwin copies a new ability every question
            // ------------------------
            Scientists.Ability copied = Scientists.Ability.NONE;
            if (s.getAbility() == Scientists.Ability.COPY_ABILITY) {

                Scientists.Ability[] list = {
                        Scientists.Ability.HINT,
                        Scientists.Ability.SKIP_QUESTION,
                        Scientists.Ability.HEAL,
                };

                copied = list[new Random().nextInt(list.length)];

                printlnC(MAGENTA, "(Darwin mimics: " + copied + ")");
            }

            // ------------------------
            // Galileo hint OR Darwin hint
            // ------------------------
            if (q.getCategory().equals("Astronomy") &&
                    (s.getAbility() == Scientists.Ability.HINT || copied == Scientists.Ability.HINT)) {

                printlnC(YELLOW, "(Hint) Correct answer might be: " + q.getCorrect());
            }

            // ------------------------
            // Newton skip OR Darwin skip
            // ------------------------
            if (q.getCategory().equals("Physics") &&
                    (s.getAbility() == Scientists.Ability.SKIP_QUESTION || copied == Scientists.Ability.SKIP_QUESTION)
            ) {
                if (s.getSkipUses() > 0) {
                    try {
                        System.out.print(YELLOW + "Skip this physics question? (Y/N): " + RESET);
                        String sk = scanner.nextLine().trim().toLowerCase();

                        if (sk.startsWith("y")) {
                            s.useSkip();
                            number++;
                            continue;
                        }

                        if (!sk.startsWith("y") && !sk.startsWith("n")) {
                            throw new Exception("Invalid input.");
                        }
                    } catch (Exception e) {
                        printlnC(RED, "Invalid input! Proceeding to question...");
                    }
                }
            }

            // ------------------------
            // Darwin extra life
            // ------------------------
            if (copied == Scientists.Ability.EXTRA_LIFE) {
                s.modifyHealth(+10);
            }

            // ------------------------
            // Answer handling
            // ------------------------
            char ans;
            while (true) {
                try {
                    System.out.print(RESET + "Your answer: ");
                    String input = scanner.nextLine().trim().toLowerCase();

                    if (input.length() != 1)
                        throw new Exception("Input must be a single character.");

                    ans = input.charAt(0);

                    if (ans != 'a' && ans != 'b' && ans != 'c' && ans != 'd')
                        throw new Exception("Invalid character.");

                    break; // valid → exit loop
                } 
                catch (Exception e) {
                    printlnC(RED, "Invalid input! Please enter only the given options (A-D).");
                }
            }

            boolean correct = (ans == q.getCorrect());

            if (correct) {
                printlnC(GREEN, "\nCorrect!");
                player.addScore(10);

                if (s.getAbility() == Scientists.Ability.HEAL ||
                    copied == Scientists.Ability.HEAL) {
                    s.modifyHealth(+5);
                }
            } else {
                printlnC(RED, "\nWrong!");
                s.modifyHealth(-10);
            }

            printlnC(YELLOW, "Health: " + s.getHealth() + " | Score: " + player.getScore());

            if (s.getHealth() <= 0) {
                printlnC(RED, "\nYou failed this level!");
                pressEnter();
                return false;
            }

            pressEnter();
            number++;
        }

        return true;
    }

    // ==========================
    // INPUT + UTILITY
    // ==========================
    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                printlnC(RED, "Invalid input. Try again and input a number.");
            }
        }
    }

    private void pressEnter() {
        System.out.print("\n" + CYAN + "Press ENTER to continue..." + RESET);
        scanner.nextLine();
    }

    private void printlnC(String color, String text) {
        System.out.println(color + text + RESET);
    }
}
