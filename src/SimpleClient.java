
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Envoi et réception des messages
            String userInput;
            String serverResponse;
            while (true) {
                // Envoi des messages au serveur
                System.out.print("Envoyer un message au serveur: ");
                userInput = reader.readLine();
                writer.println(userInput);

                // Vérifier si l'utilisateur veut quitter
                if ("exit".equalsIgnoreCase(userInput.trim())) {
                    break;
                }

                // Lecture de la réponse du serveur
                serverResponse = serverReader.readLine();
                System.out.println("Réponse du serveur: " + serverResponse);
            }

            // Fermer les flux et la socket lorsque l'utilisateur quitte
            reader.close();
            serverReader.close();
            writer.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
