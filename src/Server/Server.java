package Server;

import java.rmi.Naming;

public class Server {
    Server() {
        try {
            Part part = new Part();
            Naming.rebind("RMI://127.0.0.1:1020/PartService", part);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Server();
    }
}
