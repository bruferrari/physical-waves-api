package com.physics.api.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.physics.api.commons.QuestionSqlConstants;
import com.physics.api.connection.DataSourceFactory;

public class QuestionDAO {
	
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource = new DataSourceFactory().getDataSource();
	
	public QuestionDAO() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public byte[] findImageQuestion(Long contentId, Long questionId) {
		byte[] result = this.jdbcTemplate.queryForObject(QuestionSqlConstants.RETURN_QUESTIONS, 
				new Object[]{contentId, questionId}, byte[].class);
		
		return result;
	}
}
