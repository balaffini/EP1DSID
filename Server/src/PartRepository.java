import java.util.ArrayList;
import java.util.List;

public class PartRepository {
    String name;
    List<Part> partCollection;

    Part getById(int id) {
        return partCollection.get(id);
    }

    boolean addParts(List<Part> parts) {
        return partCollection.addAll(parts);
    }

    List<Part> getAllParts() {
        return partCollection;
    }

    String getNameAndSize() {
        return name + " " + partCollection.size();
    }
}
