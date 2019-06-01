import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

public class Event{
    private String myEventName;
    private String myHostName;
    private String myCohostName;
    private LocalDate myStartDate;
    private LocalDate myEndDate;
    private String myDescription;
    private List<String> myRequiredPlayers;
    private int myAdditionalSlots;
    private String myTimeAvailible;
    private List<String> myNotes;

    public Event(){
        Scanner scanner = new Scanner(System.in);
        myEventName = makeString(scanner, "What is the name of your event: ");
        myHostName = makeString(scanner, "Who is hosting the event: ");
        myCohostName = makeString(scanner, "Who is cohosting the event: ");
        myDescription =  makeString(scanner, "Describe your event: ");
        myNotes = makeNotes(scanner);
        myTimeAvailible = makeString(scanner, "When will you be availible to run the event: ");
        myStartDate = makeDate(scanner, "When is the event starting: ");
        myEndDate = makeDate(scanner, "When is the event ending: ");
        myRequiredPlayers = makeRequiredPlayers(scanner);
        System.out.print("How many additional slots do you have: ");
        myAdditionalSlots = scanner.nextInt();
        System.out.println();
        scanner.close();
    }

    public String makeString(Scanner scanner, String reason){
        System.out.print(reason);
        String result = scanner.nextLine();
        if(result.length() < 1){
            throw new IllegalArgumentException();
        }
        System.out.println();
        return result;
    }

    public LocalDate makeDate(Scanner scanner, String reason){
        LocalDate date = LocalDate.of(2019, 1, 1);
        System.out.println(reason);
        System.out.print("Enter the year: ");
        int year = scanner.nextInt();
        System.out.print("Enter the month: ");
        int month = scanner.nextInt();
        System.out.print("Enter the day: ");
        int day = scanner.nextInt();
        try{
            date = LocalDate.of(year, month, day);
        } catch(DateTimeException e){
            e.printStackTrace();
            System.exit(1);
        }
        
        System.out.println();
        return date;
    }

    public String validString(Scanner scanner){
        boolean flag = false;
        String result = scanner.nextLine();
        while(flag == false){
            if(result.length() == 0){
                System.out.println("Entry not valid.  Try again.");
                result = scanner.nextLine();
            } else {
                flag = true;
            }
        }
        return result;
    }

    public List<String> makeRequiredPlayers(Scanner scanner){
        List<String> players = new ArrayList<String>();
        System.out.println("How many required players do you have in this event: ");
        int length = scanner.nextInt() + 1;
        if(length < 1){
            throw new IllegalArgumentException();
        }
        System.out.println("List all required players below: ");
        for(int i = 0; i < length; ++i){
            String playerName = "";
            playerName = scanner.nextLine();
            players.add(playerName);
            System.out.println();
        }
        System.out.println();
        return players;
    }

    public List<String> makeNotes(Scanner scanner){
        boolean flag = true;
        List<String> notes = new ArrayList<String>();
        System.out.println("Enter a note or hit return to move on.");
        while(flag){
            String note = scanner.nextLine();
            if(note.length() == 0){
                flag = false;
            } else {
                notes.add(note);
                System.out.println("Enter your next note or enter nothing to move on.");
            }
        }
        return notes;
    }
    
    public String toString(){
        String result = "";
        result += "**Event Name:** " + myEventName + "\n";
        result += "**Host Name:** " + myHostName + "\n";
        result += "**Co-Host Name:** " + myCohostName + "\n";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d LLLL yyyy");
        result += "**Start Date:** " + myStartDate.format(formatter) + "\n";
        result += "**End Date:** " + myEndDate.format(formatter) + "\n";
        result += "**Description:** " + "\n" + myDescription + "\n";
        result += "**Required Peeps:** \n";
        for(int i = 1; i < myRequiredPlayers.size(); i++){
            result += "- " + myRequiredPlayers.get(i) + "\n";
        }
        result += "**Additional Slots:** " + myAdditionalSlots + "\n";
        result += "**Times Availible:** " + myTimeAvailible + "\n";
        result += "**Notes:** \n";
        for(int i = 0; i < myNotes.size(); i++){
            result += "- " + myNotes.get(i) + "\n";
        }
        return result;
    }
}