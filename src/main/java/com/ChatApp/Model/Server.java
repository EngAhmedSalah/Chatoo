package com.ChatApp.Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server
{
    private static int port = 9507;
    public static ArrayList<ClientServerHandler> clients = new ArrayList<>();
    public static ExecutorService pool  = Executors.newFixedThreadPool(100);
    public static void main(String[] args) throws IOException
    {
        ServerSocket server = new ServerSocket(port);
        ClientServerHandler clientThread;
        while (true)
        {
            System.out.println("Waiting for connection--------------------");
            Socket socket = server.accept();
            System.out.println("client is connected-----------------------");
            clientThread = new ClientServerHandler(socket);
            clients.add(clientThread);
            pool.execute(clientThread);
        }
    }
}
