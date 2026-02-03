

import java.io.*;
import java.net.*;

public class RegistrationSocket implements RegistrationConfiguration {
    public RegistrationSocket(String host, String message) {
        try (Socket s = new Socket(host, SERVER_PORT);
             BufferedWriter w = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()))) {
            w.write(message);
            w.newLine();
            w.flush();
        } catch (IOException e) { e.printStackTrace(); }
    }
}