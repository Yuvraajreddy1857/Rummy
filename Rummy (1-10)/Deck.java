
/**
 * Write a description of class Deck here.
 * 
 * @author Nived 
 * @version Version 1 2013
 */
public class Deck{

    Card[] cards;

    /**
     * This is a parameterised constructor which creates a Deck object whick is an array of cards of desired length.
     */
    public Deck (int n) {
        cards = new Card[n];
    }
    
    /**
     * This is a non-parameterised constructor which creates a Deck object whick is an array of cards this array is fully
     * populated and has 52 Card objects. 
     */
    public Deck () {
        cards = new Card[52];
        int index = 0;
        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                cards[index] = new Card (suit, rank);
                index++;
            }
        }
    }
    
    /**
     * This method takes a Deck object and prints its contents in a user friendly format.
     * @param Deck object that needs to be printed.
     * 
     */
    public static void printDeck (Deck deck) throws Exception{
        for (int i=0; i<deck.cards.length; i++) {
            //if (deck.cards[i] == null) continue;
            Card.printCard (deck.cards[i]);
        }
    }
    
    /**
     * This method takes a Deck of Cards and two ints. It then swaps the references of the Cards at the two given 
     * indexes
     */
    public static void swapCard(Deck deck, int a, int b){
        //System.out.println( "swapCard"+a);
        //System.out.println("swapCard"+b);
        //if(!(deck.cards[a] == null||deck.cards[b] == null)){
                Card carda = new Card();
                carda = deck.cards[a];
                deck.cards[a] = deck.cards[b];
                deck.cards[b] = carda;
            //return deck;
           
        //}
   }
    
    /**
     * This method takes a Deck of Cards and then shuffles them up by swapping two cards at a time.
     */
    public static Deck shuffleDeck(Deck deck){
        for(int index = 0;index<deck.cards.length;index++){
            int random =  randomInt(0, deck.cards.length);
            swapCard(deck, index, random);
        }
        return deck;
    }
    
    /**
     * This is a non-static method which returns a copy of the Deck it is invoked on.
     */
    public void copyDeck(Deck a){
        int n;
        if(cards.length>a.cards.length)n = a.cards.length;
        else n = cards.length;
        for(int i = 0; i<n; i++){
            cards[i] = a.cards[i];
        }
    }
    
    /**
     * This is a static method which returns the Deck object where all the cards have been shifted one place forward(So the oneth card becomes the zeroeth card and so on). 
     */
    public void moveUp(){
        for(int i = cards.length-1;i>0;i--){
            cards[i] = cards[i-1];
        }
    }
    
    
    /**
     * This is a recursive method that takes any two limits(one higher and one lower)and return a random integer that lie between the limits(including both of them).
     * @param int low(lower limit), int high (higher limit)
     * @return a random integer generated by the computer
     */
    public static int randomInt(int low, int high){
        double random = Math.random();
        double x = random*high;
        int random1 = (int)x; 
        if(random1 >= low && random1 <= high) return random1;
        else randomInt(low, high);
        return random1;
    }
    
    /**
     * This method takes a Deck and two indexes high and low to specify the range. It then creates a new Deck of length
     * high-low+1 and assigns the corresponding values of the earlier deck to the newly created Deck. It essentially 
     * replicates part of a given Deck.
     * @param Deck deck
     * @param int high (high index)
     * @param int low (low index)
     * @return Deck
     */
    public static Deck subDeck (Deck deck, int low, int high) {
        Deck sub = new Deck (high-low+1);
        for (int i = 0; i<sub.cards.length; i++) {
            sub.cards[i] = deck.cards[low+i];
        }
        return sub;
    }
    
    /**
     * This method finds the smallest card in the given Deck.
     */
    public static Card findLowestCard(Deck deck, int low, int high){
        Card card = deck.cards[low];
        for(int i = low+1;i<=high; i++){
            if(Card.compareCard(card,deck.cards[i])==1)card = deck.cards[i];
        }
        return card;
    }
    
    /**
     * This is a sorting method which finds the lowest Card and switches it with the Card at the first place, then the second lowest Card is switched with the Card at the second place
     */
    public static Deck sortDeck(Deck deck){
        int index;
        for(int i = 0;i<deck.cards.length;i++){
            Card smallest = findLowestCard(deck, i,deck.cards.length-1);
            index = findIndex(deck, smallest);
            if(index == -1)break;
            swapCard(deck, index, i);
        }
        return deck;
    }
    
    /**
     * This is a sorting method that works on the algorithm of insertionSort.
     */
    public static Deck insertionSort(Deck deck){
        //deck = nullExchange(deck);
        for(int i = 1;i<deck.cards.length;i++){
            Card card = deck.cards[i];
            int toInsert = i;
            for(int r = i;r>0;r--){
                if(Card.compareCard(deck.cards[r-1],card)==1)toInsert = r-1;
            }
            
            for(int r = i;r>toInsert;r--){
                deck.cards[r] = deck.cards[r-1];
            }
            deck.cards[toInsert] = card;
        }
        return deck;
    }
    
   /* 
    * public static Deck nullExchange(Deck deck){
        int top = deck.cards.length-1;//4
        for(int i = 0;i<top;i++){//i = 2
            Card card = deck.cards[i];//null
            if(card == null){
                Card card2 = deck.cards[top];//0.3
                while(card2==null){
                   top --;
                    card2 = deck.cards[top];
                }
                swapCard(deck, i, top);
            }
            
        }
        return deck;
    }
    
    
    public static int[] nullHist(Deck deck){
        int[] nullH = new int[deck.cards.length];
        for(int i = 0;i<deck.cards.length;i++){
            if(deck.cards[i] == null)nullH[i] = -1;
        }
        return nullH;
    }
    */
        
    
    /**
     * This method returns the index of a card in a deck(the index  number by which the identical element is refered 
     * to by).
     * @param Deck a
     * @param Card b
     * @return int (index of the card in the deck
     */
    public static int findIndex(Deck a, Card b){
        for(int index = 0;index<a.cards.length;index++){
            int q = Card.compareCard(a.cards[index], b);
            if(q==0)return index;
        }
        return -1;
    }
    
    /**
     * This is a method that helps in avoiding NullPointerExceptions which occur very often when a loop traverses a deck and encounters a null object.
     * This method takes a Deck whick may contain null objects and returns a new Deck which is void of nulls.
     */
    public static Deck makeSmaller(Deck deck)throws Exception{
        int q = 0;
        for(int n = 0; n<deck.cards.length;n++){
            if(deck.cards[n] == null)q++;
        }
        
        //System.out.println(q);
        
        Deck deck1 = new Deck(deck.cards.length-q);
        //System.out.println(deck1.cards.length);
        q = 0;
        for(int i = 0; i<deck.cards.length;i++){
            if(deck.cards[i]==null)  continue;
            else{
                //Card.printCard(deck.cards[i]);
                deck1.cards[q] = deck.cards[i];
                q++;
            }
            
        }
        return deck1;
    }
    
    /**
     * This method takes a Deck and returns a new Deck which is bigger by one Card(an extra Card object is added at the back of the Deck)
     */
    public static Deck makeBigger(Deck deck){
        Deck deck1 = new Deck(deck.cards.length+1);
        for(int i = 0;i<deck.cards.length;i++){
            deck1.cards[i] = deck.cards[i];
        }
        return deck1;
    }
    
    /**
     * This method takes a Deck and returns a histogram which represents the frequency of each Card in the Deck.
     */
    public static int[] deckHist(Deck deck){
        int[] hist = new int [52];
        for( int i = 0;i<deck.cards.length;i++){
            Card card = deck.cards[i];
            if(card!=null)hist[card.suit*13+card.rank-1]++;
        }
        return hist;
    }
    
    //returns whether there is a sequence and where it starts from else -1
    /**
     * This method takes a Deck and checks whether it has a sequence in it(a set of cards where there are no gaps between the cards).
     * If it does, it returns the the index number of where it starts. However if there aren't any sequences, it returns -1;
     */
    public static int checkSequence(Deck deck){
        int[] deckH = deckHist(deck);
        int index = 0;//index of the place where the sequence starts
        int n = 0;
        for (int i = deck.cards.length-1;i>=0;i--){
            //System.out.println(i);
            if (deckH[i]>=1){
                n++;
                index = i;
                if(n==2)   break;
            }
        }
        if(n==2)   return index;
        return -1;
    }
    
    /**
     * This method is normally invoked with right after Deck.checkSequence.
     * It takes the index of where a sequence begins in a Deck and then returns the length of the sequence.
     */
    public static int seqLength(Deck deck, int starting){
        int length = 0;
        int[] deckH = deckHist(deck);
        for(int i = starting;i<deck.cards.length;i++){
            if(deckH[i]>=1)   length++;
            else   break;
        }
        return length;
    }
        
        
    
    public static void main(String[]args)throws Exception{
        /*Deck deck = new Deck(5);
        deck.cards[0] = new Card(0,1);
        deck.cards[2] = new Card(0,2);
        deck.cards[4] = new Card(0,3);
        printDeck(nullExchange(deck));*/
        Deck deck = new Deck(5);
        deck.cards[0] = new Card(0,1);
        deck.cards[2] = new Card(0,2);
        deck.cards[4] = new Card(0,3);
        printDeck(makeSmaller(deck));
        
        
    }
}
