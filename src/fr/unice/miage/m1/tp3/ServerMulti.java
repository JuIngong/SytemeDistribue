package fr.unice.miage.m1.tp3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author JuIngong
 */
public class ServerMulti {

    private ServerSocket serverSocket;

    public ServerMulti(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }




    public void launch() {
        boolean waitClient = true;
        try {
            System.out.println("Server launch " + serverSocket.getLocalPort());
            while (waitClient) {
                Socket socket = serverSocket.accept();
                new Thread(new ServiceClient(socket)).start();
            }
            System.out.println("Server close");
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            int port = Integer.parseInt(args[0]);
            ServerMulti serverJouet = new ServerMulti(port);
            serverJouet.launch();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
