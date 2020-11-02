package zuul.io.userInterfaces;

import zuul.Game;
import zuul.GameInterface;

import java.util.Scanner;

public class CommandLineInterface implements UserInterface {

    private Scanner reader = null;

    public static void main(String[] args) {
        GameInterface.set(new CommandLineInterface());

        Game.getInstance().play();
    }

    @Override
    public void update(String event) {
        //Do nothing, we don't want to change the standard behaviour.
    }

    @Override
    public void print(String str) {
        System.out.print(str);
    }

    @Override
    public void printNextln() {
        System.out.println();
    }

    @Override
    public String getNextLine() {
        if (reader == null) {
            reader = new Scanner(System.in);
        }
        return reader.nextLine();
    }
}
