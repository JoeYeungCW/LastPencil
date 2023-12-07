package lastpencil;

import java.util.Random;
import java.util.Scanner;

public class GamePlay {


    private Player currentPlayer;
    private final Player John, Jack;
    Scanner scanner = new Scanner(System.in);
    private int pencil;
    private Random random = new Random();

    public GamePlay(){
        currentPlayer = new Player("");
        John = new Player("John");
        Jack = new Player("Jack");
    }
    public void play() {
        askNumberOfPencil();
    }

    private void askNumberOfPencil() {
            System.out.println("How many pencils would you like to use:");
            String input = scanner.nextLine();
            if (!input.matches("\\d+")) {
                System.out.println("The number of pencils should be numeric");
                askNumberOfPencil();
            }
            pencil = Integer.parseInt(input);
            if (pencil > 0) {
                askPlayer();
            }
            else {
                System.out.println("The number of pencils should be positive");
                askNumberOfPencil();
            }
    }

    private void askPlayer() {
        System.out.println("Who will be the first (John, Jack):");
        String input = scanner.nextLine();
        if(John.getPlayer().equals(input)||Jack.getPlayer().equals(input)) {
            currentPlayer = John.getPlayer().equals(input) ? John : Jack;
        }
        else {
            System.out.println("Choose between 'John' and 'Jack'");
            askPlayer();
        }
        printPencil();
    }


    private void printPencil() {
        if (pencil > 0) {
            System.out.println("|".repeat(pencil));
            showPlayer();
        }
        else {
            printWinner();
        }
    }

    private void showPlayer() {
        System.out.printf("%S's turn\n", currentPlayer.getPlayer());
        if (currentPlayer.getPlayer().equals(John.getPlayer())) {
            playerTurn();
        }
        else {
            botTurn();
        }
    }
    private void playerTurn() {
        String takePencil;
        takePencil = scanner.nextLine();

        if (!takePencil.matches("[123]")) {
            System.out.println("\"Possible values: '1', '2' or '3'\"");
            playerTurn();
        }

        int NumOfTakePencil = Integer.parseInt(takePencil);
            if (pencil >= NumOfTakePencil) {
                pencil -= NumOfTakePencil;
                currentPlayer = Jack;
                printPencil();
            }
            else {
                System.out.println("Too many pencils were taken");
                playerTurn();
        }
    }

    private void botTurn() {
        int takePencil;
        currentPlayer = John;
        if (pencil == 1) {
            takePencil = 1;
            System.out.println(takePencil);
            pencil -= takePencil;
            printPencil();
        }
        if (pencil % 4 == 0) {
            takePencil = 3;
            System.out.println(takePencil);
            pencil -= takePencil;
            printPencil();
        }
        else if (pencil % 4 == 1) {
            takePencil = random.nextInt( 2) + 1;
            System.out.println(takePencil);
            pencil -= takePencil;
            printPencil();
        }
        else if (pencil % 4 == 2) {
            takePencil = 1;
            System.out.println(takePencil);
            pencil -= takePencil;
            printPencil();
        }
        else if (pencil % 4 == 3) {
            takePencil = 2;
            System.out.println(takePencil);
            pencil -= takePencil;
            printPencil();
        }
    }

    private void printWinner() {
        System.out.printf("%S won!", currentPlayer.getPlayer());
        System.exit(0);
    }
    }