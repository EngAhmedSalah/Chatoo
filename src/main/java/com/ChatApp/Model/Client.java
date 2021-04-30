package com.ChatApp.Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args) throws IOException
    {
        int port = 9507;
        Socket socket = new Socket("localhost" , port);
        PrintWriter out = new PrintWriter(socket.getOutputStream() , true);
        Scanner scanner = new Scanner(System.in);
        String message;

        ServerConnection serverConnection = new ServerConnection(socket);

        new Thread(serverConnection).start();
        while (true)
        {
            message = scanner.nextLine();
            if(message.equals("Bye Bye")) break;
            out.println(message);
        }
    }
}
