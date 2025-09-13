package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
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
    void register_validUser_Ok() {
        User user = new User("121212", "323123", 18);
        User actual = service.register(user);
        assertEquals(actual, user);
    }

    @Test
    void register_userNull_notOk() {
        User user = new User(null, null, null);
        assertThrows(RegistrationException.class, () -> {
            service.register(user);
        });
    }

    @Test
    void register_userNotEmpty_notOk() {
        User user = new User("", "", 0);
        assertThrows(RegistrationException.class, () -> {
            service.register(user);
        });
    }

    @Test
    void register_loginNull_notOk() {
        User user = new User(null, "323123", 34);
        assertThrows(RegistrationException.class, () -> {
            service.register(user);
        });
    }

    @Test
    void register_loginNotEmpty_notOk() {
        User user = new User(" ", "323123", 34);
        assertThrows(RegistrationException.class, () -> {
            service.register(user);
        });
    }

    @Test
    void register_loginLength3_notOk() {
        User user = new User("642", "н53453", 34);
        assertThrows(RegistrationException.class, () -> {
            service.register(user);
        });
    }

    @Test
    void register_loginLength5_notOk() {
        User user = new User("64231", "н53453", 34);
        assertThrows(RegistrationException.class, () -> {
            service.register(user);
        });
    }

    @Test
    void register_loginLength6_Ok() {
        User user = new User("121212", "323123", 43);
        User actual = service.register(user);
        assertEquals(actual, user);
    }

    @Test
    void register_loginLengthGreater6_Ok() {
        User user = new User("12121242342", "3231232425", 43);
        User actual = service.register(user);
        assertEquals(actual, user);
    }

    @Test
    void register_passwordNull_notOk() {
        User user = new User("6345334", null, 34);
        assertThrows(RegistrationException.class, () -> {
            service.register(user);
        });
    }

    @Test
    void register_passwordLength0_notOk() {
        User user = new User("642434", "", 34);
        assertThrows(RegistrationException.class, () -> {
            service.register(user);
        });
    }

    @Test
    void register_passwordNotEmpty_notOk() {
        User user = new User("642434", " ", 34);
        assertThrows(RegistrationException.class, () -> {
            service.register(user);
        });
    }

    @Test
    void register_passwordLength3_notOk() {
        User user = new User("642434", "321", 34);
        assertThrows(RegistrationException.class, () -> {
            service.register(user);
        });
    }

    @Test
    void register_passwordLength5_notOk() {
        User user = new User("642434", "32331", 34);
        assertThrows(RegistrationException.class, () -> {
            service.register(user);
        });
    }

    @Test
    void register_passwordLength6_Ok() {
        User user = new User("121212", "323123", 43);
        User actual = service.register(user);
        assertEquals(actual, user);
    }

    @Test
    void register_passwordLengthGreater6_Ok() {
        User user = new User("12121254545", "32312343242", 43);
        User actual = service.register(user);
        assertEquals(actual, user);
    }

    @Test
    void register_userAgeNotNull_Ok() {
        User user = new User("2625465236342", "2t2tr22r32", 34);
        User actual = service.register(user);
        assertEquals(actual, user);
    }

    @Test
    void register_userAgeAdmissible_Ok() {
        User user = new User("2625465236342", "2t2tr22r32", 18);
        User actual = service.register(user);
        assertEquals(actual, user);
    }

    @Test
    void register_userAge17_notOk() {
        User user = new User("642434", "321323", 17);
        assertThrows(RegistrationException.class, () -> {
            service.register(user);
        });
    }

    @Test
    void register_ageNull_notOk() {
        User user = new User("642434", "324341", null);
        assertThrows(RegistrationException.class, () -> {
            service.register(user);
        });
    }

    @Test
    void register_userAgeZero_notOk() {
        User user = new User("642434", "324341", 0);
        assertThrows(RegistrationException.class, () -> {
            service.register(user);
        });
    }

    @Test
    void register_userAgeNegative_notOk() {
        User user = new User("642434", "324341", -1);
        assertThrows(RegistrationException.class, () -> {
            service.register(user);
        });
    }

    @Test
    void register_duplicateLoginUser_notOk() {
        StorageDao dao = new StorageDaoImpl();
        User existingUser = new User("642434", "324341", 21);
        Storage.people.add(existingUser);
        User userWithSameLogin = dao.get("642434");
        assertThrows(RegistrationException.class, () -> {
            service.register(userWithSameLogin);
        });
    }
}
