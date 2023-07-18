package monash.Airline;

import monash.Airline.server.ChooseTicket;

import java.util.Scanner;

public class TicketSystem {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        ChooseTicket chooseTicket = new ChooseTicket();
        System.out.println("Welcome to the Ticket System!");
        System.out.println("Please enter your departure city:");
        String departureCity = scanner.nextLine();

        System.out.println("Please enter your destination city:");
        String destinationCity = scanner.nextLine();

        chooseTicket.chooseTicket(departureCity, destinationCity);

        scanner.close();
    }
}
