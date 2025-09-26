/**
 * @author: Brandon Baird
 * Date: 08/24/2024
 * Purpose: To traverse a list of Player objects to find the 
 * tallest player whose age is less than or equal to the 
 * average age of all players
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Project1 {
	public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
		ArrayList<Player> playersList = new ArrayList<>();

        while (true) {
            System.out.print("Enter player's name, age, and height in feet and inches (or press Enter to finish): ");
            String name = scan.nextLine();

            if (name.isEmpty()) {
                break;
            }
            
            //initializes the instance variables for each object
            int age = scan.nextInt();
            int feet = scan.nextInt();
            int inches = scan.nextInt();
            scan.nextLine();
            
            Height height = new Height(feet, inches);
            Player player = new Player(name, height, age);
            
            //add the object to the list
            playersList.add(player);
        }
        scan.close();

        // If no players are in the list just return.
        if (playersList.isEmpty()) {
            System.out.println("No players were added.");
            return;
        }

        //Traverse list to add the total age then calculate average.
        int totalAge = 0;
        for(Player p : playersList) {
        	totalAge += p.getAge();
        }
        double averageAge = totalAge / playersList.size();
        
        System.out.println("The average age of all players is " + averageAge);
        
        //Traverse list again to try and find the tallest & player who is younger than the average age.
        Player tallestPlayer = null;
        for(Player p : playersList) {
        	if(p.getAge() <= averageAge) {
        		if(tallestPlayer == null || p.getHeight().toInches() > tallestPlayer.getHeight().toInches()) {
        			tallestPlayer = p;
        		}
        	}
        }
        if(tallestPlayer.getAge() == averageAge) {
        	System.out.println("The tallest player whose age is equal to than the average is: " + tallestPlayer.toString());
        } else {
        	System.out.println("The tallest player whose age is less than the average is: " + tallestPlayer.toString());
        }
	}
}
