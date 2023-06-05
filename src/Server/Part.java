package Server;

import Interface.PartInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Part extends UnicastRemoteObject implements PartInterface {
    private int id;
    private String name;
    private String description;
    private String repositoryName;
    private Map<PartInterface, Integer> subcomponents;

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

    @Override
    public String getRepositoryName() {
        return repositoryName;
    }

    @Override
    public Map<PartInterface, Integer> getSubcomponents() {
        return subcomponents;
    }

    @Override
    public String getNameAndDescription() {
        return name + ": " + description;
    }

    @Override
    public Boolean isPrimitive() {
        return subcomponents.isEmpty();
    }

    @Override
    public String toString(){
        return id + " " + name + " - " + description;
    }
}
