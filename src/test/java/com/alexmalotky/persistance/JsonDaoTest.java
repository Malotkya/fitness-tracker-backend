package com.alexmalotky.persistance;

import com.alexmalotky.entity.*;
import com.alexmalotky.util.Database;
import com.alexmalotky.util.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.AssertFalse;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JsonDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private JsonDao dao = new JsonDao();

    private String newLogServerString = "{\"id\":1,\"userName\":\"ajmalotky\",\"firstName\":\"Alex\",\"lastName\":\"Malotky\",\"email\":\"null\",\"Settings\":{\"Goal\":250},\"Running\":{\"Laps\":4},\"Bench Press\":{\"Seat Position\":2.5},\"Activities\":[{\"id\":2,\"name\":\"Running\",\"settings\":[{\"Value\":\"Laps\",\"Static\":true},{\"Value\":\"Time\",\"Static\":false}],\"notes\":\"null\",\"public\":true,\"logs\":[{\"date\":1573553736000,\"value\":{\"Time\":\"10 minutes\"}}]},{\"id\":3,\"name\":\"Bench Press\",\"settings\":[{\"Value\":\"Seat Position\",\"Static\":true},{\"Value\":\"Weight\",\"Static\":false},{\"Value\":\"Reps\",\"Static\":false}],\"notes\":\"null\",\"public\":true,\"logs\":[{\"date\":1573553736000,\"value\":{\"Reps\":12,\"Weight\":100}}]},{\"id\":1,\"name\":\"Settings\",\"settings\":[{\"Value\":\"Weight\",\"Static\":false},{\"Value\":\"Goal\",\"Static\":true}],\"notes\":\"null\",\"public\":true,\"logs\":[{\"date\":1573553736000,\"value\":{\"Weight\":300}},{\"date\":1573576055292,\"value\":{\"Weight\":\"350\"}}]}]}";

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
        l.setDate(new Date().getTime());
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
        String newJson = newLogServerString;

        assertNotEquals(json, newJson);
        dao.saveOrUpdate(newJson);

        String testJson = dao.getByUserId(u.getId());
        assertTrue(JsonParser.parse(newJson).equals(JsonParser.parse(testJson)));
    }

    @Test
    void removeLogTest() {
        String json = dao.getByUserId(1);
        User u = JsonParser.parse(json);

        Machine machine = (Machine)u.getMachines().toArray()[0];
        Set<Log> logs = machine.getLogs();
        Log log = (Log)logs.toArray()[0];
        logs.remove(log);

        User newUser = JsonParser.parse(dao.saveOrUpdate(u.toJson()));
        assertNotEquals(newUser.getMachines(), newUser.getMachines());
    }
}