package serverPackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            // La première étape : Création de la ServerSocket sur un port (ex: 1234)
            int port = 1234;
            ServerSocket serverSocket = new ServerSocket(port);

            System.out.println("Je suis un serveur en attente la connexion d'un client ");

            // Attente et acceptation de la connexion d'un client
            Socket socket = serverSocket.accept();

            // La deuxième étape : Un client est connecté
            System.out.println("un client est connecté");

            // La dernière étape : Fermer les sockets
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}