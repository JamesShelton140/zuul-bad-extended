package zuul.io.userInterfaces;

public interface UserInterface {

    void update(String event);

    void print(String str);

    void printNextln();

    String getNextLine();
}
