//Brian McMonigle
//CS 145
//Lab: Card Game



import java.util.Scanner;


public class DeckOfCards {
   //Need to be able to make a deck and shuffle the cards
   public static void main(String[] args) {
      System.out.println("ID001");
      String[] deck = makeDeck();
      String [] sDeck = shuffleDeck(deck);

      Scanner scan = new Scanner(System.in);

      // make the game loop
      loop: while(true){

         //Need to pose the question of what game do you want to play (choices are 1 or 2)
         System.out.println("\nWhat game do you want to do?");
         int choice = 0;
         try{
            String str = scan.next();
            choice = Integer.parseInt(str);
         }catch(NumberFormatException e){
            //This allows you to choose between two different styles of this game
            //Choice 1 gives you a single game against the computer
            //Choice 2 gives you a set of 100 games against a computer and keeps 
            //track of how many each computer won
            System.out.println("Only Integers(1/2) allowed.");
            continue;
         }

         switch (choice) {
            case 1:
               int humanCardValue, computerCardValue;
               while(true){
                  try{
                     //we need to choose a card out of the deck to be our value agianst the computer
                     System.out.println("Enter a card location from 0 to 51:");
                     String card = scan.next();
                     humanCardValue = Integer.parseInt(card);
                  }catch(NumberFormatException e){
                     //Only integers will be allowed, otherwise the game will not work
                     System.out.println("Only Integers allowed.");
                     continue;
                  }
                  //any cards outside of the range from 0-51 will show as "Invalid  Input"
                  if(humanCardValue >= 0 && humanCardValue <= 51){
                     break;
                  }else{
                     System.out.println("Invalid Input.");
                  }
               }
               //After each has chosen a card it will tell us the value and determine who won.
               //The one with the higher number on their card wins
               //this deck is shuffled so each numbered card is random in the deck
               computerCardValue = (int)(Math.random()*52);
               System.out.println("Computer chose: "+computerCardValue);

               //show what card you had vs what the computer had
               System.out.println("You had a "+sDeck[humanCardValue]);
               System.out.println("Computer has a "+sDeck[computerCardValue]);

               //tells who had the higher card or if it was a tie between the computers
               if(declareWinner(sDeck[humanCardValue], sDeck[computerCardValue]) == 1){
                  System.out.println("You have a high card.");
               }else if(declareWinner(sDeck[humanCardValue], sDeck[computerCardValue]) == -1){
                  System.out.println("Computer had a higher card.");
               }else{
                  System.out.println("It's a tie");
               }

               break;
            case 2:

               int c1_score=0, c2_score=0;

               for(int i=0; i<100; i++){
                  int c1 = (int)(Math.random()*52);
                  int c2 = (int)(Math.random()*52);
                  //This will tell us the we either had a higher or lower card then the 
                  //computer or if its a tie
                  if(declareWinner(sDeck[c1], sDeck[c2]) == 1){
                     System.out.println("Computer 1 had a higher card: "+sDeck[c1]+sDeck[c2]);
                     c1_score++;
                  }else if(declareWinner(sDeck[c1], sDeck[c2]) == -1){
                     System.out.println("Computer 2 had a higher card: "+sDeck[c2]+sDeck[c1]);
                     c2_score++;
                  }else{
                     System.out.println("Tie");
                  }
                  sDeck = shuffleDeck(sDeck);
               }

                  System.out.println("Computer 1 had "+c1_score+" wins.");
                  System.out.println("Computer 2 had "+c2_score+" wins.");
                  //if computer 1 has the higher number, they win
                  //if computer 2 has the higher number, they win
                  //if its the same its a tie
                  if(c1_score > c2_score){
                     System.out.println("Computer 1 won the game");
                  }else if(c1_score < c2_score){
                     System.out.println("Computer 2 won the game");
                  }else{
                     System.out.println("Its' a tie");
                  }

                  break;
               //after the game it will say goodbye
               case -1:
                  System.out.println("GoodBye");
                  break loop;
               //if you choose to, you can play again it will ask you to input your choice again
               default:
                  System.out.println("Invalid Input, only (1/2).");


            }
         }
            
      }

      public static String[] makeDeck(){
         String[] suits={"hearts","spades","clubs","diamonds"};
         String[] ranks={"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
         // I have now included the ranking of the suits and the numbers
         String[] sortedDeck = new String[52];
         int i=0;

         for(String suit: suits){
            for(String rank: ranks){
               sortedDeck[i++] = rank + " of " + suit;
            }
         }

         
         return sortedDeck;

      }

      public static String[] shuffleDeck(String [] deck){
         String[] shuffled = deck;
         // This deck is shuffled after each game

        
         for(int i=0; i<500; i++){
            int card1 = (int)(Math.random()*52);
            int card2 = (int)(Math.random()*52);

            String temp = shuffled[card1];
            shuffled[card1] = shuffled[card2];
            shuffled[card2] = temp;
         }

         return shuffled;
      }

      public static int declareWinner(String c1, String c2){

         if(cardValue(c1) > cardValue(c2)){
            return 1;
         }else if(cardValue(c1) < cardValue(c2)){
            return -1;
         }else{
            return 0;
         }
      }

      public static int cardValue(String card){

         String[] arr = card.split(" ");
         int score = 0;
         //This will show you the values of each number
         switch(arr[0]){
            case "Ace":
               score += 1;
               break;
            case "2":
               score += 2;
               break;
            case "3":
               score += 3;
               break;
            case "4":
               score += 4;
               break;
            case "5":
               score += 5;
               break;
            case "6":
               score += 6;
               break;
            case "7":
               score += 7;
               break;
            case "8":
               score += 8;
               break;
            case "9":
               score += 9;
               break;
            case "10":
               score += 10;
               break;
            case "Jack":
               score += 11;
               break;
            case "Queen":
               score += 12;
               break;
            case "King":
               score += 13;
         }
         //this shows you the rank of the suits in 
         //the deck if the number ends in a tie
         switch(arr[2]){
            case "hearts":
               score += 0;
               break;
            case "spades":
               score +=13;
               break;
            case "clubs":
               score +=26;
               break;
            case "diamonds":
               score +=39;
         }

         return score;
      }



}