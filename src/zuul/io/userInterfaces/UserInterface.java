package zuul.io.userInterfaces;

public interface UserInterface {

    public abstract void update(String event);

    abstract void print(String str);

    void nextln();
}
