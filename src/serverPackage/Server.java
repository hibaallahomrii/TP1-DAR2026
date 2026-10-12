package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            System.out.println("Démarrage du serveur...");
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Serveur en attente de connexion sur le port 1234...");

            // Blocage jusqu'à ce qu'un client se connecte
            Socket socket = serverSocket.accept();
            System.out.println("Client connecté avec succès !");

            // Création des flux pour lire et écrire des types primitifs (entiers)
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            // Boucle pour recevoir plusieurs requêtes successives
            while (true) {
                // c) Le serveur reçoit l'entier envoyé par le client
                int x = dis.readInt();

                // 5.c) Condition d'arrêt : si le client envoie 0, on quitte la boucle
                if (x == 0) {
                    System.out.println("Signal d'arrêt (0) reçu du client. Fermeture de la connexion.");
                    break;
                }

                System.out.println("Nombre reçu du client : " + x);

                // d) Le serveur calcule le produit x * 5
                int resultat = x * 5;

                // e) Le serveur envoie le résultat au client
                dos.writeInt(resultat);
                dos.flush(); // Force l'envoi immédiat des données dans le réseau

                System.out.println("Résultat envoyé au client : " + resultat);
            }

            // 5.d) Fermeture propre des flux et des sockets
            socket.close();
            serverSocket.close();
            System.out.println("Serveur arrêté proprement.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}