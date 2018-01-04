package by.bsuir.stock.util;

import by.bsuir.stock.bean.Batch;
import by.bsuir.stock.controller.Controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ServerThread extends Thread {
    protected InetAddress address;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public ServerThread(Socket socket) throws IOException {
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
        address = socket.getInetAddress();
    }

    public void run() {

        try {
            Batch batch = (Batch) inputStream.readObject();
            outputStream.writeObject(Controller.execute(batch));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        disconnect();

    }

    public void disconnect() {
        try {
            System.out.println(address.getHostName() + " отключился");
            outputStream.close();
            inputStream.close();
            address = null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.interrupt();
        }
    }
}
