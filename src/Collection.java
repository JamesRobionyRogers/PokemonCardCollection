// Importing Librarys 
import java.util.HashMap;  


/** Collection support class for CollectionGUI 
 * A collection contains a range of card objs and deals with them 
 * Collection does not take any inital params 
 * 
 * @author James Robiony-Rogers
 * @version 1st July 2021 */
public class Collection {
    // Instance Vairbales
    private HashMap<Integer, Card> cardMap;  // initialising the cardMap HashMap
    private int totalCards; 

    /** Constructor:    initialising the map, creating and adding cards to map*/
    public Collection() {
        // Initialising the intance vairables 
        this.cardMap = new HashMap<Integer, Card>(); //

        // Creating cards 
        Card pk1 = new Card(1, "Venusaur", 18300);
        Card pk2 = new Card(2, "Charizard", 311800);
        Card pk3 = new Card(3, "Blastoise", 45000);

        // Adding the cards to the map 
        this.cardMap.put(1, pk1); 
        this.cardMap.put(2, pk2);
        this.cardMap.put(3, pk3);

        this.totalCards = 3;         
    }

    /** Getter Mathod: Returning the Collection HashMap 
     * @return HashMap<Integer, Card> - returns the collection */
    public HashMap<Integer, Card> getCollection() {
        return this.cardMap; 
    }

    /** Lets the user add a pokemon card to the collection 
     * @param name (String) - name of the card 
     * @param value (int) - value of the card 
     * @param imgFile (String) - img file name 
     * @return boolean - returns if the card is in the collection already */
    public boolean addCard(String name, int value, String imgFile) {
        boolean inMap = false;

        // Boundary checking  -   Name Checks
        for (int cardID : this.cardMap.keySet()) {

            Card pkCard = this.cardMap.get(cardID);

            if (name.equalsIgnoreCase(pkCard.getName())) {
                inMap = true;  
            }
        }

        if (!inMap) {
            this.totalCards++; // used for the next cards id
            this.cardMap.put(this.totalCards, new Card(this.totalCards, name, 
                                                            value, imgFile));
             
        } 
        
        return inMap;
    }

    /** Method overloading 
     * @param name (String) - name of the card 
     * @param value (int) - value of the card 
     * @return boolean - returns if the card is in the collection already */
    public boolean addCard(String name, int value) {
        String imgFile = Card.DEFAULT_CARD; 
        boolean inMap = false;

        // Boundary checking - Name Checks
        for (int cardID : this.cardMap.keySet()) {

            Card pkCard = this.cardMap.get(cardID);

            if (name.equalsIgnoreCase(pkCard.getName())) {
                inMap = true;
            }
        }

        if (!inMap) {
            this.totalCards++; // used for the next cards id
            this.cardMap.put(this.totalCards, new Card(this.totalCards, name, 
                                                            value, imgFile));

        }

        return inMap;
    }

}
