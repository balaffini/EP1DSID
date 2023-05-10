import java.rmi.Naming;

public class Client {
    public static void main(String[] args) {
        try {
            Part part = (Part) Naming.lookup("//127.0.0.1:1020/PartService");
            System.out.println("Nome : " + part.getName());
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
