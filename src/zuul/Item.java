package zuul;


import java.util.Objects;

public class Item {
    private String name;
    private int weight;
    private String description;

    public Item(String name, int weight) {
        this.name = name;
        this.weight = weight;
        this.description = "A " + name;
    }

    public Item(String name, int weight, String description) {
        this.name = name;
        this.weight = weight;
        this.description = description;
    }

    /**
     * Getters and Setters
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Equals
     * @param o The object to compare.
     * @return Ture if objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name + '(' + weight + ')';
    }
}
