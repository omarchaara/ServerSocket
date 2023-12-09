
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                System.out.println("Message reçu du client " + clientSocket.getInetAddress() + ": " + inputLine);

                // Vous pouvez ajouter ici la logique de traitement des messages reçus

                // Répondre au client
                writer.println("Message reçu par le serveur: " + inputLine);
            }

            // Fermer les flux et la socket lorsque le client se déconnecte
            reader.close();
            writer.close();
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

