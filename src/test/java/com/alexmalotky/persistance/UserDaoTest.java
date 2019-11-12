package com.alexmalotky.persistance;

import com.alexmalotky.entity.User;
import com.alexmalotky.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Test
    void getById() {
        GenericDao<User> dao = new GenericDao<>(User.class);
        User u = dao.getById(1);

        logger.debug(u.toJson());
    }
}
