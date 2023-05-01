import java.util.Map;

public class Part {
    int id;
    String name;
    String description;
    Map<Part, Integer> subcomponents;

    public Part(String name, String description, Map<Part, Integer> subcomponents) {
        id = 0;
        this.name = name;
        this.description = description;
        this.subcomponents = subcomponents;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Map<Part, Integer> getSubcomponents() {
        return subcomponents;
    }
}
