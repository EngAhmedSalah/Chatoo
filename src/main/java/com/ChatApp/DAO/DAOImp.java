package com.ChatApp.DAO;

import com.ChatApp.Controller.Security;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;

public class DAOImp implements DAO
{
    private static String filePath = "database/loginData/loginData.txt";
    public DAOImp()
    {
    }

    @Override
    public File getUsers() throws URISyntaxException
    {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        File file = new File("src/main/resources/database/loginData/loginData.txt");
        return file;
    }

    @Override
    public void saveUser(String line) throws IOException, URISyntaxException
    {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        File file = new File("src/main/resources/database/loginData/loginData.txt");
        FileWriter writer = new FileWriter(file , true);
        writer.write(line + "\n");
        writer.close();
    }
}