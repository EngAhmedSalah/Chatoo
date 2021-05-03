package com.ChatApp.Controller;

import com.ChatApp.DAO.DAO;
import com.ChatApp.DAO.DAOImp;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Security
{
    DAO dao = new DAOImp();
    private static Security instance = null;


    private Security()
    {
    }

    public static Security getSecurityInstance() throws URISyntaxException
    {
        if(instance == null)
            return new Security();
        else
            return instance;
    }


    /**
     *
     * @param username
     * @param password
     * @return boolean value identify if the username and password are exist and valid
     */
    public boolean auth(String username, String password)
    {
        boolean found = false;
        try
        {
            Scanner scanner = new Scanner(dao.getUsers());
            String tempUser = "";
            String tempPassword = "";
            while (scanner.hasNext())
            {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                tempUser = data[0];
                tempPassword = data[1];
                if(tempUser.equals(username) && tempPassword.equals(hashing(password + username)))
                    found = true;
            }
            scanner.close();
        }
        catch (FileNotFoundException | URISyntaxException e)
        {
            e.printStackTrace();
        }
        return found;
    }

    /**
     *
     * @param password
     * @param confirmPassword
     * @return string value that is the hashed value of the password in case of the password is equal the confirm password value
     */
    public static String confirmPassword(String password , String confirmPassword)
    {
        if(password.equals(confirmPassword))
            return hashing(password);
        return null;
    }

    /**
     *
     * @param passwordToHash
     * @return the hashed value of the password
     * @description hash the password using md5 hashing technique
     */
    public static String hashing(String passwordToHash)
    {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            //Add password bytes to digest
            md.update(passwordToHash.getBytes());

            //Get the hash's bytes
            byte[] bytes = md.digest();

            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();

            for (byte aByte : bytes)
            {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
