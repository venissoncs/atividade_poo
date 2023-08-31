/**
 * Aluno: Vênisson Cardoso dos Santos – 201700063182
 * Aluno: Swyann Vitor Rodrigues dos Santos - 202100045831
 */
import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private Item item;
    private List<Item> items;

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem(){
        return item;
    }
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExit(String direction, Room neighbor) 
    {
            exits.put(direction, neighbor);
    }

    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    public String getExitString(){
        String exitString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit: keys) {
            exitString += " " + exit;
        }
           
        return exitString;
    }

    public void addItem(Item item){
        items.add(item);
    }

    public String getItemDescription() {
        String itemDescription = "Items:";
        for (Item item : items) {
            itemDescription += " " + item.getDescription();
        }
        return itemDescription;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + "\n" + getItemDescription();
    }
}
