package com.hai.ijavase.inetwork.socket.encrypt;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyClient {

    private final static Logger logger = Logger.getLogger(MyClient.class.getName());
    private int count;

    public MyClient() {
    }

    public MyClient(int count) {
        this.count = count;
    }

    public static void main(String[] args) throws Exception {
        new MyClient(10).run();
    }

    public void run() {
        for (int i = 0; i < count; i++) {
            Socket socket = null;
            ObjectOutputStream os = null;
            ObjectInputStream is = null;

            try {
                SocketFactory factory = SSLSocketFactory.getDefault();
                socket = factory.createSocket("localhost", MyServer.SOCKET_PORT);

                os = new ObjectOutputStream(socket.getOutputStream());
                User user = new User("user_" + i, "password_" + i);
                os.writeObject(user);
                os.flush();

                is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                Object obj = is.readObject();
                if (obj != null) {
                    user = (User) obj;
                    System.out.println("user: " + user.getName() + "/" + user.getPassword());
                }
            } catch (IOException ex) {
                logger.log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                MyServer.close(socket, is, os);
            }

        }
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}