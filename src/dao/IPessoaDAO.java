package dao;

import java.rmi.*;
import java.sql.SQLException;

import domain.Pessoa;

public interface IPessoaDAO extends Remote {
	
	public Pessoa criarPessoa(Pessoa pessoa) throws SQLException;
	
	public String buscarPessoaCPF(String cpf) throws SQLException;
	
	public boolean deletarPessoa(String cpf) throws SQLException;

}
