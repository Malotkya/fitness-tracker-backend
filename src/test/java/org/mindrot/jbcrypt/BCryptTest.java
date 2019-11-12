package org.mindrot.jbcrypt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BCryptTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Test
    void hashpw() {
        String raw = "qwerty";
        String hashed = BCrypt.hashpw(raw, BCrypt.gensalt());

        logger.debug(hashed);
        assertTrue(BCrypt.checkpw(raw, hashed));
    }
}