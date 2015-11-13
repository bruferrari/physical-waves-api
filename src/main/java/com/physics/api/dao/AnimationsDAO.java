package com.physics.api.dao;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.physics.api.commons.AnimationsSqlConstants;
import com.physics.api.connection.DBConnection;
import com.physics.api.connection.DataSourceFactory;
import com.physics.api.model.Animation;
import com.physics.api.model.Content;
import com.physics.api.model.Video;

public class AnimationsDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource = new DataSourceFactory().getDataSource();
	
	public AnimationsDAO() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public byte[] findAnimationAsByte(Long animationId, Long contentId) {
		byte[] result = this.jdbcTemplate.queryForObject(AnimationsSqlConstants.RETURN_UNIQUE_ANIMATION_BY_ID, 
				new Object[]{contentId, animationId}, byte[].class);
		
		return result;
	}
	
	public List<Animation> listAnimations() {
		List<Animation> result = new ArrayList<>();
		result = jdbcTemplate.query(AnimationsSqlConstants.RETURN_LIST_OF_ALL_ANIMATIONS, ANIMATIONS_ROW_MAPPER);
		
		return result;
	}
	
	private RowMapper<Animation> ANIMATIONS_ROW_MAPPER = new RowMapper<Animation>() {
		public Animation mapRow(ResultSet rs, int numRow) throws SQLException {
			Animation animation = new Animation();
			Content content = new Content();
			
			content.setId(rs.getLong("content_id"));
			
			animation.setId(rs.getLong("id"));
			animation.setDescription(rs.getString("description"));
			animation.setContent(content);
			
			return animation;
		}
	};

}
