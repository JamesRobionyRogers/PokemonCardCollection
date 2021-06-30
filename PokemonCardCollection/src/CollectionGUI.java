// Importing Librarys
import ecs100.*;
import java.util.HashMap;  
import java.awt.Color; 

/** CollectionGUI Driver Class 
 * CollectionGUI runs the program and creates the GUI  
 * CollectionGUI does not take any inital params 
 * 
 * @author James Robiony-Rogers
 * @version 1st July 2021 */
public class CollectionGUI {
    
    private Collection pkc;     // used for retreving the card collecion 
    private Card pkCard;        // used to store the current pokemon card

    boolean hide = false; 

    // GUI: Card Image Fields
    private int imgLocX = 100; 
    private int imgLocY = 40;
    private static final double WIDTH = 150;
    private static final double HEIGHT = (WIDTH * 1.3968);        // card ratio 
    private String imgFolder = "img/";

    // GUI: Card Details Fields
    private double leftLocX = 100;
    private double nameLocY = (40 + HEIGHT + 20);
    private double valueLocY = (this.nameLocY + 20);
    private static final int FONT_SIZE = 15; 
    // Card boundary
    private static final int MINVALUE = 0;
    private static final int MAXVALUE = 1000000;

    /** Constructor: Initialising the UI by adding buttons, text fields etc */
    public CollectionGUI() {
        // Initialising and setting properties
        pkc = new Collection();     // initialising the collection obj 

        // TODO: Un comment all of the UI.

        // UI.initialise();            // initialising the ECS100 UI lib
        // UI.setColor(Color.black); 
        // UI.setFontSize(FONT_SIZE);

        // // Adding funtionality to the GUI using Buttons and Text Fields
        // UI.addButton("Add Pokemon Card", this::addCardGUI); 
        // UI.addButton("View All Cards", this::viewAllGUI); 
        // UI.addTextField("Find Card", this::findCardGUI);
        // UI.addButton("Hide Details", this::hideDetails); 

        // // Adding mouse funtionality 
        // UI.setMouseListener(this::doMouse);

        // UI.addButton("Quit", UI::quit);

    }

    /** GUI component of the addCard mathod */
    public void addCardGUI() {
        String addImg = ""; 
        // Ask for the card's details 
        // TODO: Document the pre .trim() and post .trim()
        String name = UI.askString("Pokemon Cards Name: ").trim();              
        int value = UI.askInt("Card Value [rounded, no commers]:  $"); 
        // Continues to ask the user to add an img until a Y or N is provided 
        do { 
            // TODO: Working on Y/N do while 
            addImg = UI.askString("Would you like to add an image [Y/N]: ");
            System.out.println(addImg);

        } while (!addImg.equalsIgnoreCase("Y") && 
                 !addImg.equalsIgnoreCase("N")); 
        

        String imgFileName = Card.DEFAULT_CARD;    // setting the default img 
        
        // Resetting the img path for custom image is required 
        if (addImg.equalsIgnoreCase("Y")) {
            // Change file name if user chooses an img file
            imgFileName = UIFileChooser.open("Choose Image File");   
        }
    
        
        // Boundary Check
        try { 
            boolean inMap = false;
            boolean inRange = false; 

            // Itterating throuch collection checking for duplicate name
            for (int cardID : this.pkc.getCollection().keySet()) {

                Card itteratedPkCard = this.pkc.getCollection().get(cardID);

                // Checking for duplicate name 
                if (name.equalsIgnoreCase(itteratedPkCard.getName())) {
                    inMap = true;
                    throw new SecurityException();
                     
                }
            }

            // Checking for inconcistsnt values
            if (value >= MINVALUE && value <= MAXVALUE) {
                inRange = true; 
            }
            // Throwing the out of range exception 
            else {
                throw new ArithmeticException(); 
            }

            // Means no duplicate name & card can be added to collection 
            if (!inMap && inRange) {
                // Add the new pokemon card to the collection
                if (imgFileName.contains(".jpeg") || 
                    imgFileName.contains(".jpg") || 
                    imgFileName.contains(".png")) {

                    pkc.addCard(name, value, imgFileName);

                } 
                else 
                {
                    UI.println("Invalid img file. Default image used."); 
                    pkc.addCard(name, value);
                }

                
            }     
        } 
        // FIXME: Maybe change to custom exceptions 

        // Duplicate name error catching 
        catch (SecurityException se) {
            UI.println("Sorry cannot add this card. Duplicate Name."); 
        } 
        // Card value out of range exception
        catch (ArithmeticException ae) {
            UI.println("Sorry cannot add this card. Unrealistic card value.");
        }
        // General error catching 
        catch (Exception e) {
            UI.println("An error occured, please try again."); 
        }
    }
    
    /** Prints all Pokemon Cards in the collection to the GUI's console */
    public void viewAllGUI() {
        UI.println("");         // start of formatiing the console

        // Setting collection to the cardMap for clean code
        HashMap<Integer, Card> collection = pkc.getCollection(); 

        // Itterating through the cards within the collection
        for (int cardID : collection.keySet()) {
            
            // Setting pkCard to the itterated card from the collection
            Card tempPkCard = collection.get(cardID);   

            // Printing the deaild to the console 
            UI.println("ID: " + tempPkCard.getID()); 
            UI.println(tempPkCard.getName()); 
            UI.println("Value: \t$" + tempPkCard.getValue()); 
            UI.println("");     // formatting
        }

        UI.println("");     // formatting
    }

    /** Finds a Pokemon Card of a given name and displays the image and details
     * on the GUI
     * @param cardName (String) - textField returns the text inputted into it */
    public void findCardGUI(String cardName) {
        
        boolean inMap = false;      // conditional var for duplicate card name

        // Setting collection to the cardMap for clean code
        HashMap<Integer, Card> collection = pkc.getCollection();

        // Itterating through all cards in the collection and check the name 
        for (int cardID : collection.keySet()) {
            // Setting pkCard to the itterated card from the collection
            Card tempPkCard = collection.get(cardID); 

            // Checking if the card's name is equal to the input 
            if (tempPkCard.getName().equalsIgnoreCase(cardName)) {
                inMap = true; 

                // Setting the current card 
                this.pkCard = tempPkCard;  

                // Displaying the cards image and details
                this.clearCardArea();
                
                this.displayCard(this.pkCard);
                this.displayDetails(this.pkCard); 
            }
        }
        // After itterating and still not in collection
        if (!inMap) {
            UI.println(
                "\nThe Pokemon Card you were looking for was not found.");
            UI.println("Please check your spelling and then try again.\n");
        }
    }

    
    /** Displays the current Pokemon Cards image on the GUI 
     * @param tempPkCard (Card) - current card is passed through for details */
    public void displayCard(Card tempPkCard) {
        String path = this.imgFolder + tempPkCard.getImgFile(); 

        // TODO: Account for a null getImgFile() string (occurs when you click cancel on the the choose img screen)

        // IF the image is located somewhere other than the img folder 
        if (tempPkCard.getImgFile().contains(":")) {
            UI.drawImage(tempPkCard.getImgFile(), this.imgLocX, this.imgLocY, 
                                                                WIDTH, HEIGHT); 
            System.out.println("Path: " + tempPkCard.getImgFile());         
        } 
        else 
        {
            UI.drawImage(path, this.imgLocX, this.imgLocY, WIDTH, HEIGHT);
            System.out.println("Path: " + path);
        }
    }
    
    /** Displays the current Pokemon Cards details on the GUI
     * @param tempPkCard (Card) - current card is passed through for details */
    public void displayDetails(Card tempPkCard) {
        // Setting vairable for cleaner code 
        String name = tempPkCard.getName();
        String value = "$" + Integer.toString(tempPkCard.getValue()); 

        // Drawing etails to the GUI 
        UI.drawString(name, this.leftLocX, this.nameLocY);
        UI.drawString(value, this.leftLocX, this.valueLocY);
    }

    /** Clearing the Pokemon Card from the GUI */
    public void clearCardArea() {
        // Setting the area to be cleared 
        double topLeftX = (this.imgLocX - 1); 
        double topLeftY = (this.imgLocY - 1); 
        double totalWidth = (this.WIDTH + 100);
        double totalHeight = (this.valueLocY + 10); 

        // Clearing the area using a white rect 
        UI.setColor(Color.white); 
        UI.fillRect(topLeftX, topLeftY, totalWidth, totalHeight);
        UI.setColor(Color.black);       // setting colour back  
    }

    /** Draws a rect over the Pokemon Cards details, hiding them from the user*/
    public void clearDetails() {
        // Defigning the area to hide
        double topLeftX = (this.imgLocX - 1);
        double topLeftY = (this.imgLocY + this.HEIGHT + 5);
        double totalWidth = (this.WIDTH + 100);
        double totalHeight = 50;

        UI.setColor(Color.white);
        UI.fillRect(topLeftX, topLeftY, totalWidth, totalHeight);
        UI.setColor(Color.black);
    }

    /** Hides the pokemon cards details from the GUI */
    public void hideDetails() {
        if (this.hide) {
            this.hide = false;
            
            System.out.println("Hide: " + this.hide);
            System.out.println("Card" + this.pkCard.getName()); 

            // Printing back the details of the current card
            this.displayDetails(this.pkCard);       
        }

        else {
            this.hide = true;
            System.out.println("Hide: " + this.hide); 
            this.clearDetails();
        }
    }

    /** doMouse
     * @param action (String) - Passed from the mouseListener
     * @param x (double) - x pos of mouse passed from the mouseListener
     * @param y (double) - y pos of mouse passed from the mouseListener */
    public void doMouse(String action, double x, double y) { 

        // Checking if card has been clicked 
        if (action.equals("clicked")) {
            // Checking if the mouses pos is with-in the card image area
            if ((x >= this.imgLocX) && (x <= (this.imgLocX + this.WIDTH)) && 
                (y >= this.imgLocY) && (y <= (this.imgLocY + this.HEIGHT))) {

                this.hideDetails();
            }
        }
    }


    /** Running an instance of the CollectionGUI 
     * @param args (String[]) - Standard */
    public static void main(String[] args) throws Exception {
        new CollectionGUI();        // running the constructor
    }

}
