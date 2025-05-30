package org.pluralsight.screens;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HomeTest {

    @Test
    void validateIfTrue() {
        Home home = new Home();
        // Pass if exist in customer.csv
        String email = "1";
        String password = "1";

        assertTrue(home.validateLogin(email, password));
    }

    @Test
    void validateIfFalse() {
        Home home = new Home();
        // Pass if it doesn't exist in customer.csv
        String email = "invalid@domain.com";
        String password = "invalid";

        assertFalse(home.validateLogin(email, password));
    }
}
