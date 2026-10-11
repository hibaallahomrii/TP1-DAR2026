package clientPackage;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            // La première étape : Affichage avant la connexion
            System.out.println("Je suis un client pas encore connecté…");

            // Connexion au serveur (localhost sur le port 1234)
            String host = "127.0.0.1";
            int port = 1234;
            Socket socket = new Socket(host, port);

            // Connexion réussie
            System.out.println("je suis un client connecté");

            // La dernière étape : Fermer le socket
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}