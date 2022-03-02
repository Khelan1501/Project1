/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package uno;

/**
 *
 * @author khela
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Uno
{
    public static void main(String[] args)
    {
        ArrayList<Unocard> pdeck = new ArrayList<Unocard>();
        ArrayList<Unocard> compdeck = new ArrayList<Unocard>();
        int win;  
        Scanner input;
        Unocard CardOnTop; 
        int choice; 
        String currentColor; 

        gameLoop:
        while (true)
        {
            pdeck.clear();
            compdeck.clear();
            win = 0;
            CardOnTop = new Unocard();
            currentColor = CardOnTop.color;

            System.out.println("\nWelcome to Uno! Initialising decks...");
            draw(7, pdeck);
            draw(7, compdeck);

            
            for (boolean playersTurn = true; win == 0; playersTurn ^= true)
            {
                choice = 0;
                System.out.println("\nThe top card is: " + CardOnTop.getFace());

                if (playersTurn) 
                {
                    
                    System.out.println("Your turn! Your choices:");
                    for (int i = 0; i < pdeck.size(); i++)
                    {
                        System.out.print(String.valueOf(i + 1) + ". " + 
                        ((Unocard) pdeck.get(i) ).getFace() + "\n");
                    }
                    System.out.println(String.valueOf(pdeck.size() + 1 ) + ". " + "Draw card" + "\n" + 
                                       String.valueOf(pdeck.size() + 2) + ". " + "Quit");
                    
                    do
                    {
                        System.out.print("\nPlaease input the number of your choice: ");
                        input = new Scanner(System.in);
                    } while (!input.hasNextInt() );
                    
                    choice = input.nextInt() - 1;

                    
                    if (choice == pdeck.size() )
                        draw(1, pdeck);
                    else if (choice == pdeck.size() + 1)
                        break gameLoop;
                    else if ( ((Unocard) pdeck.get(choice)).canPlace(CardOnTop, currentColor) )
                    {
                        CardOnTop = (Unocard) pdeck.get(choice);
                        pdeck.remove(choice);
                        currentColor = CardOnTop.color;
                                               
                        if (CardOnTop.value >= 10)
                        {
                            playersTurn = false; 

                            switch (CardOnTop.value)
                            {
                                case 12: 
                                System.out.println("Drawing 2 cards...");
                                draw(2,compdeck);
                                break;

                                case 13: case 14:                         
                                do 
                                {
                                    System.out.print("\nEnter the color you want: ");
                                    input = new Scanner(System.in);
                                } while (!input.hasNext("R..|r..|G....|g....|B...|b...|Y.....|y.....") ); //Something I learned recently
                                if (input.hasNext("R..|r..") )
                                    currentColor = "Red";
                                else if (input.hasNext("G....|g....") )
                                    currentColor = "Green";
                                else if (input.hasNext("B...|b...") )
                                    currentColor = "Blue";
                                else if (input.hasNext("Y.....|y.....") )
                                    currentColor = "Yellow";

                                System.out.println("You chose " + currentColor);
                                if (CardOnTop.value == 14) 
                                {
                                    System.out.println("Drawing 4 cards...");
                                    draw(4,compdeck);
                                }
                                break;
                            }
                        }
                    } else System.out.println("Invalid choice. Turn skipped.");


                } else 
                {
                    System.out.println("My turn! I have " + String.valueOf(compdeck.size() ) 
                                        + " cards left!" + ((compdeck.size() == 1) ? "...Uno!":"") );
                    
                    for (choice = 0; choice < compdeck.size(); choice++)
                    {
                        if ( ((Unocard) compdeck.get(choice)).canPlace(CardOnTop, currentColor) ) // Searching for playable cards
                            break; 
                    }

                    if (choice == compdeck.size() )
                    {
                         System.out.println("I've got nothing! Drawing cards...");
                         draw(1,compdeck);
                    } else 
                    {
                         CardOnTop = (Unocard) compdeck.get(choice);
                         compdeck.remove(choice);
                         currentColor = CardOnTop.color;
                         System.out.println("I choose " + CardOnTop.getFace() + " !");

                         
                         if (CardOnTop.value >= 10)
                         {
                             playersTurn = true; 

                             switch (CardOnTop.value)
                             {
                                 case 12 -> {
                                    
                                     System.out.println("Drawing 2 cards for you...");
                                     draw(2,pdeck);
                                 }

                                 case 13, 14 -> {
                                     
                                     do 
                                     {
                                         currentColor = new Unocard().color;
                                     } while (currentColor == "none");
                                     
                                     System.out.println("New color is " + currentColor);
                                     if (CardOnTop.value == 14) 
                                     {
                                         System.out.println("Drawing 4 cards for you...");
                                         draw(4,pdeck);
                                     }
                                 }


                             }
                         }
                    }

                    // If decks are empty
                    if (pdeck.size() == 0)
                        win = 1;
                    else if (compdeck.size() == 0)
                        win = -1;
                }

            } // turns loop end

            /*************Results**************/
            if (win == 1)
                System.out.println("You win :)");
            else 
                System.out.println("You lose :(");

            System.out.print("\nPlay again? ");
            input = new Scanner(System.in);

            if (input.next().toLowerCase().contains("n") )
                break;
        } // game loop end // game loop end // game loop end // game loop end // game loop end // game loop end // game loop end // game loop end

        System.out.println("Bye bye");
    }
    // For drawing cards
    public static void draw(int cards, ArrayList<Unocard> deck)
    {
        for (int i = 0; i < cards; i++)
            deck.add(new Unocard() );
    }
}
