package Server;

import Interface.PartInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Part extends UnicastRemoteObject implements PartInterface {
    int id;
    String name;
    String description;
    String repositoryName;
    Map<PartInterface, Integer> subcomponents;

    public Part() throws RemoteException {
        super();
    }

    public Part(int id, String name, String description, String repositoryName, Map<PartInterface, Integer> subcomponents) throws RemoteException {
        this.id = id;
        this.name = name;
        this.description = description;
        this.repositoryName = repositoryName;
        this.subcomponents = subcomponents;
    }

    public Part(int id, String name, String description, String repositoryName) throws RemoteException {
        this.id = id;
        this.name = name;
        this.description = description;
        this.repositoryName = repositoryName;
        this.subcomponents = new ConcurrentHashMap<>();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    @Override
    public Map<PartInterface, Integer> getSubcomponents() {
        return subcomponents;
    }

    String getNameAndDescription() {
        return name + ": " + description;
    }

    Boolean isPrimitive() {
        return subcomponents.isEmpty();
    }
}
