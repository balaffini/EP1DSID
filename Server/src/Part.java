import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface Part  extends Remote {
    int getId() throws RemoteException;
    String getName() throws RemoteException;
    String getDescription() throws RemoteException;
    Map<PartBean, Integer> getSubcomponents() throws RemoteException;
}
