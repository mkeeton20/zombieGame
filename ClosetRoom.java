
/**
 * Write a description of class ClosetRoom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ClosetRoom extends Room
{
    public String key = "";
    public boolean hasKey = false;
   public ClosetRoom(String description) 
    {
        super(description);
        String key= "";
    }
    
    public void grab(Command command)
    {
        
            System.out.println("You grab the key off the floor " +
                "it is added to your inventory");
                hasKey = true;
        
    }
    public void makeItem(){
    
    String key = "Key";
    
    }
    public boolean itemCheck(){
    
      return hasKey;
    }
}
