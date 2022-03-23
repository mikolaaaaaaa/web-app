package com.epam.webapp.service;

import com.epam.webapp.exception.ServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserServiceImplTest {
    private static final String CORRECT_INPUT_LOGIN = "pet";
    private static final String CORRECT_INPUT_PASSWORD = "pet";
    private static final String INCORRECT_INPUT_LOGIN = "pUdt";
    private static final String INCORRECT_INPUT_PASSWORD = "pAkt";

//    @Test
//    public void testLoginShouldCompareCorrectInputDataWithDataReturnTrue() throws ServiceException {
//       UserService userService = new ClientService();
//       boolean actual = userService.login(CORRECT_INPUT_LOGIN, CORRECT_INPUT_PASSWORD);
//       Assertions.assertTrue(actual);
//    }

//    @Test
//    public void testLoginShouldCompareIncorrectInputDataWithDataReturnFalse() throws ServiceException {
//        UserService userService = new ClientService();
//        boolean actual = userService.login(INCORRECT_INPUT_LOGIN, INCORRECT_INPUT_PASSWORD);
//        Assertions.assertFalse(actual);
//    }
}
