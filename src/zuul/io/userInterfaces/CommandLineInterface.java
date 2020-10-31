package zuul.io.userInterfaces;

import zuul.Game;
import zuul.GameInterface;

public class CommandLineInterface implements UserInterface {

    public static void main(String[] args) {
        GameInterface.set(new CommandLineInterface());

        Game.getInstance().play();
    }

    @Override
    public void update() {

    }
}
