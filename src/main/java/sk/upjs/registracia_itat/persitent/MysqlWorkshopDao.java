package sk.upjs.registracia_itat.persitent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import sk.upjs.registracia_itat.entity.WorkShop;

public class MysqlWorkshopDao implements WorkshopDao {
	
	private JdbcTemplate jdbcTemplate;

	public MysqlWorkshopDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<WorkShop> getAll() {
		String sql = "SELECT id, name, start, end, price_full, price_student, "
				+ "price_full_late, price_student_late "
				+ "FROM workshop";
//		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WorkShop.class));
		return jdbcTemplate.query(sql, new RowMapper<WorkShop>() {

			@Override
			public WorkShop mapRow(ResultSet rs, int rowNum) throws SQLException {
				WorkShop workShop = new WorkShop();
				workShop.setId(rs.getLong("id"));
				workShop.setName(rs.getString("name"));
				Timestamp timestamp = rs.getTimestamp("start");
				if (timestamp != null) {
					workShop.setStart(timestamp.toLocalDateTime().toLocalDate());
				}
				timestamp = rs.getTimestamp("end");
				if (timestamp != null) {
					workShop.setEnd(timestamp.toLocalDateTime().toLocalDate());
				}
				workShop.setPriceFull(rs.getDouble("price_full"));
				workShop.setPriceStudent(rs.getDouble("price_student"));
				workShop.setPriceFullLate(rs.getDouble("price_full_late"));
				workShop.setPriceStudentLate(rs.getDouble("price_student_late"));
				return workShop;
			}
			
		});
	}

}
