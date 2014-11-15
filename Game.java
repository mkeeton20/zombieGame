import java.util.Random;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.08
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Random rand;
    private int timer;
    private int count;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        rand = new Random();
        timer = rand.nextInt(6) + 7;
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, entrance, kitchen, hall, diningRoom, office, secondHall, balcony, closet, familyRoom, thirdHall, bath, room2, room3, playerRoom; 
      
        // create the rooms
        outside = new Room("outside");
        entrance = new Room("enterance");
        kitchen = new Room("kitchen");
        hall = new Room("hall");
        diningRoom = new Room ("Dining Room");
        office = new Room("office");
        secondHall = new Room("Second Floor Hall");
        balcony = new Room("balcony");
        closet = new Room("closet");
        familyRoom = new Room("Family Room");
        thirdHall = new Room("Third Floor Hall");
        bath = new Room("Bath");
        room2 = new Room("Room2");
        room3 = new Room("Room3");
        playerRoom = new Room("Player Room");
        
        
        
        // initialise room exits
        outside.setExit("south", entrance);
        
        entrance.setExit("north", outside);
        entrance.setExit("east",kitchen);
        
        kitchen.setExit("west", entrance);
        kitchen.setExit("south", diningRoom);
        
        diningRoom.setExit("north", kitchen);
        diningRoom.setExit("east", hall);
        
        hall.setExit("west", diningRoom);
        hall.setExit("east", office);
        hall.setExit("up", secondHall);
        
        office.setExit("west", hall);

        secondHall.setExit("down", hall);
        secondHall.setExit("up", thirdHall);
        secondHall.setExit("east", balcony);
        secondHall.setExit("west", closet);
        secondHall.setExit("south", familyRoom);
        
        familyRoom.setExit("north", secondHall);
        
        closet.setExit("east", secondHall);
        
        balcony.setExit("west", secondHall);

        thirdHall.setExit("down", secondHall);
        thirdHall.setExit("west", playerRoom);
        thirdHall.setExit("north", room2);
        thirdHall.setExit("east", bath);
        thirdHall.setExit("south", room3);
        
        
        playerRoom.setExit("north", thirdHall);
        
        bath.setExit("west", thirdHall);
        
        room2.setExit("south", thirdHall);
        
        room3.setExit("west", thirdHall);
        
        

        currentRoom = kitchen;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            if(timer==count){
                System.out.println("The Zombies Caught you and YOU Died AHHHHHHH D:");
                break;
            }
            if(currentRoom.getShortDescription().equals("outside")){
                System.out.println("You made it outside and you are now safe Congratulations!!");
                break;
            
            }
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing Goodbye!");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            count++;
            System.out.println(count);
            System.out.println(timer);
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
