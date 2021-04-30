package com.ChatApp.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientServerHandler implements Runnable
{
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;

    public ClientServerHandler(Socket socket) throws IOException
    {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream() , true);
    }

    @Override
    public void run()
    {
        String message = "";
        try
        {
            while (true)
            {
                message = in.readLine();
                broadcast(message);
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    private void broadcast(String message)
    {
        for(ClientServerHandler client : Server.clients)
        {
            if(message != null)
                client.out.println(message);
        }
    }
}
