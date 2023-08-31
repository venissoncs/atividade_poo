/**
 Aluno: Vênisson Cardoso dos Santos – 201700063182
 Aluno: Swyann Vitor Rodrigues dos Santos - 202100045831
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
        "go", "quit", "help", "look"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    public void getCommandList()
    {
        for(String command: validCommands) {
            System.out.println(command + " ");
        }
        System.out.println();
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    public void showAll()
    {
        for(String command: validCommands) {
            System.out.println(command + " ");
        }
    }
}
