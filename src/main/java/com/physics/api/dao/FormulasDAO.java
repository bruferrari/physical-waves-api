package com.physics.api.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.physics.api.commons.FormulasSqlConstants;
import com.physics.api.connection.DataSourceFactory;

public class FormulasDAO {
	
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource = new DataSourceFactory().getDataSource();
	
	public FormulasDAO() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public byte[] findFormulaAsBytes(Long formulaId, Long contentId) {
		byte[] result = this.jdbcTemplate.queryForObject(FormulasSqlConstants.RETURN_UNIQUE_FORMULA_BY_ID, 
				new Object[]{contentId, formulaId}, byte[].class);
		
		return result;
	}
}
