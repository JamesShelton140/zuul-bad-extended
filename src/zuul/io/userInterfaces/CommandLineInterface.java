package zuul.io.userInterfaces;

import zuul.Game;
import zuul.GameInterface;

public class CommandLineInterface implements UserInterface {


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
    public void nextln() {
        System.out.println();
    }
}
