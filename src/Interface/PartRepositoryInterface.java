package Interface;

import Server.Part;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface PartRepositoryInterface extends Remote {
    PartInterface getById(int id) throws RemoteException;

    boolean addParts(List<PartInterface> parts) throws RemoteException;

    int addPart(String name, String description, Map<PartInterface, Integer> subcomponents) throws RemoteException;

    List<PartInterface> getAllParts() throws RemoteException;

    int getSize() throws RemoteException;

    String getName() throws RemoteException;
}
