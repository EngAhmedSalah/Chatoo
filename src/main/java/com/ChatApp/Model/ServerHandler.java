package com.ChatApp.Model;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerHandler implements Runnable
{
    Socket socket;
    DataInputStream in ;

    public ServerHandler(Socket socket) throws IOException
    {
        this.socket = socket;
        this.in = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void run()
    {
        String serverResponse;
        try
        {
            while (true)
            {
                serverResponse = in.readUTF();
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
