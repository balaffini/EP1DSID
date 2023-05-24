package Server;

import Interface.PartInterface;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public class PartRepository {
    String name;
    List<PartInterface> partCollection;

    PartInterface getById(int id) {
        return partCollection.get(id);
    }

    boolean addParts(List<PartInterface> parts) {
        return partCollection.addAll(parts);
    }

    boolean addPart(String name, String description, Map<PartInterface, Integer> subcomponents) throws RemoteException {
        int id = partCollection.size() + 1;
        Part part = new Part(id, name, this.name, description, subcomponents);
        return partCollection.add(part);
    }

    List<PartInterface> getAllParts() {
        return partCollection;
    }

    String getNameAndSize() {
        return name + " " + partCollection.size();
    }
}
