package com.ChatApp.Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server
{
    public static Server serverInstance;
    private static int port = 9507;
    private int idx = 1;
    public static ArrayList<ClientServerHandler> clients;
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
            ClientServerHandler clientThread = new ClientServerHandler(server.accept() , getRandName());
            clients.add(clientThread);
            pool.execute(clientThread);
            System.out.println("client is connected-----------------------");
        }
    }

    public static void broadcast(ClientServerHandler from , String message)
    {
        for(ClientServerHandler clientServerHandler : clients)
        {
                if(clientServerHandler.username.equals(from.username))
                    clientServerHandler.out.println(message);
                else
                    clientServerHandler.out.println(from.username + "Says: " + message);
        }
    }
    public static void main(String[] args) throws IOException
    {
            System.out.println("Waiting for connection--------------------");
            getInstance().getConnection();
    }

//    public void setName(String name)
//    {
//        this.name = name;
//    }
    public String getRandName()
    {
        String s[] = {"Ahmed" , "Salah"};
        return s[idx--];
    }
}
