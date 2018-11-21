package sk.upjs.registracia_itat.persitent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import sk.upjs.registracia_itat.entity.Companion;
import sk.upjs.registracia_itat.entity.Participant;

public class MysqlParticipantDao implements ParticipantDao {

	private JdbcTemplate jdbcTemplate;
	
	public MysqlParticipantDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void add(Participant participant) {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		simpleJdbcInsert.withTableName("participant");
		simpleJdbcInsert.usingGeneratedKeyColumns("id");
		simpleJdbcInsert.usingColumns("name", "surname", "email", "organization", "address", "ico", "dic", 
				"early", "student", "single_room", "start", "end", "cash",
				"tshirt", "workshop_id");
		Map<String,Object> hodnoty = new HashMap<>();
		hodnoty.put("name",participant.getName());
		hodnoty.put("surname",participant.getSurname());
		hodnoty.put("email",participant.getEmail());
		hodnoty.put("organization",participant.getOrganization());
		hodnoty.put("address",participant.getAddress());
		hodnoty.put("ico",participant.getIco());
		hodnoty.put("dic",participant.getDic());
		hodnoty.put("early",participant.isEarly());
		hodnoty.put("student",participant.isStudent());
		hodnoty.put("single_room",participant.isSingleRoom());
		hodnoty.put("start",participant.getStart());
		hodnoty.put("end", participant.getEnd());
		hodnoty.put("cash",participant.isCash());
		hodnoty.put("tshirt",participant.getTshirt());
		hodnoty.put("workshop_id",participant.getWorkshop().getId());
		Long id = simpleJdbcInsert.executeAndReturnKey(hodnoty).longValue();
		participant.setId(id);
		insertCompanions(participant);
	}

	private void insertCompanions(Participant participant) {
		List<Companion> companions = participant.getCompanions();
		if (companions != null && companions.size() > 0) {
			String insertSql = "INSERT INTO companion (start, end, category, tshirt, participant_id) VALUES (?,?,?,?,?)";
			for (Companion companion: companions) {
				jdbcTemplate.update(insertSql, companion.getStart(), companion.getEnd(), companion.getCategory().name(),
						companion.getTshirt().name(), participant.getId());
			}
		}
	}

	@Override
	public List<Participant> getAll() {
		String sql = "SELECT id, name, surname, email, organization, address, ico, dic, "
				+ "early, student, single_room, start, end, cash, tshirt, workshop_id FROM participant";
		List<Participant> participants = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Participant.class));
		WorkshopDao workshopDao = DaoFactory.INSTANCE.getWorkshopDao();
		for (Participant p : participants) {
			p.setCompanions(getCompanions(p.getId()));
			p.setWorkshop(workshopDao.getByParticipantId(p.getId()));
		}
		return participants;
	}

	@Override
	public void save(Participant p) throws NullPointerException {
		if (p == null) 
			throw new NullPointerException("participant cannot be null");
		if (p.getId() == null) {
			add(p);
		} else {
			String sql = "UPDATE participant SET name = ?, surname = ?, email = ?, organization = ?, "
					+ "address = ?, ico = ?, dic = ?, early = ?, student = ?, single_room = ?, start = ?, "
					+ "end = ?, cash = ?, tshirt = ?, workshop_id = ? WHERE id = ?";
			jdbcTemplate.update(sql, p.getName(), p.getSurname(), p.getEmail(), p.getOrganization(),
					p.getAddress(), p.getIco(), p.getDic(), p.isEarly(), p.isStudent(), p.isSingleRoom(),
					p.getStart(), p.getEnd(), p.isCash(), p.getTshirt().name(), p.getWorkshop().getId(), p.getId());
			jdbcTemplate.update("DELETE FROM companion WHERE participant_id = ?", p.getId());
			insertCompanions(p);
		}
	}
	
	private List<Companion> getCompanions(long participantId) {
		String sql = "SELECT id, start, end, category, tshirt FROM companion WHERE participant_id = " + participantId;
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Companion.class));
	}

	@Override
	public void delete(long id) {
		jdbcTemplate.update("DELETE FROM companion WHERE participant_id = ?", id);
		jdbcTemplate.update("DELETE FROM participant WHERE id = ?", id);
	}
}
