package de.clemens.util;

import java.io.*;
import java.net.Socket;

public class ServerInfoUtil {

    public static int getOnlinePlayers(String domain, Integer port) {
        try {

            Socket socket = new Socket(domain, port);

            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.write(0xFE);

            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            StringBuilder stringBuilder = new StringBuilder();

            int i;
            while ((i = dataInputStream.read()) != -1) {
                if (i > 16 && i != 255 && i != 23 && i != 24)
                    stringBuilder.append((char) i);
            }

            socket.close();

            String string = stringBuilder.toString();
            String[] serverPingData = string.split("§");

            return Integer.parseInt(serverPingData[1]);
        } catch (IOException ioException) {

            ioException.printStackTrace();
        }

        return 0;
    }
}
