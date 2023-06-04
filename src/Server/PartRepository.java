package Server;

import Interface.PartInterface;
import Interface.PartRepositoryInterface;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public class PartRepository implements PartRepositoryInterface {
    String name;
    List<PartInterface> partCollection;

    public PartInterface getById(int id) {
        return partCollection.get(id);
    }

    public boolean addParts(List<PartInterface> parts) {
        return partCollection.addAll(parts);
    }

    public boolean addPart(String name, String description, Map<PartInterface, Integer> subcomponents) throws RemoteException {
        int id = partCollection.size() + 1;
        Part part = new Part(id, name, this.name, description, subcomponents);
        return partCollection.add(part);
    }

    public List<PartInterface> getAllParts() {
        return partCollection;
    }

    public String getNameAndSize() {
        return name + " " + partCollection.size();
    }
}
