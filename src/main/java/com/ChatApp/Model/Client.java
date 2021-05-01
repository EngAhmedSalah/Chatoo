package com.ChatApp.Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args) throws IOException
    {
        Socket socket = new Socket("localhost" , 9507);
        PrintWriter out = new PrintWriter(socket.getOutputStream() , true);
        Scanner scanner = new Scanner(System.in);
        String message;

        ServerHandler serverHandler = new ServerHandler(socket);

        new Thread(serverHandler).start();
        while (true)
        {
            message = scanner.nextLine();
            if(message.equals("Bye Bye")) break;
            out.println(message);
        }
    }
}
