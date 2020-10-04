package dao;

import java.rmi.*;

import domain.Pessoa;

public interface IPessoaDAO extends Remote {
	
	public Pessoa criarPessoa(Pessoa pessoa);
	
	public Pessoa buscarPessoaNome(String nome);
	
	public Pessoa buscarPessoaCPF(String cpf);
	
	public Pessoa deletarPessoa(Pessoa pessoa);

}
