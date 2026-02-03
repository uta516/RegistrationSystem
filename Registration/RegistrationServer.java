

import java.io.*;
import java.net.*;

public class RegistrationServer extends Thread {
    private Socket socket;
    public RegistrationServer(Socket s) { this.socket = s; }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            
            RegistrationParser parser = new RegistrationParser();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals(RegistrationConfiguration.QUIT)) break;
                String response = parser.parse(line);
                writer.write(response);
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
}