package server;

import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.IPessoaDAO;
import dao.PessoaDAO;

public class Server {
	
	public static void main(String args[]) {
		
		int      thisPort = 10000;
	    String   thisAddress;
	    Registry registry;
	    
		try {
			System.out.println("Server STARTED!");
			
			thisAddress = (InetAddress.getLocalHost()).toString();
			IPessoaDAO pessoaDAO = new PessoaDAO();
			
			registry = LocateRegistry.createRegistry(thisPort); 
			registry.rebind("pessoasql", pessoaDAO);
			
			System.out.println("Conectado!");
		} catch (Exception e) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
		}
		
		
	}
}
