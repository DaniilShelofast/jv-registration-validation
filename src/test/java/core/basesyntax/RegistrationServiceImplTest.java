package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.db.Storage;
import core.basesyntax.exception.RegistrationException;
import core.basesyntax.model.User;
import core.basesyntax.service.RegistrationService;
import core.basesyntax.service.RegistrationServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegistrationServiceImplTest {

    private static RegistrationService service;

    @BeforeAll
    static void beforeAll() {
        service = new RegistrationServiceImpl();
    }

    @BeforeEach
    void setUp() {
        Storage.people.clear();
    }

    @Test
    void register_userNotNull_Ok() {
        User u = new User("121212", "323123", 43);
        assertNotNull(service.register(u), "User is invalid!");
    }

    @Test
    void register_userNull_notOk() {
        User u = new User(null, null, null);
        assertThrows(RegistrationException.class, () -> {
            service.register(u);
        });

    }

    @Test
    void register_userNotEmpty_notOk() {
        User u = new User("", "", 0);
        assertThrows(RegistrationException.class, () -> {
            service.register(u);
        });

    }

    @Test
    void register_loginNull_notOk() {
        User u = new User(null, "323123", 34);
        assertThrows(RegistrationException.class, () -> {
            service.register(u);
        });

    }

    @Test
    void register_loginNotEmpty_Ok() {
        User u = new User(" ", "323123", 34);
        assertThrows(RegistrationException.class, () -> {
            service.register(u);
        });
    }

    @Test
    void register_loginLength3_notOk() {
        User u = new User("642", "н53453", 34);
        assertThrows(RegistrationException.class, () -> {
            service.register(u);
        });
    }

    @Test
    void register_loginLength6_Ok() {
        User u = new User("642434", "323123", 34);
        assertNotNull(service.register(u), "User should be registered successfully");
    }

    @Test
    void register_loginLengthGreater6_Ok() {
        User u = new User("6424345454", "323123", 34);
        assertNotNull(service.register(u), "User should be registered successfully");
    }

    @Test
    void register_passwordNull_notOk() {
        User u = new User("6345334", null, 34);
        assertThrows(RegistrationException.class, () -> {
            service.register(u);
        });

    }

    @Test
    void register_passwordLength0_notOk() {
        User u = new User("642434", "", 34);
        assertThrows(RegistrationException.class, () -> {
            service.register(u);
        });
    }

    @Test
    void register_passwordNotEmpty_Ok() {
        User u = new User("642434", " ", 34);
        assertThrows(RegistrationException.class, () -> {
            service.register(u);
        });
    }

    @Test
    void register_passwordLength3_notOk() {
        User u = new User("642434", "321", 34);
        assertThrows(RegistrationException.class, () -> {
            service.register(u);
        });
    }

    @Test
    void register_passwordLength6_Ok() {
        User u = new User("642434", "323123", 34);
        assertNotNull(service.register(u), "User should be registered successfully");
    }

    @Test
    void register_passwordLengthGreater6_Ok() {
        User u = new User("6424345454", "32312geg3", 34);
        assertNotNull(service.register(u), "User should be registered successfully");
    }

    @Test
    void register_userAgeNotNull_Ok() {
        User u = new User("6424345454", "32312geg3", 34);
        assertNotNull(service.register(u), "User should be registered successfully");
    }

    @Test
    void register_userAgeAdmissible_Ok() {
        User u = new User("6424345454", "32312geg3", 18);
        assertNotNull(service.register(u), "User should be registered successfully");
    }

    @Test
    void register_userAge_notOk() {
        User u = new User("642434", "324341", null);
        assertThrows(RegistrationException.class, () -> {
            service.register(u);
        });
    }

    @Test
    void register_userAgeZero_notOk() {
        User u = new User("642434", "324341", 0);
        assertThrows(RegistrationException.class, () -> {
            service.register(u);
        });
    }
}
