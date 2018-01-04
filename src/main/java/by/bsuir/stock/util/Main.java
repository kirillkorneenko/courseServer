package by.bsuir.stock.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.Properties;

public class Main {

    public static void main(String[] args)  {

        try {
        Calendar calendar = Calendar.getInstance();

        Properties properties = new Properties();
        InputStream input = new FileInputStream("classes/connection.properties");
        properties.load(input);
        Integer port =Integer.parseInt(properties.getProperty("port"));

        System.out.println("Сервер запущен в " + calendar.getTime()+
                "\n Порт №"+ port+"\n");


            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket userSocket = serverSocket.accept();
                System.out.println(userSocket.getInetAddress().getHostName() + " подключился");
                ServerThread server = new ServerThread(userSocket);
                server.start();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}