package com.ChatApp.DAO;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public interface DAO
{
    /**
     *
     * @return file that contains all users and passwords
     * @throws URISyntaxException
     */
    public File getUsers() throws URISyntaxException;


    /**
     *
     * @param line
     * @throws IOException
     * @throws URISyntaxException
     * @description save a new user in a database file
     */
    public void saveUser(String line) throws IOException, URISyntaxException;

    /**
     *
     * @param currentUserChat
     * @throws IOException
     * @description get a dump file that contains the occurrence of the words in chat
     */
    public void gatDumpfileStatistics(String currentUserChat) throws IOException;


    /**
     *
     * @param currentUserChat
     * @throws IOException
     * @description get a dump file that contains the whole chat history
     */
    public void gatDumpFile(String currentUserChat) throws IOException;
}
