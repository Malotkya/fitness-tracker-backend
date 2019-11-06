package com.alexmalotky.persistance;

import com.alexmalotky.entity.*;
import com.alexmalotky.util.Database;
import com.alexmalotky.util.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private JsonDao dao = new JsonDao();

    @BeforeEach
    void setUp () {
        Database database = Database.getInstance();
        database.runSQL("clean.sql");
    }

    @Test
    void insertTest() {
        String newUser = "{\"id\":null, \"userName\":\"testUser\", \"password\":\"12345\", \"firstName\":\"test\", \"lastName\":\"user\", \"email\":\"null\"}";
        String test = dao.insert(newUser);

        logger.debug(test);

        GenericDao<User> userDao = new GenericDao<>(User.class);
        List<User> list = userDao.getAll();

        assertEquals(2, list.size());
    }

    @Test
    void deleteTest() {
        String user = dao.getByUserId(1);
        dao.delete(user);

        GenericDao<User> userDao = new GenericDao<>(User.class);
        List<User> list = userDao.getAll();

        assertEquals(0, list.size());
    }

    @Test
    void updateNewMachineTest() {
        String json = dao.getByUserId(1);
        User u = JsonParser.parse(json);

        Machine m = new Machine();
        m.setName("Running");
        m.setSettings("[{\"Value\":\"Laps\", \"Static\":false},{\"Value\":\"Time\", \"Static\":false}]");

        Log l = new Log();
        l.setDate(new Date());
        l.setValue("{\"Laps\":5, \"Time\":\"20 Minutes\"}");

        m.getLogs().add(l);
        u.getMachines().add(m);
        String newJson = u.toJson();

        assertNotEquals(json, newJson);
        dao.saveOrUpdate(newJson);

        String testJson = dao.getByUserId(u.getId());
        assertTrue(JsonParser.parse(newJson).equals(JsonParser.parse(testJson)));
    }

    @Test
    void updateNewLogTest() {
        String json = dao.getByUserId(1);
        User u = JsonParser.parse(json);

        Log l = new Log();
        l.setDate(new Date());
        l.setValue("{\"Weight\":500}");

        Machine m = (Machine)u.getMachines().toArray()[0];
        m.getLogs().add(l);
        String newJson = u.toJson();

        assertNotEquals(json, newJson);
        dao.saveOrUpdate(newJson);

        String testJson = dao.getByUserId(u.getId());
        assertTrue(JsonParser.parse(newJson).equals(JsonParser.parse(testJson)));
    }
}