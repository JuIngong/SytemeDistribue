package fr.unice.miage.m1.tp1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class created on 07/10/2016
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
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                Pattern pattern = Pattern.compile("(Hello I'm : )");
                String name = in.readLine();
                Matcher matcher = pattern.matcher(name);
                if (matcher.find()) {
                    out.println("Hello " + name.substring(matcher.end()));
                    out.flush();
                }
                System.out.println("Connect");
                boolean stayOn = true;
                int line = 0;
                while (stayOn) {
                    String message_distant = in.readLine();
                    if (message_distant == null || message_distant.equals("exit")) {
                        stayOn = false;
                    } else {
                        System.out.println(++line + " : " + message_distant);
                        out.println(message_distant.hashCode());
                        out.flush();
                    }
                }
                System.out.println("Disconnect");
                socket.close();
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
