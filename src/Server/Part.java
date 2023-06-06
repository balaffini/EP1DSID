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
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.repositoryName = repositoryName;
        this.subcomponents = subcomponents;
    }

    public Part(int id, String name, String description, String repositoryName) throws RemoteException {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.repositoryName = repositoryName;
        this.subcomponents = new ConcurrentHashMap<>();
    }

    @Override
    public int getId() throws RemoteException {
        return id;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public String getDescription() throws RemoteException {
        return description;
    }

    @Override
    public String getRepositoryName() throws RemoteException {
        return repositoryName;
    }

    @Override
    public Map<PartInterface, Integer> getSubcomponents() throws RemoteException {
        return subcomponents;
    }

    @Override
    public Boolean isPrimitive() throws RemoteException {
        return subcomponents.isEmpty();
    }

    @Override
    public String toString() {
        return this.id + " " + this.name + " - " + this.description;
    }

    @Override
    public int getTotalSubcomponents() throws RemoteException {
        return subcomponents.size();
    }
}
