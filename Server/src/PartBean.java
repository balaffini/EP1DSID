import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

public class PartBean extends UnicastRemoteObject implements Part {
    int id;
    String name;
    String description;
    Map<PartBean, Integer> subcomponents;

    public PartBean() throws RemoteException {
        super();
    }

    public PartBean(String name, String description, Map<PartBean, Integer> subcomponents) throws RemoteException {
        id = 0;
        this.name = name;
        this.description = description;
        this.subcomponents = subcomponents;
    }

    String getNameAndDescription() {
        return name + ": " + description;
    }

    String getRepositoryName(Part part) {
        return ""; // ??
    }

    Boolean isPrimitive() {
        return subcomponents.isEmpty();
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

    public Map<PartBean, Integer> getSubcomponents() {
        return subcomponents;
    }
}
