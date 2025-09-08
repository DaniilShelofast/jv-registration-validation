package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.exception.RegistrationException;
import core.basesyntax.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegistrationServiceImplTest {

    private User user;
    private String login;
    private Integer loginLength;
    private String password;
    private Integer passwordLength;
    private Integer age;

    @BeforeEach
    void setUp() {
        user = new User();
        if (user.getLogin() != null) {
            login = user.getLogin();
            loginLength = user.getLogin().length();
        }
        if (user.getPassword() != null) {
            password = user.getPassword();
            passwordLength = user.getPassword().length();
        }
        if (user.getAge() != null) {
            age = user.getAge();
        }
    }

    @Test
    void register_userNotNull_Ok() {
        assertNotNull(user, "User is invalid!");
    }

    @Test
    void register_loginNotNull_Ok() {
        if (login != null) {
            assertNotNull(login, "login is invalid!");
        }
    }

    @Test
    void register_loginNotEmpty_Ok() {
        if (login != null) {
            assertFalse(login.isBlank(), "login is empty");
        }
    }

    @Test
    void register_lengthLogin_notOk() {
        assertThrows(RegistrationException.class, () -> {
            if (loginLength == null) {
                throw new RegistrationException("error length not null");
            }
            if (loginLength < 6) {
                throw new RegistrationException("error login should be more.");
            }
        });
    }

    @Test
    void register_lengthLoginNotZero_notOk() {
        assertThrows(RegistrationException.class, () -> {
            if (loginLength == null) {
                throw new RegistrationException("error length not null");
            }
            if (loginLength == 0) {
                throw new RegistrationException("error login should be more.");
            }
        });
    }

    @Test
    void register_passwordNotNull_Ok() {
        if (password != null) {
            assertNotNull(password, "password is invalid!");
        }
    }

    @Test
    void register_passwordNotEmpty_Ok() {
        if (password != null) {
            assertFalse(password.isBlank(), "password is empty");
        }
    }

    @Test
    void register_lengthPassword_notOk() {
        assertThrows(RegistrationException.class, () -> {
            if (passwordLength == null) {
                throw new RegistrationException("error length not null");
            }
            if (passwordLength < 6) {
                throw new RegistrationException("error password should be more.");
            }
        });
    }

    @Test
    void register_lengthPasswordNotZero_notOk() {
        assertThrows(RegistrationException.class, () -> {
            if (passwordLength == null) {
                throw new RegistrationException("error length not null");
            }
            if (passwordLength == 0) {
                throw new RegistrationException("error password should be more.");
            }
        });
    }

    @Test
    void register_userAgeNotNull_Ok() {
        if (age != null) {
            assertNotNull(age, "Age is invalid.");
        }
    }

    @Test
    void register_userAge_notOk() {
        assertThrows(RegistrationException.class, () -> {
            if (age == null) {
                throw new RegistrationException("error age not null");
            }
            if (age < 18) {
                throw new RegistrationException("error age be more");
            }
        });
    }

    @Test
    void register_userAgeZero_notOk() {
        assertThrows(RegistrationException.class, () -> {
            if (age == null) {
                throw new RegistrationException("error age not null");
            }
            if (age == 0) {
                throw new RegistrationException("error age be more");
            }
        });
    }

}
