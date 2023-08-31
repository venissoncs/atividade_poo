/**
 Questão 8.5 Aula 19
 Aluno: Vênisson Cardoso dos Santos – 201700063182
 Aluno: Swyann Vitor Rodrigues dos Santos - 202100045831
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room lastRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office, cellar;

      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        cellar = new Room("in the cellar");
        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);
    
        office.setExit("west", lab);
        office.setExit("down", cellar);

        cellar.setExit("up", office);

        currentRoom = outside;  // start game outside

        Item Cracha = new Item("Um cartão de acesso a portas.", 0.05);
        Item Guarda_Chuva = new Item("Um guarda-chuva vermelho com listras brancas", 1);
        Item Roteiro = new Item("Um bloco de papel com o roteiro da peça", 0.1);
        Item Caneca = new Item("Uma caneca de café vazia", 0.2);
        Item Laterna = new Item("Uma laterna com a bateria fraca", 0.3);
        
        office.setItem(Cracha);
        outside.setItem(Guarda_Chuva);
        theater.setItem(Roteiro);
        pub.setItem(Caneca);
        cellar.setItem(Laterna);

        
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
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printLocationInfo()
    {
        System.out.println("You are " + currentRoom.getDescription());
        System.out.println(currentRoom.getItemDescription());
        System.out.println("Exits: ");
        if(currentRoom.getExit("north") != null){
            System.out.println("north ");
        }
        if(currentRoom.getExit("east") != null) {
            System.out.println("east ");
        }
        if(currentRoom.getExit("south") != null) {
            System.out.println("south ");
        }
        if(currentRoom.getExit("west") != null){
            System.out.println("west ");
        }
        System.out.println();
    }
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println(currentRoom.getLongDescription());
        System.out.println();
        printLocationInfo();
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
        else if(commandWord.equals("back")) {
            goBack();
        }
        else if(commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("eat")) {
            eat();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

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
        parser.showCommands();
        System.out.println("   go quit help");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goBack(){
        if(lastRoom != null) {
            currentRoom = lastRoom;
            printLocationInfo();
        } else {
            System.out.println("You haven't been anywhere yet.");
        }

    }
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        lastRoom = currentRoom;

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            lastRoom = currentRoom; // Atualiza a última sala visitada antes de mudar de sala
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    private void eat(){
        System.out.println("You have eaten now and you are not hungry any more");
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
