package net.bulldozer.tourofall.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import net.bulldozer.tourofall.model.Comment;

@Repository
public class DestinationDao {
	private JdbcOperations jo;
	
	@Autowired
	public DestinationDao(DataSource dataSource){
		jo = new JdbcTemplate(dataSource);
	}
	
	public List<Comment> getCommentsByItemId(int itemId){
		return jo.query("select title, content, created_date, visitor,score, user_id from comments where item_id=?", new Object[]{itemId}, new RowMapper<Comment>(){
			@Override
			public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
				Comment comment = new Comment();
				comment.setTitle(rs.getString("title"));
				comment.setContent(rs.getString("content"));
				comment.setCreatedDate(rs.getDate("created_date"));
				comment.setVisitor(rs.getInt("visitor"));
				comment.setScore(rs.getDouble("score"));
				comment.setUserId(rs.getInt("user_id"));
				return comment;
			}
			
		});
	}

	public boolean addComment(Comment comment) {
		String title = comment.getTitle();
		String content = comment.getContent();
		double score = comment.getScore();
		int user_id = comment.getUserId();
		int item_id = comment.getItemId();
		
		return (jo.update("insert into comments(title, content, score, user_id, item_id) values(?,?,?,?,?)", new Object[]{title, content, score, user_id, item_id}) == 1);
	}
}
