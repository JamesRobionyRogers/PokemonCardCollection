// Importing Librarys  
// import ecs100.*;     // shouldnt need any of these libs  
// import java.awt.Color; 

/** Card support class for CardCollection
 * a Card contains an id, name, monetary value and a image
 * 
 * @author James Robiony-Rogers 
 * @version 1st July 2021 */
public class Card {
    // Fields 
    private int id; 
    private String name; 
    private int value; 
    private String imgFile; 
    // TODO: specify the absolute path for home computer 
    // private static String abPath = "C:\\Users\\James Robiony-Rogers\\OneDrive - Onslow College\\2021 Yr 13\\13DTC\\2021-06-24 - Assessment\\PokemonCardCollection\\src\\img\\default-card.png";
    // public static final String DEFAULT_CARD = abPath;
    /** Default card img file */
    public static final String DEFAULT_CARD = "default-card.png"; 
    
    
    
    /**
     * Constructor for objs of the Card class
     * @param id (int) - cards id number
     * @param name (String) - cards name
     * @param value (int) - cards monatary value 
     * @param imgFile (String) - cards img file path */
    public Card(int id, String name, int value, String imgFile) {
        // Initialising the instance vairables
        this.id = id;
        this.name = name;
        this.value = value;
        this.imgFile = imgFile;
    }

    /** Constructor overloading 
     * @param id (int) - cards id number
     * @param name (String) - cards name
     * @param value (int) - cards monatary value */
    public Card(int id, String name, int value) {
        /** if only id, name and value are passed, the constructor will run and
         * set the imgFile to DEFAULT_CARD rather than throwing an error */
        this(id, name, value, DEFAULT_CARD); 
    }

    /** Getter Method: Returning the cards id 
     * @return int - returns the cards ID */
    public int getID() {
        return this.id;
    }

    /** Getter Method: Returning the cards name 
     * @return String - returns the cards name */
    public String getName() {
        return this.name; 
    }

    /** Getter Method: Returning the cards value 
     * @return int - returns the cards value */
    public int getValue() {
        return this.value; 
    }

    /** Getter Method: Returning the cards imgFile 
     * @return String - returns the cards imgFile path */
    public String getImgFile() {
        return this.imgFile;
    }
}
