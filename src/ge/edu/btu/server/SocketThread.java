package ge.edu.btu.server;

import ge.edu.btu.common.Command;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketThread extends Thread {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private ElectionService electionService;

    public SocketThread(Socket socket, ElectionService electionService) throws Exception {
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
        this.socket = socket;
        this.electionService = electionService;
    }

    @Override
    public void run() {
        boolean finished = false;
        while (!finished) {
            try {
                Command command = (Command) in.readObject();
                switch (command) {
                    case GET_ALL_PARTIES:
                        out.writeObject(electionService.getAllParties());
                        out.flush();
                        out.reset();
                        break;
                    case VOTE:
                        Integer number = (Integer) in.readObject();
                        electionService.vote(number);
                        break;
                    case GET_RESULTS:
                        out.writeObject(electionService.getResult());
                        out.flush();
                        out.reset();
                        break;
                    case EXIT:
                        out.close();
                        in.close();
                        socket.close();
                        finished = true;
                        break;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
