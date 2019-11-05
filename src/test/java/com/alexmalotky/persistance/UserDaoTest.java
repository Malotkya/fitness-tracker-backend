package com.alexmalotky.persistance;

import com.alexmalotky.entity.Machine;
import com.alexmalotky.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Test
    void getUserById() {
        GenericDao<User> userDao = new GenericDao<>(User.class);

        User user = userDao.getById(1);

        logger.debug(user.toString());
    }
}
