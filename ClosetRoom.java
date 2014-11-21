
/**
 * Write a description of class ClosetRoom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ClosetRoom extends Room
{
   public ClosetRoom(String description) 
    {
        super(description);
    }
    
    public void grab(Command command)
    {
        if(command.getSecondWord().equals("key"))
        {
            System.out.println("You grab the key off the floor " +
                "it is added to your inventory");
        }
        else
        {
            super.grab(command);
        }
    }
}
