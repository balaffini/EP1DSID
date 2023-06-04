package Client;

import Interface.PartInterface;
import Interface.PartRepositoryInterface;
import Server.Part;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Client {
    public static void main(String[] args) {
        Client client = new Client();
        client.init();
    }
    static private final String HELP = "help";
    static private final String BIND = "bind";
    static private final String LISTP = "listp";
    static private final String GETP = "getp";
    static private final String SHOWP = "showp";
    static private final String CLEAR_LIST = "clearlist";
    static private final String ADD_SUBPART = "addsubpart";
    static private final String ADDP = "addp";
    static private final String QUIT = "quit";

    PartRepositoryInterface currentPartRepository;
    PartInterface currentPart;
    Map<PartInterface, Integer> currentSubParts;

    public Client() {
        currentPartRepository = null;
        currentPart = null;
        currentSubParts = new ConcurrentHashMap<>();
    }

    public void init() {
        try {
            printHelp();
            boolean isRunning = true;
            while(isRunning){
                Scanner scan = new Scanner(System.in);
                String command = scan.nextLine();
                switch (command.split(" ")[0]){
                    case HELP:
                        printHelp();
                        break;
                    case BIND:
                        String repositoryName = command.split(" ")[1];
                        Registry registry = LocateRegistry.getRegistry();
                        this.currentPartRepository = (PartRepositoryInterface) registry.lookup(repositoryName);
                        break;
                    case LISTP:
                        if(currentPartRepository == null){
                            System.out.println("No bound repository");
                            break;
                        }
                        currentPartRepository.getAllParts().forEach(System.out::println);
                        break;
                    case GETP:
                        int id = Integer.parseInt(command.split(" ")[1]);
                        currentPart = currentPartRepository.getById(id);
                        break;
                    case SHOWP:
                        System.out.println(currentPart);
                        break;
                    case CLEAR_LIST:
                        currentSubParts.clear();
                        break;
                    case ADD_SUBPART:
                        int subPartQnt = Integer.parseInt(command.split(" ")[1]);
                        currentSubParts.put(currentPart, subPartQnt);
                        break;
                    case ADDP:
                        String name = command.split(" ")[1];
                        String description = command.split(" ")[2];
                        currentPartRepository.addPart(name, description, currentSubParts);
                        break;
                    case QUIT:
                        isRunning = false;
                        break;
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    static private void printHelp(){
        System.out.println("Available commands: ");
        System.out.println(BIND + " [repositoryName]");
        System.out.println(LISTP);
        System.out.println(GETP + " [partId]");
        System.out.println(SHOWP);
        System.out.println(CLEAR_LIST);
        System.out.println(ADD_SUBPART + " [subPartQuantity]");
        System.out.println(ADDP + " [partName] [partDescription]");
        System.out.println(QUIT);
    }
}
