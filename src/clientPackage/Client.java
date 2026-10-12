package clientPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            System.out.println("Connexion au serveur en cours...");
            // Connexion au serveur local sur le port 1234
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Connecté au serveur avec succès !");

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            // 5.a) Boucle pour saisir plusieurs entiers successivement
            while (true) {
                // a) Demander à l'utilisateur de taper un entier x au clavier
                System.out.print("Entrez un entier (ou 0 pour quitter) : ");
                int x = scanner.nextInt();

                // b) Le client envoie cet entier au serveur (gère positifs et négatifs)
                dos.writeInt(x);
                dos.flush();

                // 5.c) Si l'utilisateur saisit 0, on arrête la communication
                if (x == 0) {
                    System.out.println("Arrêt de la communication demandé par l'utilisateur.");
                    break;
                }

                // f) Le client reçoit le résultat du calcul effectué par le serveur
                int resultat = dis.readInt();
                System.out.println("--> Résultat reçu du serveur (x * 5) = " + resultat);
            }

            // 5.d) Fermeture propre des ressources
            scanner.close();
            socket.close();
            System.out.println("Connexion fermée proprement.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}