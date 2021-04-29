package com.ChatApp.DAO;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public interface DAO
{
    public File getUsers() throws URISyntaxException;
    public void saveUser(String line) throws IOException, URISyntaxException;
}
