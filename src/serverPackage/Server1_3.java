package serverPackage;

import java.io.*;   // Importation des classes pour la gestion des flux d'entrée et de sortie
import java.net.*;  // Importation des classes réseaux (ServerSocket, Socket, InetSocketAddress)

public class Server1_3 {
    public static void main(String[] args) {
        try {
            System.out.println("=== DÉMARRAGE DU SERVEUR ===");
            int port = 1234; // Définition du numéro de port sur lequel le serveur va écouter les requêtes

            // Étape 1 : Création d'un objet ServerSocket vide (sans port assigné pour l'instant)
            ServerSocket serverSocket = new ServerSocket();

            // Étape 2 : Association explicite (bind) du serveur avec l'adresse IP locale et le port défini
            // Utilisation de l'adresse IP "127.0.0.1" (localhost) pour les tests sur la même machine
            String ipLocale = "127.0.0.1";
            serverSocket.bind(new InetSocketAddress(ipLocale, port));

            System.out.println("Le serveur écoute sur l'adresse " + ipLocale + " et le port " + port + "...");

            // Étape 3 : Le serveur se met en attente (bloquant) qu'un client distant se connecte
            Socket socket = serverSocket.accept();
            System.out.println("Client connecté avec succès depuis l'adresse : " + socket.getRemoteSocketAddress());

            // Étape 4 : Préparation des flux de communication (lecture des données du client et écriture des réponses)
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            // Étape 5 : Réception séquentielle des données envoyées par le client (choix du menu et les deux nombres)
            int choix = Integer.parseInt(br.readLine()); // Réception du choix de l'opération (1, 2, 3 ou 4)
            double a = Double.parseDouble(br.readLine());   // Réception du premier nombre (a)
            double b = Double.parseDouble(br.readLine());   // Réception du deuxième nombre (b)

            double resultat = 0;       // Variable pour stocker le résultat du calcul mathématique
            boolean erreur = false;    // Indicateur pour détecter s'il y a une erreur (ex: division par zéro)
            String msgErreur = "";     // Message d'erreur textuel à renvoyer en cas de problème

            // Étape 6 : Traitement de l'opération mathématique selon le choix reçu du client
            switch (choix) {
                case 1: // Option 1 : Addition
                    resultat = a + b;
                    break;
                case 2: // Option 2 : Soustraction
                    resultat = a - b;
                    break;
                case 3: // Option 3 : Multiplication
                    resultat = a * b;
                    break;
                case 4: // Option 4 : Division
                    if (b != 0) {
                        resultat = a / b; // Division normale si le dénominateur n'est pas nul
                    } else {
                        erreur = true;
                        msgErreur = "Erreur : Division par zéro impossible !";
                    }
                    break;
                default: // Si le choix ne correspond à aucune option valide du menu
                    erreur = true;
                    msgErreur = "Erreur : Choix d'opération invalide !";
                    break;
            }

            // Étape 7 : Envoi de la réponse finale (soit le résultat calculé, soit le message d'erreur) au client
            if (erreur) {
                pw.println(msgErreur);
            } else {
                pw.println("Le résultat est : " + resultat);
            }

            // Étape 8 : Fermeture propre de la socket client et du serveur pour libérer les ressources réseau
            socket.close();
            serverSocket.close();
            System.out.println("Serveur arrêté proprement.");

        } catch (Exception e) {
            // Capture et affichage des erreurs éventuelles (exceptions réseau ou de conversion)
            e.printStackTrace();
        }
    }
}