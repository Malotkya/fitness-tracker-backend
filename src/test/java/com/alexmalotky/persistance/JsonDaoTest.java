package com.alexmalotky.persistance;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private JsonDao dao = new JsonDao();

    @Test
    void insertTest() {
        String newUser = "{\"id\":null, \"userName\":\"testUser\", \"firstName\":\"test\", \"lastName\":\"user\", \"email\":\"null\"}";
        String test = dao.insert(newUser);

        logger.debug(test);
    }

    @Test
    void deleteTest() {
    }

    @Test
    void saveOrUpdateTest() {
    }
}