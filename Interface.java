//imports for some of the external methods used.
import java.io.*;
import javax.swing.*;
/**
 * The interface class is a class that has been designed to make communication between the Computer and the user easier and more pleasing.
 * It also has methods to print the rules, etc. This class is where the game starts.
 * 
 * @author Nived.Kodavali 
 * @version 1.0(Basic)
 */

public class Interface{

    /**
     * This is one of the first methods to be invoked in a game and is invoked only in the beginning.
     * It is an introductory method as the name suggests and it asks the player whether he/she wants to play etc.
     */
    public static void intro()throws Exception{
        slowPrint("Welcome to Rummy in R.V");
        Thread.sleep(1000);
        slowPrint("Would you mind disclosing yor name?(name please!)");
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);
        String name = br.readLine();
        System.out.println("Are you really the great "+name+"?");
        System.out.println(" God bless me! "+name+" the greatest Rummy player who defeated the 'brainiest' computers!");
        System.out.println("It is good fortune that you have come to play against this humble computer.");
        System.out.println(" Of course, it goes without saying that you are ");
        System.out.println("expected to play an easy game!");
        Thread.sleep(6000);
        int play = JOptionPane.showConfirmDialog(null,"I assume you would love to proceed.");
        //JOptionPane.showConfirmDialog has three buttons, yes; no; and cancel.
        //yes returns 0; no returns 1; cancel returns 2;
        //This block is to decide whether the plater wants to play.
        if(play == 1 ){
            JOptionPane.showMessageDialog(null,"you have missed out on amazing fun!!");
            System.exit(0);
        }
        else if(play==2)   intro();
        else if(play==0){
            int rules = JOptionPane.showConfirmDialog(null,"Do you know the rules?");
            if(rules == 1)   rules();
            //invokes the begin method in the Game class which is the central method.
            Game.begin();
        }
        
    }
    
    /**
     * This is a method that is used to make the output look more pleasing and is invoked frequently in the game.
     * It employs the Thread.sleep(int) method to print the output slowly where each character is printed after a gap of a few milliseconds.
     * @param s The string that has to be printed slowly.
     */
    public static void slowPrint(String s) throws Exception{
        for (int i = 0;i<s.length();i++){
            Thread.sleep(20);
            System.out.print(s.charAt(i));
        }
        System.out.println();
    }
    
    /**
     * This is a method that is used to make the output look more pleasing and is invoked frequently in the game.
     * It employs the Thread.sleep(int) method to print the output slowly where each character is printed after a gap of a few milliseconds.
     * @param s The string that has to be printed slowly.
     */
    public static void fastPrint(String s) throws Exception{
        for (int i = 0;i<s.length();i++){
            Thread.sleep(2);
            System.out.print(s.charAt(i));
        }
        System.out.println();
    }
    
    /**
     * This is a method that is used to make the output look more pleasing and is invoked frequently in the game.
     * It employs the Thread.sleep(int) method to print the output slowly where each character is printed after a gap of a few milliseconds.
     * @param s The string that has to be printed slowly.
     */
    public static void veryFastPrint(String s) throws Exception{
        for (int i = 0;i<s.length();i++){
            Thread.sleep(1);
            System.out.print(s.charAt(i));
        }
        System.out.println();
    }
    
    /**
     * This is a method which is invoked when a line needs to printed. It has a simple for loop that is employed to print a line.
     */
    public static void printLine(){
        for(int n = 0;n<143;n++)   System.out.print("_");
        System.out.println("_");
    }
    
    /**
     * This method prints the rules of Rummy in a user friendly format.
     */
    public static void rules()throws Exception{
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);
        slowPrint("The rules of Rummy are as follows(Press enter for the next rule).");
        String s = br.readLine();
        slowPrint("(1)Rummy is a 2 player card game where each player gets 10 cards each.");
        s = br.readLine();
        slowPrint("(2)The aim of the game is to be the first person to make: ");
        slowPrint("   One set of 4 cards and two sets of 3 cards");
        slowPrint("   OR");
        slowPrint("   two sets of 5 cards");
        s = br.readLine();
        slowPrint("(3)A set can either be:");
        s = br.readLine();
        slowPrint("   (i) a sequence of 3,4 or 5 cards of a suit. For example:");
        slowPrint("      6 of ♠, 7 of ♠, 8 of ♠ is a sequence.");
        slowPrint("      OR");
        s = br.readLine();
        slowPrint("   (ii) a Three of a Kind or a four of a kind. For example:");
        slowPrint("       8 of ♥, 8 of ♦, 8 of ♣ is a three of a kind. Similarly, when there are four, it is a four of a kind.");
        s = br.readLine();
        slowPrint("   (iii)an Ace can either be used as a card higher that a king or a card lower that a two in a set. However, it can't be used as both");
        slowPrint("          However, in the present version of the game, an Ace can be used only in a sequence with two or as part of a triplet etc.");
        s = br.readLine();
        slowPrint("(4)The game starts with a player (YOU!!) picking a card from the discard pile or the pack of Cards.");
        slowPrint("   The discard pile consists of the cards that are thrown at the end of turns.");
        slowPrint("       In the beginning, a random card is taken as the discard pile.");
        slowPrint("   The pack of Cards is the unused pile.");
        s = br.readLine();
        slowPrint("(5)At a point, a player can have only 10 cards. So when a Card is picked, a Card has to be thrown.");
        slowPrint("   The Card that has been thrown, joins the discard pile.");
        s = br.readLine();
        slowPrint("(6)By repeatedly picking and throwing Cards, the player has to make sets.");
        slowPrint("   Sets are made by picking up Cards that may make a set and throwing Cards that are not part of prospective set.");
        s = br.readLine();
        slowPrint("*====================*** DO YOU WISH TO PROCEED? ***====================*");
        slowPrint("(yes/no)");
        s = br.readLine();
        if (s.compareToIgnoreCase("yes") == 0) return;
        else{
            JOptionPane.showMessageDialog(null,"you have missed out on amazing fun!!");
            System.exit(0);
        }
        
        
    }
    
    public static void main(String[]args)throws Exception{
        veryFastPrint("♠♠♠♠♠♠♠♠♠♠♠     ♠♠♠           ♠♠♠   ♠♠♠               ♠♠♠   ♠♠♠               ♠♠♠   ♠♠♠            ♠♠♠");
        veryFastPrint("♠♠♠♠♠♠♠♠♠♠♠♠    ♠♠♠           ♠♠♠   ♠♠♠♠             ♠♠♠♠   ♠♠♠♠             ♠♠♠♠    ♠♠♠          ♠♠♠");
        veryFastPrint("♠♠♠      ♠♠♠♠   ♠♠♠           ♠♠♠   ♠♠♠♠♠           ♠♠♠♠♠   ♠♠♠♠♠           ♠♠♠♠♠     ♠♠♠        ♠♠♠");
        veryFastPrint("♠♠♠       ♠♠♠   ♠♠♠           ♠♠♠   ♠♠♠♠♠♠         ♠♠♠♠♠♠   ♠♠♠♠♠♠         ♠♠♠♠♠♠      ♠♠♠      ♠♠♠");
        veryFastPrint("♥♥♥       ♥♥♥   ♥♥♥           ♥♥♥   ♥♥♥ ♥♥♥       ♥♥♥ ♥♥♥   ♥♥♥ ♥♥♥       ♥♥♥ ♥♥♥       ♥♥♥    ♥♥♥");
        veryFastPrint("♥♥♥      ♥♥♥    ♥♥♥           ♥♥♥   ♥♥♥  ♥♥♥     ♥♥♥  ♥♥♥   ♥♥♥  ♥♥♥     ♥♥♥  ♥♥♥        ♥♥♥  ♥♥♥");
        veryFastPrint("♥♥♥     ♥♥♥     ♥♥♥           ♥♥♥   ♥♥♥   ♥♥♥   ♥♥♥   ♥♥♥   ♥♥♥   ♥♥♥   ♥♥♥   ♥♥♥         ♥♥♥♥♥♥");
        veryFastPrint("♥♥♥♥♥♥♥♥♥       ♥♥♥           ♥♥♥   ♥♥♥    ♥♥♥ ♥♥♥    ♥♥♥   ♥♥♥    ♥♥♥ ♥♥♥    ♥♥♥          ♥♥♥♥");
        veryFastPrint("♣♣♣♣♣♣          ♣♣♣           ♣♣♣   ♣♣♣     ♣♣♣♣♣     ♣♣♣   ♣♣♣     ♣♣♣♣♣     ♣♣♣          ♣♣♣♣");
        veryFastPrint("♣♣♣ ♣♣♣         ♣♣♣           ♣♣♣   ♣♣♣               ♣♣♣   ♣♣♣               ♣♣♣          ♣♣♣♣");
        veryFastPrint("♣♣♣  ♣♣♣        ♣♣♣           ♣♣♣   ♣♣♣               ♣♣♣   ♣♣♣               ♣♣♣          ♣♣♣♣");
        veryFastPrint("♣♣♣   ♣♣♣        ♣♣♣         ♣♣♣    ♣♣♣               ♣♣♣   ♣♣♣               ♣♣♣          ♣♣♣♣");
        veryFastPrint("♦♦♦    ♦♦♦        ♦♦♦       ♦♦♦     ♦♦♦               ♦♦♦   ♦♦♦               ♦♦♦          ♦♦♦♦");
        veryFastPrint("♦♦♦     ♦♦♦        ♦♦♦♦♦♦♦♦♦♦♦      ♦♦♦               ♦♦♦   ♦♦♦               ♦♦♦          ♦♦♦♦");
        veryFastPrint("♦♦♦      ♦♦♦        ♦♦♦♦♦♦♦♦♦       ♦♦♦               ♦♦♦   ♦♦♦               ♦♦♦          ♦♦♦♦\n\n\n\n");
        intro();
        
    }
    
    /*public static void PrintCard(){
        System.out.println("9830          "+(char)9830);
        System.out.println("9829          "+(char)9829);
        System.out.println("9824          "+(char)9824);    
        System.out.println("9827          "+(char)9827);  
    }
    
    //ASCII values.
    //Found out by copy-pasting the symbols srom the Cards page on Wikipedia and printing the int counterparts.
    9830          ♦
    9829          ♥
    9824          ♠
    9827          ♣
    
    */
        
}
