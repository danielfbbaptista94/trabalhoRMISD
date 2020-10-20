package dao;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Pessoa;


@SuppressWarnings("serial")
public class PessoaDAO extends UnicastRemoteObject implements IPessoaDAO, Serializable {
	
	private static final String SALVAR_PESSOA = "INSERT INTO pessoas(nome, cpf) VALUES (?,?)";
    private static final String SELECIONAR_PESSOABYCPF = "SELECT nome,cpf FROM pessoas WHERE cpf = ?";
    private static final String DELETE_PESSOA = "DELETE FROM pessoas WHERE cpf = ?";
	
	private static final String URI = "jdbc:mysql://localhost:3308/pessoa?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PWD = "1234";
    
    private Connection conn;
    
    public PessoaDAO() throws RemoteException {
        super();
    }

    private Connection getConnection() throws SQLException {

        if (this.conn != null) {
            return this.conn;
        }

        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        this.conn = DriverManager.getConnection(URI, USER, PWD);

        return this.conn;

    }

	@Override
	public Pessoa criarPessoa(Pessoa pessoa) throws SQLException, RemoteException {
		PreparedStatement pStmt = this.getConnection().prepareStatement(PessoaDAO.SALVAR_PESSOA);

        pStmt.setString(1, pessoa.getNome());
        pStmt.setString(2, pessoa.getCpf());

        pStmt.executeUpdate();

        return pessoa;
	}

	@Override
	public String buscarPessoaCPF(String cpf) throws SQLException, RemoteException {
		PreparedStatement pStmt = this.getConnection().prepareStatement(PessoaDAO.SELECIONAR_PESSOABYCPF);
		
		pStmt.setString(1, cpf);
        ResultSet rSet = pStmt.executeQuery();
        
        if(rSet.next()){
            Pessoa pessoa = new Pessoa(rSet.getString("nome"), rSet.getString("cpf"));
            rSet.close();
            pStmt.close();
            this.conn.close();
            return pessoa.toString();
        }
        return "Pessoa NÃO CADASTRADO NA BASE!!";
	}

	@Override
	public String deletarPessoa(String cpf) throws SQLException, RemoteException {
		try {
			PreparedStatement pStmt = this.getConnection().prepareStatement(PessoaDAO.DELETE_PESSOA);
			
			pStmt.setString(1, cpf);
			pStmt.execute();
			pStmt.close();
			
			return "Pessoa deletado!";
		} catch (Exception e) {
			return "Ocorreu um problema ao deletar!";
		}
	}

}
