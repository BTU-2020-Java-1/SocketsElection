package ge.edu.btu.server;

import ge.edu.btu.common.Partie;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class Server {

    public static void main(String[] args) throws Exception {
        ElectionService electionService = new ElectionServiceImpl();
        electionService.initParties(getTestParties());

        ServerSocket serverSocket = new ServerSocket(8081);

        while (true) {
            Socket socket = serverSocket.accept();
            SocketThread socketThread = new SocketThread(socket, electionService);
            socketThread.start();
        }
    }

    private static List<Partie> getTestParties() {
        return Arrays.asList(new Partie(1, "Test 1"),
                new Partie(2, "Test 2"),
                new Partie(3, "Test 3"));
    }
}
