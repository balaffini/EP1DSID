package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface PartInterface extends Remote {
    int getId() throws RemoteException;
    String getName() throws RemoteException;
    String getDescription() throws RemoteException;
    Map<PartInterface, Integer> getSubcomponents() throws RemoteException;
}
