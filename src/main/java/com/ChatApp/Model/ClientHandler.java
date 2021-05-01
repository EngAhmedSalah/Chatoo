package com.ChatApp.Model;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread
{
    DataOutputStream out;
    DataInputStream in ;
    public Socket socket;
    String username;
    public ClientHandler(Socket socket, String username) throws IOException
    {
        this.socket = socket;
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
        this.username = username;
    }

    @Override
    public void run()
    {
        String message = "";
        try
        {
            while (true)
            {
                message = in.readUTF();
                if(message.equals("Bye Bye"))
                {
                    this.socket.close();
                    this.out.close();
                    this.in.close();
                }
                else
                     Server.broadcast(this , message);
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}
