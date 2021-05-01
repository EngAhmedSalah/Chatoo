package com.ChatApp.Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server
{
    int idx = 1;
    public static Server serverInstance;
    private static int port = 9507;
    public static ArrayList<ClientHandler> clients;
    public static ExecutorService pool  = Executors.newFixedThreadPool(100);
    ServerSocket server;
    String name;

    private Server() throws IOException
    {
        clients = new ArrayList<>();
        this.server = new ServerSocket(port);
    }

    public static Server getInstance() throws IOException
    {
        return serverInstance == null ? new Server() : serverInstance;
    }

    public void getConnection() throws IOException
    {
        while (true)
        {
            ClientHandler clientThread = new ClientHandler(server.accept() ,getRand());
            clients.add(clientThread);
            pool.execute(clientThread);
            System.out.println("client is connected-----------------------");
        }
    }

    public static void broadcast(ClientHandler from , String message) throws IOException
    {
        if(message != null)
             System.out.println(message);
        for(ClientHandler clientServerHandler : clients)
        {
                if(clientServerHandler.username.equals(from.username))
                    clientServerHandler.out.writeUTF(message);
                else
                    clientServerHandler.out.writeUTF(from.username + "Says: " + message);
        }
    }
    public static void main(String[] args) throws IOException
    {
            System.out.println("Waiting for connection--------------------");
            getInstance().getConnection();
    }

    public String getRand()
    {
        String s[] = {"ahmed" , "salah"};
        return s[idx--];
    }
}
