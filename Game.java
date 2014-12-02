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
        outside = new Room("outside! Your surge of warm happiness quickly replaced by spine-chilling cold dread as you hear helpless screams In the distance.  As you look out into your neighborhood, you see fire spread throughtout, and people running for their lives. Zombies litter the streets, maiming and devouring any flesh they can get their hands on.  The end of the world seems to be upon us.");
        entrance = new Room("in the main enterance lobby. You can see the front door.");
        kitchen = new Room("in the kitchen. Pots, pans, silver, and piles of shattered glass cover the floor.  You must be careful where you step.")
        hall = new Room("in the first floor hall.  This is the main hall that leads to the kitchen and dining room.  You should be able to find an exit on this floor.")
        diningRoom = new Room ("in the dining room. You remember having countless dinners with your family here.  You wonder if there will ever be another one.");
        office = new Room("in the office. Your father used to work in here.  His work papers are spread about, but you are pretty sure that had nothing to do with the blast you felt earlier. The computer monitor is smashed to pieces on the ground, too.");
        secondHall = new Room("in the second floor hall. You remember running through this hall every day as a kid.  You would race your brother from end to end.");
        balcony = new Room("near the balcony.  Atleast a 20ft drop to the ground.  Maybe you should try to get to the first floor and find a door.");
        closet = new Room("in the closet.  Your mother always was a pack rat.  The closet is nothing more than a pile of miscellaneous garbage now.");
        familyRoom = new Room("in the family room.  The television has a huge hole in it.  What happened?");
        thirdHall = new Room("in the third floor hall.  The stairs to go down is in this hallway.");
        bath = new Room("This is the bathroom you and your brother share.  It was always too small.");
        room2 = new Room("in your brother's room.  Your older's brother room.  You carefully enter the room, stepping over piles of clothing. You approach the bed, but it is empty.  Must keep looking.");
        room3 = new Room("in your parents' room.  You enter in hopes this is just a game by your parents, but inside you just find a dark room with glass on the floor.  The windows on both walls are completely shattered.  The blast must have been monstrous to do this damage.");
        playerRoom = new Room("in your room.  THERE'S A ZOMBIE, GET OUT.");
        
        
        
        // initialise room exits
        
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
        
        room3.setExit("north", thirdHall);
        
        

        currentRoom = playerRoom;  // start game in player's room
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
        System.out.println("World of Zuul is a text-based computer game.");
        System.out.println(
        System.out.println("A strong wave of energy shakes you awake. You can feel the entire house shake to this outside force. ");
        System.out.println("It is dark, but you find yourself in your bedroom.");
        System.out.println("After trying to move, you feel stiff and full of aches.  You are hurt but you manage");
        System.out.println("To stumble to your feet.  As you gather your bearings, you realize there is a man in the room.");
        System.out.println("Upon further inspection, you realize that the figure is not a man, but a ZOMBIE!");
        System.out.println("You have to RUN! Escape the house before the zombie catches you!");
        System.out.println("The counter is how many moves you have left, you must escape the house");
        System.out.println("before the counter reaches zero or you DIE.");
        System.out.println(
        System.out.println("For further instructions, please type *help* ");
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
        else if (commandWord.equals("grab") && currentRoom.getShortDescription().equals("in the closet") ){
        
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
        System.out.println("You wake up alone in a house.");
        System.out.println("You can type 'go' and then any number of directions such as:");
        System.out.println("West, East, North, South, Up, Down. For example 'go north' or 'go up'");
        System.out.println("The point of the game is to escape before the timer is up.");
        System.out.println("GOOD LUCK!");
        System.out.println();
        System.out.println("Your command words are: go, quit, help, grab");
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
