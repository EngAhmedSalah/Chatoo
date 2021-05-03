package com.ChatApp.DAO;

import com.ChatApp.Controller.Login;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DAOImp implements DAO
{
    private static String filePath = "database/loginData/loginData.txt";
    public DAOImp()
    {
    }

    /**
     *
     * @return
     * @description implements the method that get all the users and their passwords
     */
    @Override
    public File getUsers()
    {
        return new File("src/main/resources/database/loginData/loginData.txt");
    }

    /**
     *
     * @param line
     * @throws IOException
     * @description implements the method that save a new passwords into the file
     */
    @Override
    public void saveUser(String line) throws IOException
    {
        File file = new File("src/main/resources/database/loginData/loginData.txt");
        FileWriter writer = new FileWriter(file , true);
        writer.write(line + "\n");
        writer.close();
    }

    /**
     *
     * @param currentUserChat
     * @throws IOException
     * @description get a dump file that contains the whole chat history
     */
    public void gatDumpFile(String currentUserChat) throws IOException
    {
        //define the filename
        String outFileName = "src/main/resources/database/dumbs/"+ Login.username + "_dumpfile.txt";

        //write the chat content to the file
        Files.write(Paths.get(outFileName), currentUserChat.getBytes());
    }

    /**
     *
     * @param currentUserChat
     * @throws IOException
     * @description get a dump file that contains the occurrence of the words in chat
     */
    public void gatDumpfileStatistics(String currentUserChat) throws IOException
    {
        //define the filename
        String outFileName = "src/main/resources/database/dumbs/" + Login.username + "_dumpfileStatistics.txt";

        //java 8 stream to filter the text
        List<String> list = Stream.of(currentUserChat)
                .map(w -> w.split("\\s+")).flatMap(Arrays::stream)
                .filter(x -> !x.contains(":"))
                .collect(Collectors.toList());

        //counting each word using map<key , value>
        Map<String, Integer> wordCounter = list.stream()
                .collect(Collectors.toMap(String::toLowerCase, w -> 1, Integer::sum));

        //write the count results to the file
        Files.write(Paths.get(outFileName), wordCounter.toString().getBytes());
    }
}