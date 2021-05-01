package com.ChatApp.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientServerHandler extends Thread
{
    public BufferedReader in;
    public PrintWriter out;
    public Socket socket;
    String username;
    public ClientServerHandler(Socket socket, String username) throws IOException
    {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream() , true);
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
                message = in.readLine();
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
