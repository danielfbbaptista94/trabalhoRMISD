package dao;

import java.rmi.*;
import java.sql.SQLException;

import domain.Pessoa;

public interface IPessoaDAO extends Remote {
	
	public Pessoa criarPessoa(Pessoa pessoa) throws SQLException, RemoteException;
	
	public String buscarPessoaCPF(String cpf) throws SQLException, RemoteException;
	
	public String deletarPessoa(String cpf) throws SQLException, RemoteException;

}
