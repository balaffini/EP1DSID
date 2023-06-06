package Server;

import Interface.PartInterface;
import Interface.PartRepositoryInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PartRepository extends UnicastRemoteObject implements PartRepositoryInterface {
    String name;
    List<PartInterface> partCollection;

    public PartRepository(String name) throws RemoteException {
        super();
        this.name = name;
        partCollection = new ArrayList<>();
    }

    @Override
    public PartInterface getById(int id) throws RemoteException {
        return partCollection.get((id-1));
    }

    @Override
    public boolean addParts(List<PartInterface> parts) throws RemoteException {
        return partCollection.addAll(parts);
    }

    @Override
    public int addPart(String name, String description, Map<PartInterface, Integer> subcomponents) throws RemoteException {
        int id = partCollection.size() + 1;
        PartInterface part = new Part(id, name, description, this.name, subcomponents);
        partCollection.add(part);
        return id;
    }

    @Override
    public List<PartInterface> getAllParts() throws RemoteException {
        return partCollection;
    }

    @Override
    public int getSize() throws RemoteException {
        return partCollection.size();
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }
}
