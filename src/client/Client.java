package client;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.IPessoaDAO;
import dao.PessoaDAO;
import domain.Pessoa;

public class Client {
	
	public static void main(String args[]) throws Exception {
		int op;
		String nome;
		String cpf;
		Pessoa pessoa = new Pessoa();
		Scanner ler = new Scanner(System.in);;
		IPessoaDAO pessoaDAO = null;
		
		Registry registry;
		String serverAddress = "localhost";
	    String serverPort= "10000";
		
		registry = LocateRegistry.getRegistry(serverAddress, Integer.parseInt(serverPort));
		
		try {
			pessoaDAO = (IPessoaDAO) (registry.lookup("pessoasql"));
        } catch (NotBoundException | RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		do {
			System.out.println("\nSelecione uma opção:");
            System.out.println("1 - Cadastrar pessoa.");
            System.out.println("2 - Buscar pessoa.");
            System.out.println("3 - Deletar pessoa.");
            System.out.println("0 - Sair.");
			
			op = ler.nextInt();
			
			switch(op) {
				case 1:
	                System.out.println("Digite o nome e o cpf da pessoa: ");
	                
	                ler.nextLine();
	                System.out.print(">");
	                pessoa.setNome(ler.nextLine());
	                
	                ler.nextLine();
	                System.out.print(">");
	                pessoa.setCpf(ler.nextLine());
	
	                System.out.println(pessoaDAO.criarPessoa(pessoa));
	
	                break;
	
	            case 2:
	                System.out.print("Digite o CPF da pessoa: ");
	                ler.nextLine();
	                System.out.print(">");
	
	                System.out.println(pessoaDAO.buscarPessoaCPF(ler.nextLine()));
	
	                break;
	
	            case 3:
	                System.out.println("Digite o CPF da pessoa que deseja deletar: ");
	                ler.nextLine();
	
	                System.out.print(">");
	                System.out.println(pessoaDAO.deletarPessoa(ler.nextLine()));
	
	                break;
	                
	            case 0:
                    break;

                default:
                    System.out.println("Opção inválida!!!!");
			}
		} while (op!=0);
	}
}
