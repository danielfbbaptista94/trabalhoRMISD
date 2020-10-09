package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.IPessoaDAO;
import dao.PessoaDAO;

public class Server {
	
	public static void main(String args[]) {
		try {
			IPessoaDAO pessoaDAO = new 
			Registry registry = LocateRegistry.createRegistry(5000);
			registry.bind("pessoasql", pessoaDAO);
			System.out.println("Conectado!");
		} catch (Exception e) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
		}
		
		
	}
}
