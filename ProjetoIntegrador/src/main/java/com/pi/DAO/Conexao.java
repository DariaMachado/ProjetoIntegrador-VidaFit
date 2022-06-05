package com.pi.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class Conexao {
private static BasicDataSource dataSource = null;
	
	public static DataSource getDataSource() {
		if(dataSource==null) {
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			dataSource.setUsername("sa");
			dataSource.setPassword("opet");
			dataSource.setUrl("jdbc:sqlserver://localhost:1433;databaseName=Projeto;encrypt=false");
			dataSource.setInitialSize(50);
			dataSource.setMaxIdle(100);
			dataSource.setMaxTotal(1000);
			dataSource.setMaxWaitMillis(5000);
		}
		return dataSource;
	}
	
	public static Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}
}

