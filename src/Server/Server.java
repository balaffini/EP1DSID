package Server;

import Interface.PartRepositoryInterface;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    private static Registry registry;
    public Server(String name, String port) {
        try {
            PartRepositoryInterface partRepository = new PartRepository(name);
            registry = LocateRegistry.createRegistry(Integer.parseInt(port));
            registry.rebind(name, partRepository);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Server(args[0], args[1]);
    }
}
