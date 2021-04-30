package com.ChatApp.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerConnection implements Runnable
{
    Socket socket;
    BufferedReader in ;

    public ServerConnection(Socket socket) throws IOException
    {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run()
    {
        String serverResponse;
        try
        {
            while (true)
            {
                serverResponse = in.readLine();
                if(serverResponse == null) break;
                System.out.println(serverResponse);

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
