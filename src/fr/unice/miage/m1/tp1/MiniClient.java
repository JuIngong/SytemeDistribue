package fr.unice.miage.m1.tp1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Class created on 07/10/2016
 *
 * @author JuIngong
 */
public class MiniClient {
    private String host;
    private int port;
    private String name;

    public MiniClient(String host, int port, String name) {
        this.host = host;
        this.port = port;
        this.name = name;
    }

    public void connect() {
        PrintWriter out;
        try {
            Socket socket = new Socket(host, port);
            out = new PrintWriter(socket.getOutputStream());
            out.println("Hello I'm : " + name);
            out.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message_distant = in.readLine();
            System.out.println(message_distant);
            boolean stayOn = true;

            Scanner sc = new Scanner(System.in);
            while (stayOn) {
                String str = sc.nextLine();
                int hash = str.hashCode();
                out.println(str);
                out.flush();
                message_distant = in.readLine();
                if (str == null || str.equals("exit") || hash == Integer.parseInt(message_distant)) {
                    stayOn = false;
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MiniClient(args[0], Integer.parseInt(args[1]), args[2]).connect();
    }
}
