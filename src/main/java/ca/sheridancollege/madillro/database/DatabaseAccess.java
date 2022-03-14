package ca.sheridancollege.madillro.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.madillro.beans.Meeting;
import ca.sheridancollege.madillro.beans.User;

@Repository
public class DatabaseAccess {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public User findUserAccount(String email) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM sec_user WHERE email = :email";
		parameters.addValue("email", email);
		ArrayList<User> userList = (ArrayList<User>) jdbc.query(query, parameters,
				new BeanPropertyRowMapper<User>(User.class));
		if (userList.size() > 0)
			return userList.get(0);
		else
			return null;
	}

	public List<String> getRolesById(Long userId) {
		ArrayList<String> roleList = new ArrayList<String>();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT user_role.userId, sec_role.roleName " + "FROM user_role, sec_role "
				+ "WHERE user_role.roleId = sec_role.roleId " + "AND userId = :userId ";
		parameters.addValue("userId", userId);
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		for (Map<String, Object> row : rows) {
			roleList.add((String) row.get("roleName"));
		}
		return roleList;
	}

	public void addUser(String email, String password) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "INSERT INTO sec_user (email, encryptedPassword, enabled) "
				+ "VALUES (:email, :encryptedPassword, 1)";
		namedParameters.addValue("email", email);
		namedParameters.addValue("encryptedPassword", passwordEncoder.encode(password));
		jdbc.update(query, namedParameters);
	}

	public void addRole(Long userId, Long roleId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "INSERT INTO user_role (userId, roleId) " + "VALUES(:userId, :roleId)";
		namedParameters.addValue("userId", userId);
		namedParameters.addValue("roleId", roleId);
		jdbc.update(query, namedParameters);
	}

	public void insertMeeting(Meeting meeting, String email) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "INSERT INTO meeting(email, meetingDate1, meetingTime1, meetingDate2, meetingTime2) VALUES (:email, :meetingDate1, :meetingTime1, :meetingDate2, :meetingTime2)";		
		namedParameters.addValue("email", findUserAccount(email).getEmail());
		namedParameters.addValue("meetingDate1", meeting.getMeetingDate1());
		namedParameters.addValue("meetingTime1", meeting.getMeetingTime1());
		namedParameters.addValue("meetingDate2", meeting.getMeetingDate2());
		namedParameters.addValue("meetingTime2", meeting.getMeetingTime2());
		int rowsAffected = jdbc.update(query, namedParameters);
		if (rowsAffected > 0) {
			System.out.println("Inserted meeting into database");
		}
	}

	public List<Meeting> getAllMeetings() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM meeting";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Meeting>(Meeting.class));
	}

}