package com.physics.api.connection;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSourceFactory {

	private static final String MYSQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_TEST = "physics_db_test";
	private static final String URL = "jdbc:mysql://localhost:3306/" + DB_TEST;

	private static final String USER = "admin";
	private static final String PASS = "appfisica2015!";
	
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName(MYSQL_JDBC_DRIVER);
		dataSource.setUrl(URL);
		dataSource.setUsername(USER);
		dataSource.setPassword(PASS);
		
		return dataSource;
	}
}
