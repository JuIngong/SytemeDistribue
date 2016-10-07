package fr.unice.miage.m1.tp1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class created on 07/10/2016
 *
 * @author JuIngong
 */
public class ServerJouet {

    private ServerSocket serverSocket;

    public ServerJouet() throws IOException {
        this.serverSocket = new ServerSocket(12000);
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void launch(){
        Socket socket;
        BufferedReader in;
        boolean stayOn = true;
        int line = 0;
        try {
            socket = serverSocket.accept();
            System.out.println("Connect");
            while (stayOn) {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message_distant = in.readLine();
                if (message_distant == null || message_distant.equals("exit")) {
                    stayOn = false;
                } else {
                    System.out.println(++line + " : " + message_distant);
                }

            }
            System.out.println("Disconnect");
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            ServerJouet serverJouet = new ServerJouet();
            serverJouet.launch();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
