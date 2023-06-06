package Client;

import Interface.PartInterface;
import Interface.PartRepositoryInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.PatternSyntaxException;

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
                        if(command.split(" ").length < 3) {
                            System.out.println("Invalid arguments");
                            break;
                        }
                        String repositoryName = command.split(" ")[1];
                        try {
                            int port = Integer.parseInt(command.split(" ")[2]);
                            try {
                                Registry registry = LocateRegistry.getRegistry(port);
                                this.currentPartRepository = (PartRepositoryInterface) registry.lookup(repositoryName);
                                System.out.println("Bound to: " + repositoryName);
                            } catch (NotBoundException e) {
                                System.out.println("Invalid repository name or port");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid arguments");
                            break;
                        }
                        break;
                    case LISTP:
                        if(currentPartRepository == null){
                            System.out.println("No bound repository");
                            break;
                        }
                        System.out.println(currentPartRepository.getName());
                        System.out.println("Total parts: " + currentPartRepository.getSize());
                        currentPartRepository.getAllParts().forEach((p) -> {
                            try {
                                System.out.println(p.getId() + " " + p.getName() + " - " + p.getDescription());
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                        });
                        break;
                    case GETP:
                        try {
                            int id = Integer.parseInt(command.split(" ")[1]);
                            if(currentPartRepository.getSize() < id){
                                System.out.println("Invalid id");
                                break;
                            }
                            currentPart = currentPartRepository.getById(id);
                            System.out.println("Current part: " + id);
                        } catch (PatternSyntaxException e) {
                            System.out.println("Invalid arguments");
                            break;
                        }
                        break;
                    case SHOWP:
                        if(currentPart == null){
                            System.out.println("No bound part");
                            break;
                        }
                        System.out.println(currentPart.getId() + " " + currentPart.getName() + " - " + currentPart.getDescription());
                        System.out.println("isPrimitive: " + currentPart.isPrimitive());
                        System.out.println("repository: " + currentPart.getRepositoryName());
                        break;
                    case CLEAR_LIST:
                        currentSubParts.clear();
                        System.out.println("Subpart list cleared");
                        break;
                    case ADD_SUBPART:
                        try {
                            int subPartQnt = Integer.parseInt(command.split(" ")[1]);
                            currentSubParts.put(currentPart, subPartQnt);
                            System.out.println("Added " + subPartQnt + " parts " + currentPart.getId());
                        } catch (PatternSyntaxException e) {
                            System.out.println("Invalid arguments");
                            break;
                        }
                        break;
                    case ADDP:
                        if(command.split(" ").length < 3) {
                            System.out.println("Invalid arguments");
                            break;
                        }
                        String name = command.split(" ")[1];
                        String description = command.split(" ")[2];
                        int id = currentPartRepository.addPart(name, description, currentSubParts);
                        System.out.println("Added part with id: " + id);
                        break;
                    case QUIT:
                        isRunning = false;
                        break;
                    default:
                        printHelp();
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    static private void printHelp(){
        System.out.println("Available commands: ");
        System.out.println(BIND + " [repositoryName] [port]");
        System.out.println(LISTP);
        System.out.println(GETP + " [partId]");
        System.out.println(SHOWP);
        System.out.println(CLEAR_LIST);
        System.out.println(ADD_SUBPART + " [subPartQuantity]");
        System.out.println(ADDP + " [partName] [partDescription]");
        System.out.println(QUIT);
    }
}
