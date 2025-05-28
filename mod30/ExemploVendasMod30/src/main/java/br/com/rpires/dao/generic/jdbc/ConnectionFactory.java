package br.com.rpires.dao.generic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por fornecer conexões com o banco de dados PostgreSQL.
 */
public class ConnectionFactory {

	private static Connection connection;

	// Construtor privado para impedir instâncias
	private ConnectionFactory() {
	}

	public static Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			connection = initConnection();
		}
		return connection;
	}

	private static Connection initConnection() {
		try {
			// Força o carregamento do driver
			Class.forName("org.postgresql.Driver");

			return DriverManager.getConnection(
					"jdbc:postgresql://localhost:15432/vendas_online_2",
					"postgres",
					"eliezer");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Driver JDBC do PostgreSQL não encontrado!", e);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao conectar com o banco de dados!", e);
		}
	}
}