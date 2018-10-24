package sk.upjs.registracia_itat.persitent;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import sk.upjs.registracia_itat.entity.WorkShop;

class MysqlWorkshopDaoTest {

	@Test
	void testGetAll() {
		List<WorkShop> workshops = DaoFactory.INSTANCE.getWorkshopDao().getAll();
		assertNotNull(workshops);
		assertTrue(workshops.size() > 0);
	}

}
