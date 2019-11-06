package com.alexmalotky.util;

import com.alexmalotky.entity.*;
import com.alexmalotky.persistance.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

class JsonParserTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final String json = "{\"id\":1, \"userName\":\"ajmalotky\", \"password\":\"qwerty\", \"firstName\":\"Alex\", \"lastName\":\"Malotky\", \"email\":\"null\", \"Settings\":{\"Goal\":250}, \"Activities\": [{ \"id\":1,\"name\":\"Settings\", \"settings\":[{\"Value\":\"Weight\", \"Static\":false}, {\"Value\":\"Goal\", \"Static\":true}], \"notes\":\"null\", \"public\":false, \"logs\":[ { \"date\":1572886066000, \"value\":{\"Weight\":300}} ]}]}";

    @Test
    void stringifyTest() {
        GenericDao<User> userDao = new GenericDao<>(User.class);

        User user = userDao.getById(1);

        //logger.debug("Stringify:" + user.toJson());
    }

    @Test
    void parserTest() {
        User user = JsonParser.parse(json);
        //logger.debug("   Parser:" + user.toJson());
    }
}