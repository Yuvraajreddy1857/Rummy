/**
 * This is the Player class whick has some of the essencial attributes of a Player such as his hand, the player's prediction of the oppponent's hand, memory of the discard pile, 
 * the different probabilities he/she might arrange them into, etc.
 * It is mainly used by the computer and also consists of the crude 'artificial intelligence' where the computer decides whaat to do based on certain parameters.
 * It has methods which enable it to pick a card, sort out its cards for a win, etc 'wisely'.
 * The Player object represents a real life Rummy Player.
 * It has the neccesary attributer of a real Player.
 * @author Nived.Kodavali 
 * @version 1.0(Basic)
 * 
 */
public class Player{
    
   /**the human Player's hand**/
   Deck hand;
   /**the Computer's hand**/
   Deck compHand;
   /**the computer's prediction of the opponents card(it is barely used right now bu it shall be used in later versions).**/
   Deck oppCard;
   /**the discard pile**/
   Deck discard;
   /**this is an array of 52 integers where based on the priority and the value of a Card(depending on how many places it is needed), the integer containing each value is given 
    a number. This helps the computer in deciding what card to pick etc. In later versions, it will **/
   int[] cardsNeeded;
   /**the probs are the different possibilities the computer may form towarsd making sets. The minimum length of a probability is 2, hence only 5 probabilities are probable**/
   /**It will contain the first probability (close to a set as the computer arranges it**/
   Deck prob1;
   /**It will contain the second probability (close to a set as the computer arranges it**/
   Deck prob2;
   /**It will contain the third probability (close to a set as the computer arranges it**/
   Deck prob3;
   /**It will contain the fourth probability (close to a set as the computer arranges it**/
   Deck prob4;
   /**It will contain the fifth probability (close to a set as the computer arranges it**/
   Deck prob5;
   // boolean[] isProbSequence;/**This is an array where each element represents 1 of the probs. It is used **/
   
   
   /**
    * This is a non parameterised constructor which returns a fully initialised Player object.
    */
   public Player(){
       this.hand = new Deck(10);
       this.compHand = new Deck (10);
       this.oppCard = new Deck(0);
       this.prob1 = new Deck(1); 
       this.discard = new Deck(1);
       this.prob2 = new Deck(1);
       this.prob3 = new Deck(1);
       this.prob4 = new Deck(1);
       this.prob5 = new Deck(1);
       this.cardsNeeded = new int[52];
       
   }
   
   /**
    * This method is invoked on this Player.
    * It modifies the int cardsNeeded[] array in this.
    * One element in cardsNeeded represents a Card in a full Deck.
    * It gives the integers in cardsNeeded appropriate values based on how important the Card it represents is.
    * It will have more priority if it a part of a sequence and a triple, or two sequences and so on.
    * It is a very powerful tool. Though it does not have too many important uses in this version, in later versions, based on the array generated by this method, the computer 
    * will decide how to organise its sets in order to win, etc. 
    */
   public void refreshCardsNeeded()throws Exception{
       Deck deck = new Deck();
       for(int i = 0; i<52;i++){
           cardsNeeded[i] = 0;
       }
       
       //this code along with some other code (now deleted) was to ensure that the computer does not pick a Card whose 'set companions' are in the discard pile etc.
       /*for(int w = 0 ; w < discard.cards.length ; w++){
           int index = Deck.findIndex(discard.cards[w]);
           deck.cards[index] = null;
       }*/
       
       //to copy all the probabilities etc into one Deck.
       Deck hand1= new Deck(compHand.cards.length+prob1.cards.length+prob2.cards.length+prob3.cards.length+prob4.cards.length+prob5.cards.length);
       compHand = Deck.makeSmaller(compHand);
       prob1 = Deck.makeSmaller(prob1);
       prob2 = Deck.makeSmaller(prob2);
       prob3 = Deck.makeSmaller(prob3);
       prob4 = Deck.makeSmaller(prob4);
       prob5 = Deck.makeSmaller(prob5);
       int w = 0;
       for(int n = 0;n<compHand.cards.length;n++){
           hand1.cards[w] = compHand.cards[n];
           w++;
       }
        
       for(int n = 0; n<prob1.cards.length; n++){
           hand1.cards[w] = prob1.cards[n];
           w++;
       }
        
       for(int n = 0; n<prob2.cards.length; n++){
           hand1.cards[w] = prob2.cards[n];
           w++;
       }
       
       for(int n = 0; n<prob3.cards.length; n++){
           hand1.cards[w] = prob3.cards[n];
           w++;
       }
       
       for(int n = 0; n<prob4.cards.length; n++){
           hand1.cards[w] = prob4.cards[n];
           w++;
       }
       
       for(int n = 0; n<prob5.cards.length; n++){
           hand1.cards[w] = prob5.cards[n];
           w++;
       }
       hand1 = Deck.insertionSort(hand1);
       hand1 = Deck.makeSmaller(hand1);
        
       int[] hist1 = Deck.deckHist(deck);
       int[] hist2 = Deck.deckHist(compHand);
       for(int i = 0; i<52; i++){
           Card card1 = deck.cards[i];
           for(int n = 0; n<compHand.cards.length; n++){
               Card card2 = compHand.cards[n];
               int difference = Math.abs(((card1.suit*13)+card1.rank)-((card2.suit*13)+card2.rank));
               if(difference>0&&difference<=2){
                   cardsNeeded[i]++;
               }
               
               //The nested if block is to ensure that cardsNeeded does not get incremented when a king or a queen of a lower suit are being compared with an ace or a two of the
               //next suit 
               //it has been commented out for now due to some minor bugs. It shall be modified in later versions.
               /*if(card1.rank <= 2){   
                   if(((card1.suit*13)+card1.rank>((card2.suit*13)+card2.rank))){
                       if((Math.abs(((card1.suit*13)+card1.rank)-((card2.suit*13)+card2.rank)))<=2)   cardsNeeded[i]--;
                   }
               }*/
               //if(card1.rank == 13 && card2.rank == 2 && card1.suit == card2.suit)cardsNeeded[i]--;
               //if(card1.rank == 2 && card2.rank == 13 && card1.suit == card2.suit)cardsNeeded[i]--;
                   
           
               if(prob1.cards.length<=4 && prob2.cards.length<=4 && prob3.cards.length<=4 && prob4.cards.length<=4 && prob5.cards.length<=4){ 
                  if(card1.rank == card2.rank && card1.suit != card2.suit)   cardsNeeded[i]++;
               }
           }
       }    
   }
   
   //The older version which has been ommited out for the time being.
   /*public void refreshProbabilities()throws Exception{
       
       int seqIndex = 0;
       int length = 0;
       if(compHand.cards.length>=2){
           seqIndex =  Deck.checkSequence(compHand);//The integer is the place where the sequence starts.
           if(seqIndex>=0){
               length = Deck.seqLength(compHand,seqIndex);
           }   
       }
       //for(int i = 0; i < prob1.cards.length; i++){
           
       /*for(int n = 0; n < compHand.cards.length;n++){//compHand = 11; n = 1
           
           Card card = compHand.cards[n];//card = 4 of ♣
           prob1 = Deck.makeSmaller(prob1);  
           //if(prob1.cards.length<=1)   break;
           prob1 = Deck.makeBigger(prob1);
           Deck deck = new Deck (prob1.cards.length);//length = 1
           deck.copyDeck(prob1);
           deck.cards[deck.cards.length-1] = card;//0th card is 4 of ♣
           int without = Deck.isPartOfSequence(prob1);//without = 0
           int with = Deck.isPartOfSequence(deck);//0
           int r = without-with;
           System.out.println("prob1   " + r);  
           if(with <= without && with != -1){
               if(Deck.findRangeLength(prob1)<=4){
                   prob1 = Deck.makeBigger(prob1);
                   prob1.cards[prob1.cards.length-1] = card;
                   compHand.cards[n] = null;
                   //compHand = Deck.makeSmaller(compHand);
               }
               //else   break; 
           }              
       }
       
       /*compHand = Deck.makeSmaller(compHand);
       prob1 = Deck.makeSmaller(prob1);
       if(prob1.cards.length<2 && ){
           compHand = Deck.makeBigger(compHand);
           compHand.cards[compHand.cards.length-1] = prob1.cards[0];
           
       
       for(int n = 0; n < compHand.cards.length; n++){
           Card card = compHand.cards[n];
           prob2 = Deck.makeSmaller(prob2); 
           //if(prob2.cards.length<=1)   break;
           prob2 = Deck.makeBigger(prob2);
           Deck deck = new Deck (prob2.cards.length);
           deck.copyDeck(prob2);
           deck.cards[deck.cards.length-1] = card;
           int without = Deck.isPartOfSequence(prob2);
           int with = Deck.isPartOfSequence(deck);
           int r = without-with;
           System.out.println("prob2   " + r);
           if(with <= without && with != -1){
               if(Deck.findRangeLength(prob2)<=4){
                   prob2 = Deck.makeBigger(prob2);
                   prob2.cards[prob2.cards.length-1] = card;
                   compHand.cards[n] = null;
                   //compHand = Deck.makeSmaller(compHand);
               }
               //else   break; 
           }              
       }
       
       for(int n = 0; n < compHand.cards.length; n++){
           Card card = compHand.cards[n];
           prob3 = Deck.makeSmaller(prob3);
           //if(prob3.cards.length<=1)   break;
           prob3 = Deck.makeBigger(prob3);
           Deck deck = new Deck (prob3.cards.length);
           deck.copyDeck(prob3);
           deck.cards[deck.cards.length-1] = card;
           int without = Deck.isPartOfSequence(prob3);
           int with = Deck.isPartOfSequence(deck);
           int r = without-with;
           System.out.println("prob3   " + r);
           if(with <= without && with != -1){
               if(Deck.findRangeLength(prob3)<=4){
                   prob3 = Deck.makeBigger(prob3);
                   prob3.cards[prob3.cards.length-1] = card;
                   compHand.cards[n] = null;
                   //compHand = Deck.makeSmaller(compHand);
               }
               //else   break; 
           }              
       }
       
       for(int n = 0; n < compHand.cards.length; n++){
           Card card = compHand.cards[n];
           prob4 = Deck.makeSmaller(prob4);   
           //if(prob4.cards.length<=1)   break;
           prob4 = Deck.makeBigger(prob4);
           Deck deck = new Deck (prob4.cards.length);
           deck.copyDeck(prob4);
           deck.cards[deck.cards.length-1] = card;
           int without = Deck.isPartOfSequence(prob4);
           int with = Deck.isPartOfSequence(deck);
           int r = without-with;
           System.out.println("prob4   " + r);
           if(with <= without && with != -1){
               if(Deck.findRangeLength(prob4)<=4){
                   prob4 = Deck.makeBigger(prob4);
                   prob4.cards[prob4.cards.length-1] = card;
                   compHand.cards[n] = null;
                   //compHand = Deck.makeSmaller(compHand);
               }
               //else   break; 
           }              
       }
       
       for(int n = 0; n < compHand.cards.length; n++){
           Card card = compHand.cards[n];
           prob5 = Deck.makeSmaller(prob5); 
           // if(prob5.cards.length<=1)   break;
           prob5 = Deck.makeBigger(prob5);
           Deck deck = new Deck (prob5.cards.length);
           deck.copyDeck(prob5);
           deck.cards[deck.cards.length-1] = card;
           int without = Deck.isPartOfSequence(prob5);
           int with = Deck.isPartOfSequence(deck);
           int r = without-with;
           System.out.println("prob5   " + r);
           if(with <= without && with != -1){
               if(Deck.findRangeLength(prob5)<=4){
                   prob5 = Deck.makeBigger(prob5);
                   prob5.cards[prob5.cards.length-1] = card;
                   compHand.cards[n] = null;
                   //compHand = Deck.makeSmaller(compHand);
               }
               //else   break; 
           }              
       }
       
       prob1 = Deck.makeSmaller(prob1);
       prob2 = Deck.makeSmaller(prob2);
       prob3 = Deck.makeSmaller(prob3);
       prob4 = Deck.makeSmaller(prob4);
       prob5 = Deck.makeSmaller(prob5);
       compHand = Deck.makeSmaller(compHand);
       //}
       
   }*/
   
   /**
    * This is the central method in the Computer's 'brain'.
    * It gets invoked on this Player.
    * With the help of about 20+ helper methods, it sorts out the computer's cards into different 'probabilities'.
    */
   public void refreshProbabilities()throws Exception{
       compHand = Deck.insertionSort(compHand);
       for (int i = 1;i<=5;i++){
           
           
           if(i==2||i==4){
               Deck deck1 = fixFourOfAKind(compHand);
               deck1 = Deck.makeSmaller(deck1);
               
               if(deck1.cards.length>=2){
                   int rank = deck1.cards[0].rank;
                   for( int q = 0;q<compHand.cards.length;q++){
                       if(compHand.cards[q].rank == rank){
                           //System.out.println("To be nullified(in triplet)   ");
                           //Card.printCard(compHand.cards[q]);
                           compHand.cards[q] = null;
                       }
                   }
                   compHand = Deck.makeSmaller(compHand);
                   //System.out.println();
                   //Deck.printDeck(deck1);
                   //System.out.println();
               }
                
               if(i==4){
                   prob4 = new Deck(deck1.cards.length);
                   prob4.copyDeck(deck1);
                   //Deck.printDeck(prob4);
               }
               else{ 
                   prob2 = new Deck(deck1.cards.length);
                   prob2.copyDeck(deck1);
                   //Deck.printDeck(prob2);
               }
           }
            
           else{
               Deck deck2 = fixSequence(compHand);
               deck2 = Deck.makeSmaller(deck2);
               if(deck2.cards.length>=2){
                   int w = Deck.findIndex(compHand,deck2.cards[0]);
                   int n = w;
                   for(;w<deck2.cards.length+n;w++){
                       //System.out.print("To be nullified(in sequence)   ");
                       //Card.printCard(compHand.cards[w]);
                       compHand.cards[w] = null;
                   }
                   compHand = Deck.makeSmaller(compHand); 
                   //System.out.println();
                   //Deck.printDeck(deck2);
                   //System.out.println();
               }
               
               if(i==1){
                   prob1 = new Deck(deck2.cards.length);
                   prob1.copyDeck(deck2);
                   //Deck.printDeck(prob1);
               }
               else if(i==3){
                   prob3 = new Deck(deck2.cards.length);
                   prob3.copyDeck(deck2);
                   //Deck.printDeck(prob3);
               }
               else if(i==5){
                   prob5 = new Deck(deck2.cards.length);
                   prob5.copyDeck(deck2);
                   //Deck.printDeck(prob5);
               }
           }
       }
       /*Deck.printDeck (prob1);
       System.out.println();
       System.out.println();
       System.out.println();
       Deck.printDeck (prob2);
       System.out.println();
       System.out.println();
       System.out.println();
       Deck.printDeck (prob3);
       System.out.println();
       System.out.println();
       System.out.println();
       Deck.printDeck (prob4);
       System.out.println();
       System.out.println();
       System.out.println();
       Deck.printDeck (prob5);
       System.out.println();
       System.out.println();
       System.out.println();
       //Deck.printDeck(compHand);*/
       
   }
   
   /**
    * This method, invoked at the end of every Computer turn checks if the computer has won the game.
    * If it has, it prints a message, prints out the sets and exits.
    */
   public void checkWin()throws Exception{
       //System.out.println("checkWin invoked");
       int length = (prob1.cards.length + prob2.cards.length + prob3.cards.length + prob4.cards.length + prob5.cards.length);
       //System.out.println("Length =   "+length);
       int numberOfProbs = 0;
       int n = 0;
       boolean isPair = false;
       //System.out.println("\n"+Deck.findRangeLength(prob1));
       //System.out.println("\n"+Deck.findRangeLength(prob2));
       //System.out.println("\n"+Deck.findRangeLength(prob3));
       //System.out.println("\n"+Deck.findRangeLength(prob4));
       //System.out.println("\n"+Deck.findRangeLength(prob5));
       if(length == 10){
           n = prob1.cards.length;
           if(Deck.isPair(prob1) && n >= 3){
               numberOfProbs++;
               //System.out.println("NumberOfProbs incremented at prob1(pair).   Now numberOfProbs =    "+ numberOfProbs);
           }
           else{
               if(n >= 3 && Deck.findRangeLength(prob1) == n-1){
                   numberOfProbs++;
                   //System.out.println("NumberOfProbs incremented at prob1(trip).   Now numberOfProbs =    "+ numberOfProbs);
                }
           }
           
           n = prob2.cards.length;
           if(Deck.isPair(prob2) && n >= 3){
               numberOfProbs++;
               //System.out.println("NumberOfProbs incremented at prob2(pair).   Now numberOfProbs =    "+ numberOfProbs);
           }
           else{
               if(n >= 3 && Deck.findRangeLength(prob2) == n-1){
                   numberOfProbs++;
                   //System.out.println("NumberOfProbs incremented at prob2(trip).   Now numberOfProbs =    "+ numberOfProbs);
                }
           }
           
           n = prob3.cards.length;
           if(Deck.isPair(prob3) && n >= 3){
               numberOfProbs++;
               //System.out.println("NumberOfProbs incremented at prob3(pair).   Now numberOfProbs =    "+ numberOfProbs);
           }
           else{
               if(n >= 3 && Deck.findRangeLength(prob3) == n-1){
                   numberOfProbs++;
                   //System.out.println("NumberOfProbs incremented at prob3(trip).   Now numberOfProbs =    "+ numberOfProbs);
                }
           }
           
           n = prob4.cards.length;
           if(Deck.isPair(prob4) && n >= 3){
               numberOfProbs++;
               //System.out.println("NumberOfProbs incremented at prob4(pair).   Now numberOfProbs =    "+ numberOfProbs);
           }
           else{
               if(n >= 3 && Deck.findRangeLength(prob4) == n-1){
                   numberOfProbs++;
                   //System.out.println("NumberOfProbs incremented at prob4(trip).   Now numberOfProbs =    "+ numberOfProbs);
                }
           }
           
           n = prob5.cards.length;
           if(Deck.isPair(prob5) && n >= 3){
               numberOfProbs++;
               //System.out.println("NumberOfProbs incremented at prob5(pair).   Now numberOfProbs =    "+ numberOfProbs);
           }
           else{
               if(n >= 3 && Deck.findRangeLength(prob5) == n-1){
                   numberOfProbs++;
                   //System.out.println("NumberOfProbs incremented at prob5(trip).   Now numberOfProbs =    "+ numberOfProbs);
                }
           }
           
           /*n = prob1.cards.length;
           if(n >= 3 && Deck.findRangeLength(prob1) == n)   numberOfProbs++;
           n = prob2.cards.length;
           if(n >= 3 && Deck.findRangeLength(prob2) == n)   numberOfProbs++;
           n = prob3.cards.length;
           if(n >= 3 && Deck.findRangeLength(prob3) == n)   numberOfProbs++;
           n = prob4.cards.length;
           if(n >= 3 && Deck.findRangeLength(prob4) == n)   numberOfProbs++;
           n = prob5.cards.length;
           if(n >= 3 && Deck.findRangeLength(prob5) == n)   numberOfProbs++;*/
           
           if(numberOfProbs == 3){
               Interface.printLine();
               Interface.printLine();
               Interface.printLine();
               Interface.slowPrint("THE COMPUTER HAS WON, HERE ARE ITS CARDS");
               Interface.printLine();
               Interface.printLine();
               Interface.printLine();
               Interface.slowPrint("HERE ARE ITS CARDS");
               
               if(prob1.cards.length>=3){
                   Deck.printDeck(prob1);
                   Interface.printLine();
               }
               
               if(prob2.cards.length>=3){
                   Deck.printDeck(prob2);
                   Interface.printLine();
               }
               
               if(prob3.cards.length>=3){
                   Deck.printDeck(prob3);
                   Interface.printLine();
               }
               
               if(prob4.cards.length>=3){
                   Deck.printDeck(prob4);
                   Interface.printLine();
               }
               
               if(prob5.cards.length>=3){
                   Deck.printDeck(prob5);
                   Interface.printLine();
               }
              System.exit(0); 
               
           }
       }
   }
   
   /**
    * This method is like the headquarters for the computer's part of the game.
    * It invokes different methods which 'play' the game on behalf of the computer. 
    * @param game it is the game object which has all the attributes of a game.
    */ 
   public static void start(Game game)throws Exception{
       //System.out.println("Player.start has been invoked");
       game.player.compHand = Deck.makeSmaller(game.player.compHand);
       game.player.compHand = Deck.insertionSort(game.player.compHand);
       Deck.printDeck (game.player.compHand);
       
       //game.player.refreshCardsNeeded();
            
       pickCard(game);
       Player player = game.player;
       player.refreshCardsNeeded();
       
       //some parts of the older algorithm.
       /*int seqIndex = 0;
       int length = 0;
        //Deck deck;
       if(player.compHand.cards.length>=2){
           seqIndex =  Deck.checkSequence(player.compHand);//The integer is the place where the sequence starts.
           if(seqIndex>=0){
               length = Deck.seqLength(player.compHand,seqIndex);
                
           }
                
       }
        
       Deck deck = new Deck(length);
       for(int i = 0;i<length;i++){
           deck.cards[i] = player.compHand.cards[seqIndex];
       }
               
       if(length>=2){
           for(int i = 1;i<=5;i++){
               if(player.prob1.cards.length==0){
                   player.prob1.copyDeck(deck);
               }
               
               else if(player.prob2.cards.length==0){
                   player.prob2.copyDeck(deck);
               }
               
               else if(player.prob3.cards.length==0){
                   player.prob3.copyDeck(deck);
               }
                
               else if(player.prob4.cards.length==0){
                   player.prob4.copyDeck(deck);
               }
                
               else if(player.prob5.cards.length==0){
                   player.prob5.copyDeck(deck);
               }
                
           }
            
       }*/
      
       
       player.refreshProbabilities();
       player.discard = Deck.makeBigger(game.player.discard);
       player.discard.moveUp();
       player.compHand = Deck.makeSmaller(player.compHand);
       
       //this block of the code decides which Card the computer throws. It is done by 
       //The  discard transaction starts here.
       int bad = 0;
       for(int i = 0;i<player.compHand.cards.length;i++){
           if(player.cardsNeeded[Deck.findIndex(player.compHand.cards[i])] == 0){
               bad = i;
               break;
           }
       }
       
       if(player.compHand.cards.length == 0){
           int length1 = player.prob1.cards.length;
           //System.out.println("\n@@@@@@length1 = " + length1 + "\n");
           int length2 = player.prob2.cards.length;
           //System.out.println("\n@@@@@@length2 = " + length2 + "\n");
           int length3 = player.prob3.cards.length;
           //System.out.println("\n@@@@@@length3 = " + length3 + "\n");
           int length4 = player.prob4.cards.length;
           //System.out.println("\n@@@@@@length4 = " + length4 + "\n");
           int length5 = player.prob5.cards.length;
           //System.out.println("\n@@@@@@length5 = " + length5 + "\n");
           if(length1 == 2){
               player.compHand = Deck.copyDeck(player.compHand, player.prob1);
               //System.out.println("\n@@@@@@\n");
               //Deck.printDeck(player.compHand);
               //System.out.println("\n@@@@@@\n");
               player.prob1 = new Deck(0);
           }
           else if(length2 == 2){
               player.compHand = Deck.copyDeck(player.compHand, player.prob2);
               //System.out.println("\n@@@@@@\n");
               //Deck.printDeck(player.compHand);
               //System.out.println("\n@@@@@@\n");
               player.prob2 = new Deck(0);
           }
           else if(length3 == 2){
               player.compHand = Deck.copyDeck(player.compHand, player.prob3);
               //System.out.println("\n@@@@@@\n");
               //Deck.printDeck(player.compHand);
               //System.out.println("\n@@@@@@\n");
               player.prob3 = new Deck(0);
           }
           else if(length4 == 2){
               player.compHand = Deck.copyDeck(player.compHand, player.prob4);
               //System.out.println("\n@@@@@@\n");
               //Deck.printDeck(player.compHand);
               //System.out.println("\n@@@@@@\n");
               player.prob4 = new Deck(0);
           }
           else if(length5 == 2){
               player.compHand = Deck.copyDeck(player.compHand, player.prob5);
               //System.out.println("\n@@@@@@\n");
               //Deck.printDeck(player.compHand);
               //System.out.println("\n@@@@@@\n");
               player.prob5 = new Deck(0);
           }
           //This block may throw an ArrayIndexOutOfBounds Exception. Sice the reason is very puzzling, Rishi Sir asked me to leave it.
           //System.out.println("\n^^^^^^^^^^^\n");
           //Deck.printDeck(player.compHand);
           //System.out.println("\n^^^^^^^^^^^\n");
           int card1Val = player.cardsNeeded[Deck.findIndex(player.compHand.cards[0])];
           int card2Val = player.cardsNeeded[Deck.findIndex(player.compHand.cards[player.compHand.cards.length-1])];
           if(card1Val > card2Val)   bad = 1;
           else   bad = 0;
       }
       
       
        
       player.discard.cards[0] = player.compHand.cards[bad];
       //System.out.print(player.cardsNeeded[Deck.findIndex(player.compHand.cards[bad])]);
       //Card.printCard(player.compHand.cards[bad]);
       
       player.compHand.cards[bad] = null;
       Deck.makeSmaller(player.compHand);
       //The  discard transaction ends here.
       player.checkWin();
       
       //the following part of the code is to consolidate the probablilities back into compHand. This will be ommited in later versions of the game.
       Deck hand1= new Deck(player.compHand.cards.length + player.prob1.cards.length + player.prob2.cards.length + player.prob3.cards.length + player.prob4.cards.length + player.prob5.cards.length);
       player.compHand = Deck.makeSmaller(player.compHand);
       player.prob1 = Deck.makeSmaller(player.prob1);
       player.prob2 = Deck.makeSmaller(player.prob2);
       player.prob3 = Deck.makeSmaller(player.prob3);
       player.prob4 = Deck.makeSmaller(player.prob4);
       player.prob5 = Deck.makeSmaller(player.prob5);

       int w = 0;
       //System.out.println("compHand being merged");
       for(int n = 0;n<player.compHand.cards.length;n++){
           hand1.cards[w] = player.compHand.cards[n];
           w++;
       }
       
       //System.out.println("prob1 being merged");
       for(int n = 0; n<player.prob1.cards.length; n++){
           hand1.cards[w] = player.prob1.cards[n];
           w++;
       }
       
       //System.out.println("prob2 being merged");
       for(int n = 0; n<player.prob2.cards.length; n++){
           hand1.cards[w] = player.prob2.cards[n];
           w++;
       }
       
       //System.out.println("prob3 being merged");
       for(int n = 0; n<player.prob3.cards.length; n++){
           hand1.cards[w] = player.prob3.cards[n];
           w++;
       }
       
       //System.out.println("prob4 being merged");
       for(int n = 0; n<player.prob4.cards.length; n++){
           hand1.cards[w] = player.prob4.cards[n];
           w++;
       }
       
       //System.out.println("prob5 being merged");
       for(int n = 0; n<player.prob5.cards.length; n++){
           hand1.cards[w] = player.prob5.cards[n];
           w++;
       }
       
       player.compHand = new Deck(hand1.cards.length);
       player.compHand.copyDeck(hand1);
       player.compHand = Deck.makeSmaller(player.compHand);
       player.compHand = Deck.insertionSort(player.compHand);
       //System.out.println("CompHand");
       //Deck.printDeck(player.compHand);
       Interface.printLine();
       Interface.printLine();
       System.out.println("THE COMPUTER HAS FINISHED PLAYING ITS TURN!! IT IS YOUR TURN");
       Interface.printLine();
       Interface.printLine();
       game.player = player;
   }
   
   /**
    * As the player decides intutively which Card to pick he/she will surely use some 'parameters'. Pick card uses cards needed and other such 'parameters' to decide which card to
    * pick.
    * @param game it is the game object which has all the attributes of a game.
    */ 
   public static void pickCard(Game game)throws Exception{
       
       Card card = game.player.discard.cards[0];
       Card card1 = game.pack.cards[0];
       game.player.compHand = Deck.makeBigger(game.player.compHand);
       if(game.player.cardsNeeded[card.suit*13+card.rank-1]>=1){
           game.player.compHand = Deck.makeSmaller(game.player.compHand);
           game.player.compHand = Deck.makeBigger(game.player.compHand);
           game.player.compHand.cards[game.player.compHand.cards.length-1] = card;
       }
       else{
           game.player.compHand = Deck.makeSmaller(game.player.compHand);
           game.player.compHand = Deck.makeBigger(game.player.compHand);
           game.player.compHand.cards[game.player.compHand.cards.length-1] = game.pack.cards[0];
           game.pack.cards[0] = null;
           Deck.makeSmaller(game.pack);
       }
       
       Deck forIndex = new Deck();
       game.player.compHand = Deck.insertionSort(game.player.compHand);
       //Deck.printDeck(game.player.compHand);
       //System.out.println();
       //System.out.println("This was the discard card");
       //Card.printCard(card);
       //System.out.println(game.player.cardsNeeded[Deck.findIndex(forIndex,card)]);
       //System.out.println("This was the pack card");
       //Card.printCard(card1);
       //System.out.println(game.player.cardsNeeded[Deck.findIndex(forIndex,game.player.compHand.cards[game.player.compHand.cards.length-1])]);
   }
   
   //A tester method built to check some parts of the code.
   /*public static void testRank () throws Exception {
       Deck d = new Deck();
       int i=0;
       while (i<10) {
           d.shuffleDeck();
           Deck sub = Deck.subDeck (d, 0, 10);
           sub = Deck.insertionSort (sub);
           Deck.printDeck (sub);
           System.out.println ("\nfindRank invoked\n\n");
           System.out.println (Deck.findRank(sub));
           System.out.println ("\n\n--------------------\n");
           i++;
        }
    }*/
   
   /**
    * This method takes a Deck object(the Computer's hand) and extracts the pairs, triplets, fourOfAKinds which it puts into another Deck and returns.
    * @param deck The computer's hand. 
    * @return deck - Later becomes one of the probabilities.
    */
   public static Deck fixFourOfAKind(Deck deck)throws Exception{ 
       //System.out.println("fixFourOfAKind has been invoked");
       boolean trueFlag = Deck.isPair(deck);
       int setLength = 0;
       int rank = 0;
       Deck set = new Deck(0);
       if(trueFlag){
           
           setLength = Deck.findSameRankSize(deck);
           set = new Deck(setLength);
           //System.out.println("set.cards.length   "+ set.cards.length);
           rank = Deck.findRank(deck);
           int index = 0;
           for(int i = 0;i<deck.cards.length;i++){
                if(deck.cards[i].rank == rank){
                    set.cards[index] = deck.cards[i];
                    index++;
                }
           }
       }
       
       //System.out.println("trueFlag = " + trueFlag + "  setLength = " + rank + "  rank = " + rank);
       //System.out.println("being returned from fixFourOfAKind");
       Deck.printDeck(set);
       return set;   
   }
   
   /**
     * This method takes a Deck object(the Computer's hand) and extracts the sequences which it puts into another Deck and returns.
     * @param deck The computer's hand. 
     * @return deck - Later becomes one of the probabilities.
    */
   public static Deck fixSequence(Deck deck)throws Exception{
       //System.out.println("In fixSequence");
       int[] deckH = Deck.deckHist(deck);
       deck = Deck.insertionSort(deck);
       int seqLength = 0;
       Deck difference = new Deck();
       int masterSeqLength = 0;
       int index = 0;
       int totalGap = 0;
       Deck sequence = new Deck(0);
       int gap = 0;
       Card carda = new Card();
       Card cardb = new Card();
       for(int i = 51;i>=0;i--){
           
           if(deckH[i] == 0)   gap++;  
           
           if(gap == 2){
               gap = 0;
               seqLength = 0;
               totalGap = 0;
            }
            
           if(deckH[i] == 1){
               
               if(seqLength == 0)   gap = 0;
               seqLength++;
               cardb = carda;
               carda = difference.cards[i];
               if(((cardb.rank == 1||cardb.rank == 2))){
                   
                   if(carda.rank == 12 || carda.rank == 13){
                       
                       if(carda.suit+1 == cardb.suit){
                           seqLength--;
                           continue;
                       }
                   }
               }
               else{
                   //seqLength++;
                   if(seqLength > 0 && gap == 1)   totalGap++;
                   gap = 0;
                   
                   if(seqLength>=masterSeqLength){
                       masterSeqLength = seqLength;
                       index = i;
                   }
                   
                   if (totalGap + seqLength >=4 ) break;
                   
               }
           }
            
           if(seqLength+totalGap>=4)   break;
           
           if(masterSeqLength >= 2 && gap ==1){
               
               if(i>0){
                   
                   if(deckH[i-1] == 0){
                       break;
                    }
               }
            } 
           /*for(int n = index;n<length+index;n++){
               deck[n] = null;
           }*/
           //return sequence;
       }
       if(masterSeqLength>=2){
           Deck random = new Deck();
       
           Card card1 = random.cards[index];
           // if(gap == 0)   seqLength--;
           //System.out.println ("index= " + index + " totalGap= " + totalGap + " seqLength= " + seqLength + " array length= " + random.cards.length + "masterSeqLength" + masterSeqLength);
           Card card2 = random.cards[index+totalGap+seqLength-1];
           //System.out.print("Card1   ");
           //Card.printCard(card1);
           //System.out.print("Card2   ");
           Card.printCard(card2);
           index = Deck.findIndex(deck,card1);
           //System.out.println ("index =" + index + " card1= " + card1);
           int high = Deck.findIndex(deck,card2);
           //System.out.println ("high =" + high + " card2= " + card2);
           sequence = Deck.subDeck(deck,index,high);
           //System.out.println("being returned from fixSequence");
           //Deck.printDeck(sequence);
       }
       return sequence;
   }
   
   //Code used for testing
   
   /* public static void main(String []args)throws Exception{
       
       Player n = new Player();
       n.compHand = new Deck (0);
       n.prob1 = new Deck(4);
       n.prob1.cards[0] = new Card(0,1);
       n.prob1.cards[1] = new Card(1,1);
       n.prob1.cards[2] = new Card(2,1);
       n.prob1.cards[3] = new Card(3,1);
       n.prob3 = new Deck(3);
       n.prob3.cards[0] = new Card(0,3);
       n.prob3.cards[1] = new Card(0,4);
       n.prob3.cards[2] = new Card(0,5);
       n.prob4 = new Deck(3);
       n.prob4.cards[0] = new Card(2,2);
       n.prob4.cards[1] = new Card(2,3);
       n.prob4.cards[2] = new Card(2,4);
       n.prob2 = new Deck(0);
       n.prob5 = new Deck(0);
       n.checkWin();
       
       /*n.compHand.cards[0] = new Card(0,1);
       n.prob1.cards[0] = new Card(0,2);
       n.prob2.cards[0] = new Card(0,3);
       n.prob3.cards[0] = new Card(0,4);
       n.prob4.cards[0] = new Card(0,5);
       n.prob5.cards[0] = new Card(0,6);
       n.refreshCardsNeeded();
       Deck deck = new Deck(11);
       deck.cards[0] = new Card(0,1);
       deck.cards[1] = new Card(0,2);
       deck.cards[2] = new Card(0,3);
       deck.cards[3] = new Card(0,4);
       deck.cards[4] = new Card(0,5);
       deck.cards[5] = new Card(0,6);
       deck.cards[6] = new Card(0,7);
       deck.cards[7] = new Card(0,8);
       deck.cards[8] = new Card(0,9);
       deck.cards[9] = new Card(0,10);
       deck.cards[10] = new Card(0,11);
       //deck.cards[9] = new Card(3,9);
       //deck.cards[10] = new Card(3,11);
       Deck deck1 = fixFourOfAKind(deck);
       Deck.printDeck(deck1);
       deck1 = Deck.makeSmaller(deck1);
       if(deck1.cards.length == 0)   System.out.println("null");
       

       
        
   }*/
        
}