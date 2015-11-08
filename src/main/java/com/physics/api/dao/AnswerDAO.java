package com.physics.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.physics.api.commons.AnswerSqlConstants;
import com.physics.api.connection.DBConnection;
import com.physics.api.connection.DataSourceFactory;
import com.physics.api.model.Answer;

public class AnswerDAO {
	
	/*					           *
	 *  ======== COLUMNS ========  *
	 *					           */
	
	private static final String ANSWER = "answer";
	private static final String CONTENT_KEY = "id_content";
	private static final String QUESTION_KEY = "id_question";
	
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource = new DataSourceFactory().getDataSource();

//	public List<Answer> allAnswers(Long idContent) {
//		List<Answer> answers = new ArrayList<Answer>();
//		try {
//			Connection con = DBConnection.getConnection();
//
//			PreparedStatement ppStm = con
//					.prepareStatement(AnswerSqlConstants.RETURN_LIST_ANSWER);
//			ppStm.setLong(1, idContent);
//
//			ResultSet resultSet = ppStm.executeQuery();
//
//			while (resultSet.next()) {
//				Answer a = new Answer();
//				a.setAnswer(resultSet.getString("answer"));
//				a.setContentId(resultSet.getLong("id_content"));
//				a.setQuestionId(resultSet.getLong("id_question"));
//				answers.add(a);
//			}
//			con.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return answers;
//	}
	
	public AnswerDAO() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Answer> getAllAnswers(Long contentId) {
		List<Answer> result = new ArrayList<>();
		result = jdbcTemplate.query(AnswerSqlConstants.RETURN_LIST_ANSWER, ANSWERS_ROW_MAPPER,
				new Object[]{contentId});
		
		return result;
	}
	
	private RowMapper<Answer> ANSWERS_ROW_MAPPER = new RowMapper<Answer>() {
		public Answer mapRow(ResultSet rs, int numRow) throws SQLException {
			Answer answer = new Answer();
			
			answer.setAnswer(rs.getString(ANSWER));
			answer.setContentId(rs.getLong(CONTENT_KEY));
			answer.setQuestionId(rs.getLong(QUESTION_KEY));
			
			return answer;
		}
	};

}
