package principal;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Solicita ao usuário o endereço e a porta
            System.out.print("Digite o endereço (ex: www.gov.com.br): ");
            String endereco = scanner.nextLine();

            System.out.print("Digite a porta (ex: 80): ");
            int porta = Integer.parseInt(scanner.nextLine());

            // Cria o socket com os dados fornecidos
            Socket sock = new Socket(endereco, porta);

            // Configura entrada e saída
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

            // Envia a requisição HTTP
            out.println("GET / HTTP/1.0");
            out.println(); // Linha em branco indica fim do cabeçalho

            // Lê e exibe a resposta
            String linha;
            while ((linha = in.readLine()) != null) {
                System.out.println("echo: " + linha);
            }

            // Fecha recursos
            in.close();
            out.close();
            sock.close();

        } catch (NumberFormatException e) {
            System.err.println("Porta inválida.");
        } catch (UnknownHostException e) {
            System.err.println("Endereço desconhecido.");
        } catch (IOException e) {
            System.err.println("Problemas de IO: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
