package ge.edu.btu.client;

import ge.edu.btu.common.Command;
import ge.edu.btu.common.Partie;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        Socket socket = new Socket("localhost", 8081);

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        while (true) {
            System.out.println("1. პარტიების ნახვა");
            System.out.println("2. ხმის მიცემა");
            System.out.println("3. შედეგების ნახვა");
            System.out.println("0. გასვლა");

            String option = scanner.nextLine();
            if (option.equals("0")) {
                out.writeObject(Command.EXIT);
                out.close();
                in.close();
                socket.close();
                break;
            }
            Map<Integer, Long> result;
            switch (option) {
                case "1" :
                    out.writeObject(Command.GET_ALL_PARTIES);
                    List<Partie> parties = (List<Partie>) in.readObject();
                    for (Partie partie : parties) {
                        System.out.println(partie);
                    }
                    break;
                case "2" :
                    System.out.println("პარტიის ნომერი:");
                    int number = Integer.parseInt(scanner.nextLine());

                    out.writeObject(Command.VOTE);
                    out.writeObject(number);
                    out.flush();
                    out.reset();
                    break;
                case "3" :
                    out.writeObject(Command.GET_RESULTS);
                    result = (Map<Integer, Long>) in.readObject();
                    for (int n : result.keySet()) {
                        System.out.println(n + " ->  " + result.get(n));
                    }
                    out.flush();
                    out.reset();
                    break;
                default:
                    System.out.println("არასწორია ოპცია");
            }
        }
    }
}
