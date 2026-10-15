package clientPackage;

import java.io.*;         // Importation pour la gestion des flux d'entrée/sortie (BufferedReader, PrintWriter)
import java.net.*;        // Importation pour les sockets et les adresses réseau (Socket, InetSocketAddress)
import java.util.Scanner; // Importation pour lire les saisies de l'utilisateur depuis la console

public class Client1_3 {
    public static void main(String[] args) {
        try {
            System.out.println("=== CLIENT DISTANT ===");

            // Définition de l'adresse IP du serveur (127.0.0.1 pour un test local sur la même machine) et du port
            String ipDuServeur = "127.0.0.1";
            int port = 1234;

            System.out.println("Tentative de connexion au serveur sur " + ipDuServeur + ":" + port + "...");

            // Étape 1 : Création d'une Socket vide et connexion au serveur distant via InetSocketAddress
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ipDuServeur, port));
            System.out.println("Connecté au serveur avec succès !");

            // Initialisation du Scanner pour récupérer les choix et les nombres saisis par l'utilisateur
            Scanner scanner = new Scanner(System.in);

            // Étape 2 : Affichage du menu interactif de la calculatrice pour l'utilisateur
            System.out.println("\n--- MENU CALCULATRICE ---");
            System.out.println("1. Addition (+)");
            System.out.println("2. Soustraction (-)");
            System.out.println("3. Multiplication (*)");
            System.out.println("4. Division (/)");
            System.out.print("Entrez votre choix (1-4) : ");
            int choix = scanner.nextInt(); // Lecture du choix de l'opération

            System.out.print("Entrez le premier nombre (a) : ");
            double a = scanner.nextDouble(); // Lecture du premier nombre

            System.out.print("Entrez le deuxième nombre (b) : ");
            double b = scanner.nextDouble(); // Lecture du deuxième nombre

            // Étape 3 : Envoi du choix et des deux nombres au serveur via un flux d'écriture (PrintWriter)
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            pw.println(choix); // Envoi de l'option choisie
            pw.println(a);     // Envoi du nombre a
            pw.println(b);     // Envoi du nombre b

            // Étape 4 : Réception de la réponse calculée et renvoyée par le serveur
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String reponse = br.readLine(); // Lecture de la ligne de réponse du serveur
            System.out.println("Réponse reçue du serveur -> " + reponse);

            // Étape 5 : Fermeture du scanner et de la socket pour libérer proprement les ressources
            scanner.close();
            socket.close();
            System.out.println("Client déconnecté.");

        } catch (Exception e) {
            // Capture et affichage des erreurs en cas de problème de connexion ou d'E/S
            e.printStackTrace();
        }
    }
}