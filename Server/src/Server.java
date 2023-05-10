import java.rmi.Naming;

public class Server {
    Server() {
        try {
            Part part = new PartBean();
            Naming.rebind("RMI://127.0.0.1:1020/CalculatorService", part);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Server();
    }
}
