package sk.upjs.registracia_itat.persitent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import sk.upjs.registracia_itat.entity.Workshop;

public class MysqlWorkshopDao implements WorkshopDao {
	
	private JdbcTemplate jdbcTemplate;

	public MysqlWorkshopDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Workshop> getAll() {
		String sql = "SELECT id, name, start, end, price_full, price_student, "
				+ "price_full_late, price_student_late "
				+ "FROM workshop";
//		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WorkShop.class));
		return jdbcTemplate.query(sql, new RowMapper<Workshop>() {

			@Override
			public Workshop mapRow(ResultSet rs, int rowNum) throws SQLException {
				Workshop workShop = new Workshop();
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

	@Override
	public Workshop save(Workshop workshop) {
		if (workshop == null)
			return null;
		if (workshop.getId() == null) { //CREATE
			SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
			simpleJdbcInsert.withTableName("workshop");
			simpleJdbcInsert.usingGeneratedKeyColumns("id");
			simpleJdbcInsert.usingColumns("name", "start", "end", "price_full",
					"price_student", "price_full_late", "price_student_late");
			Map<String,Object> hodnoty = new HashMap<>();
			hodnoty.put("name",workshop.getName());
			hodnoty.put("start",workshop.getStart());
			hodnoty.put("end", workshop.getEnd());
			hodnoty.put("price_full", workshop.getPriceFull());
			hodnoty.put("price_student", workshop.getPriceStudent());
			hodnoty.put("price_full_late", workshop.getPriceFullLate());
			hodnoty.put("price_student_late", workshop.getPriceStudentLate());
			Long id = simpleJdbcInsert.executeAndReturnKey(hodnoty).longValue();
			workshop.setId(id);
		} else { //UPDATE
			String sql = "UPDATE workshop SET "
					+ "name = ?, start = ?, end = ?, price_full = ?, "
					+ "price_student = ?, price_full_late = ?, price_student_late = ? "
					+ "WHERE id = ?";
			jdbcTemplate.update(sql,
					workshop.getName(), workshop.getStart(),
					workshop.getEnd(), workshop.getPriceFull(),
					workshop.getPriceStudent(),	workshop.getPriceFullLate(),
					workshop.getPriceStudentLate(),	workshop.getId());
		}
		return workshop;
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM workshop WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

}
