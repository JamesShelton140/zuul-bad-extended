package zuul;

import zuul.characters.Player;

import java.util.Arrays;
import java.util.HashSet;

public class ZuulMap extends Map {

    public ZuulMap() {
        super();
        createRooms();
        createCharacters();
    }

    /**
     * Create all the characters
     */
    @Override
    protected void createCharacters() {
        Character player1 = new Player("player1", getDefaultStartingRoom());
        Character jimmy = new Player("Jimmy", getDefaultStartingRoom());

        Arrays.stream(new Character[] {player1, jimmy}).forEach(this::addCharacter); //Add all characters to list
    }

    /**
     * Create all the rooms and link their exits together.
     */
    @Override
    protected void createRooms() {

        Room outside, theatre, pub, lab, office;

        // create the rooms
        outside = new Room("outside", GameText.getString("outside"));
        theatre = new Room("theatre", GameText.getString("theatre"));
        pub = new Room("pub", GameText.getString("pub"));
        lab = new Room("lab", GameText.getString("lab"));
        office = new Room("office", GameText.getString("office"));

        // initialise room exits //zuul.Room north, zuul.Room east, zuul.Room south, zuul.Room west)
        outside.setExits(new String[]{GameText.getString("east"), GameText.getString("south"), GameText.getString("west")},
                new Room[]{theatre, lab, pub});
        outside.getInventory().addItem(new Item(GameText.getString("notebook"), 2));
        theatre.setExits(new String[]{GameText.getString("west")}, new Room[]{outside});
        pub.setExits(new String[]{GameText.getString("east")}, new Room[]{outside});
        lab.setExits(new String[]{GameText.getString("north"), GameText.getString("east")},
                new Room[]{outside, office});
        office.setExits(new String[]{GameText.getString("west")}, new Room[]{lab});

        setDefaultStartingRoom(outside);  // start game outside
        Arrays.stream(new Room[]{outside, theatre, pub, lab, office}).forEach(this::addRoom); //Add all rooms to list
    }

    @Override
    public void printWelcome() {
        System.out.println();
        System.out.println(GameText.getString("welcome_ln1"));
        System.out.println(GameText.getString("welcome_ln2"));
        System.out.println(GameText.getString("welcome_ln3",
                new Object[] {GameText.getString("CommandWordsBundle", "help")}));
        System.out.println();
        //getDefaultStartingRoom().printInfo(); //Print info for default starting room (where player starts)
    }
}
