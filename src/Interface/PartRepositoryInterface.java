package Interface;

import Server.Part;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface PartRepositoryInterface {
    PartInterface getById(int id);

    boolean addParts(List<PartInterface> parts);

    boolean addPart(String name, String description, Map<PartInterface, Integer> subcomponents) throws RemoteException;

    List<PartInterface> getAllParts();

    String getNameAndSize();
}
