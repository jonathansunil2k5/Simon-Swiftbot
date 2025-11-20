import swiftbot.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimonSays {
    static SwiftBotAPI swiftBot;

    public static void main(String[] args) {

        swiftBot = SwiftBotAPI.INSTANCE;
        clearScreen();
        System.out.println("                                                                                                                 \n" +
                "                                                                                                                 \n" +
                "  .--.--.                      ____                                  .--.--.                                     \n" +
                " /  /    '.   ,--,           ,'  , `.                               /  /    '.                                   \n" +
                "|  :  /`. / ,--.'|        ,-+-,.' _ |   ,---.        ,---,         |  :  /`. /                                   \n" +
                ";  |  |--`  |  |,      ,-+-. ;   , ||  '   ,'\\   ,-+-. /  |        ;  |  |--`                         .--.--.    \n" +
                "|  :  ;_    `--'_     ,--.'|'   |  || /   /   | ,--.'|'   |        |  :  ;_      ,--.--.        .--, /  /    '   \n" +
                " \\  \\    `. ,' ,'|   |   |  ,', |  |,.   ; ,. :|   |  ,\"' |         \\  \\    `.  /       \\     /_ ./||  :  /`./   \n" +
                "  `----.   \\'  | |   |   | /  | |--' '   | |: :|   | /  | |          `----.   \\.--.  .-. | , ' , ' :|  :  ;_     \n" +
                "  __ \\  \\  ||  | :   |   : |  | ,    '   | .; :|   | |  | |          __ \\  \\  | \\__\\/: . ./___/ \\: | \\  \\    `.  \n" +
                " /  /`--'  /'  : |__ |   : |  |/     |   :    ||   | |  |/          /  /`--'  / ,\" .--.; | .  \\  ' |  `----.   \\ \n" +
                "'--'.     / |  | '.'||   | |`-'       \\   \\  / |   | |--'          '--'.     / /  /  ,.  |  \\  ;   : /  /`--'  / \n" +
                "  `--'---'  ;  :    ;|   ;/            `----'  |   |/                `--'---' ;  :   .'   \\  \\  \\  ;'--'.     /  \n" +
                "            |  ,   / '---'                     '---'                          |  ,     .-./   :  \\  \\ `--'---'   \n" +
                "             ---`-'                                                            `--`---'        \\  ' ;            \n" +
                "                                                                                                `--`             ");

        System.out.print("Enter any key to continue... or press 0 to quit: ");
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                String ans = scanner.next();
                switch (ans) {
                    case "0":
                        clearScreen();
                        System.out.println("Goodbye! :D");
                        Thread.sleep(3000);
                        System.exit(0);
                        break;
                    default:
                        startGame();
                        break;
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void startGame() {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        ArrayList<Button> buttonSequence = new ArrayList<Button>();

        //Generate inital 2 color sequence
        buttonSequence.add(getButton(random.nextInt(4)));
        buttonSequence.add(getButton(random.nextInt(4)));

        boolean continueGame = true;
        int level = 0;
        try {
            while (continueGame) {
                clearScreen();
                System.out.print("Level " + (level + 1));
                lightButtons(buttonSequence);

                continueGame = getUserInput(buttonSequence, level+1);
                if (continueGame) {
                    level++;
                    clearScreen();
                    System.out.println("Level " + level + " passed!");
                    Thread.sleep(3000);
                    //Generates random number. 0 is A, 1 is B, 3 is X, 4 is Y
                    buttonSequence.add(getButton(random.nextInt(4)));

                    if (level % 5 == 0) {
                        clearScreen();
                        System.out.println("You've passed " + level + " levels. Do you wish to continue? Y/N");
                        if (!Objects.equals(scanner.nextLine().toLowerCase(), "y")){
                            continueGame = false;
                        }
                    }
                }
                else {
                    clearScreen();
                    System.out.println("You've lost at level " +  (level + 1));
                    System.out.println("Try again? Y/N");
                    if (Objects.equals(scanner.nextLine().toLowerCase(), "y")){
                        level = 0;
                    } else continueGame = false;
                }
            }
            clearScreen();
            System.out.println("Goodbye! :D");
            Thread.sleep(3000);
            System.exit(0);
        }
        catch (Exception e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }

    public static boolean  getUserInput(ArrayList<Button> buttonSequence, int level) {
        boolean passed = true;
        ArrayList<Button> inputSequence = new ArrayList<Button>();

        AtomicBoolean done = new AtomicBoolean(false);

        swiftBot.enableButton(Button.A, () -> {
            clearScreen();
            System.out.println("Level " +  level);
            System.out.println("Button A was pressed");

            turnOnUnderlight(Button.A);
            swiftBot.toggleButtonLight(Button.A);
            try {Thread.sleep(1000);} catch (Exception e) {}
            swiftBot.disableUnderlights();
            swiftBot.toggleButtonLight(Button.A);

            inputSequence.add(Button.A);
            if (inputSequence.size() == buttonSequence.size()) {
                done.set(true);
            }
        });

        swiftBot.enableButton(Button.B, () -> {
            clearScreen();
            System.out.println("Level " +  level);
            System.out.println("Button B was pressed");

            turnOnUnderlight(Button.B);
            swiftBot.toggleButtonLight(Button.B);
            try {Thread.sleep(1000);} catch (Exception e) {}
            swiftBot.disableUnderlights();
            swiftBot.toggleButtonLight(Button.B);


            inputSequence.add(Button.B);
            if (inputSequence.size() == buttonSequence.size()) {
                done.set(true);
            }
        });

        swiftBot.enableButton(Button.X, () -> {
            clearScreen();
            System.out.println("Level " +  level);
            System.out.println("Button X was pressed");

            turnOnUnderlight(Button.X);
            swiftBot.toggleButtonLight(Button.X);
            try {Thread.sleep(1000);} catch (Exception e) {}
            swiftBot.disableUnderlights();
            swiftBot.toggleButtonLight(Button.X);

            inputSequence.add(Button.X);
            if (inputSequence.size() == buttonSequence.size()) {
                done.set(true);
            }
        });

        swiftBot.enableButton(Button.Y, () -> {
            clearScreen();
            System.out.println("Level " +  level);
            System.out.println("Button Y was pressed");

            turnOnUnderlight(Button.Y);
            swiftBot.toggleButtonLight(Button.Y);
            try {Thread.sleep(1000);} catch (Exception e) {}
            swiftBot.disableUnderlights();
            swiftBot.toggleButtonLight(Button.Y);

            inputSequence.add(Button.Y);
            if (inputSequence.size() == buttonSequence.size()) {
                done.set(true);
            }
        });

        while (!done.get()) {
            try { Thread.sleep(10); } catch (InterruptedException e) {}
        }

        swiftBot.disableAllButtons();

        if (!inputSequence.equals(buttonSequence)) {
            passed = false;
        }
        return passed;
    }


    public static void lightButtons(ArrayList<Button> buttonSequence) {
        try {
            for (Button button : buttonSequence) {
                //Turning on button light and corresponding underlight
                //A is red, B is blue, X is green, Y is yellow
                swiftBot.setButtonLight(button, true);
                turnOnUnderlight(button);

                //Wait a second
                Thread.sleep(1000);

                //Toggling the button light and turning off underlights.
                swiftBot.toggleButtonLight(button);
                swiftBot.disableUnderlights();

                // Small wait so they're completely off.
                Thread.sleep(250);
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }

    public static void turnOnUnderlight(Button button) {
        int[] red = new int[] { 255, 0, 0 };
        int[] blue = new int[] { 0, 0, 255 };
        int[] green = new int[] { 0, 255, 0 };
        int[] yellow = new int[] { 255, 255, 0 };

        try {
            if (button.equals(Button.A)) {
                swiftBot.fillUnderlights(red);
            }
            else if (button.equals(Button.B)) {
                swiftBot.fillUnderlights(blue);
            }
            else if (button.equals(Button.X)) {
                swiftBot.fillUnderlights(green);
            }
            else if (button.equals(Button.Y)) {
                swiftBot.fillUnderlights(yellow);
            }

        } catch (Exception e) {
            System.out.println("ERROR");
        }

    }

    public static Button getButton(int buttonInteger) {
        switch (buttonInteger) {
            case 0:
                return Button.A;
            case 1:
                return Button.B;
            case 2:
                return Button.X;
            case 3:
                return Button.Y;
            default:
                return null;
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
