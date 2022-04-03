package cinema;

import javax.swing.plaf.synth.SynthDesktopIconUI;
import java.util.Scanner;

public class Cinema {


    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");

        int rowLength = scanner.nextInt();

        String[][] seating = new String[rows + 1][rowLength + 1];

        for (int i = 1; i < rows + 1; i++) {
            for (int j = 1; j < rowLength + 1; j++) {
                seating[i][j] = "S";
            }
        }

        int totalSeats = rows * rowLength;

        while (true) {

            System.out.print("\n");
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int selection = scanner.nextInt();

            if (selection == 1) {
                showSeats(seating);
            } else if (selection == 2) {
                seating = checkTicket(seating);
            } else if (selection == 3) {
                showStats(seating);
            }
            else if (selection == 0) {
                break;
            }
        }
    }

    public static void showStats(String[][] seating) {

        int rows = seating.length-1;
        int rowLength = seating[0].length-1;

        int soldTickets = 0;
        double percentage;
        int currentIncome = 0;
        int totalIncome = 0;

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= rowLength; j++) {
                if (seating[i][j].equals("B")) {
                    soldTickets += 1;
                    if (rows * rowLength <= 60 || i <= rows / 2) {
                        currentIncome += 10;
                    } else {
                        currentIncome += 8;
                    }

                }
            }
        }

        double sold = soldTickets;
        double total = rows * rowLength;
        percentage = ((sold * 100 / total * 100)) / 100;

        if (rows * rowLength <= 60) {
            totalIncome = 10 * rows * rowLength;
        } else {
            int frontIncome = (rows / 2) * 10 * rowLength;
            int backIncome = (rows - (rows / 2)) * 8 * rowLength;
            totalIncome = frontIncome + backIncome;

        }

        System.out.println("Number of purchased tickets: " + soldTickets);
        System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);

    }


    public static String[][] checkTicket(String[][] seating) {

        int rows = seating.length -1;
        int rowLength = seating[0].length -1;


        while (true) {
            System.out.println("Enter a row number:");
            int rowNumber = scanner.nextInt();

            System.out.println("Enter a seat number in that row:");
            int seatNumber = scanner.nextInt();

            System.out.println("Seatnumber entered: " + seatNumber);

            if (rowNumber >= 1 && rowNumber <= rows && seatNumber >= 1 && seatNumber <= rowLength) {
                if (seating[rowNumber][seatNumber].equals("B")) {
                    System.out.println("That ticket has already been purchased!");
                } else {
                    seating = bookTicket(seating, rowNumber, seatNumber);
                    break;
                }
            } else {
                System.out.println("Wrong input!");
            }
        }
        return seating;
    }

    public static String[][] bookTicket(String[][] seating, int rowNumber, int seatNumber) {

        int rows = seating.length -1;
        int rowLength = seating[0].length -1;

        System.out.println("Seatnumber rec: " + seatNumber);

        seating[rowNumber][seatNumber] = "B";

        int ticketPrice = 0;

        if (rows * rowLength <= 60 || rowNumber <= rows / 2) {
            ticketPrice = 10;
        } else {
            ticketPrice = 8;
        }

        System.out.println("Ticket price: $" + ticketPrice);

        System.out.println();

        return seating;
    }
    public static void showSeats(String[][] seating) {

        int rows = seating.length;
        int cols = seating[0].length;

        System.out.print("\n");
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i < cols; i++) {
            System.out.print(" " + i);
        }
        System.out.print("\n");

        for (int j = 1; j < rows; j ++) {
            System.out.print(j);
            for (int i = 1; i < cols; i++) {
                System.out.print(" " + seating[j][i]);
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}