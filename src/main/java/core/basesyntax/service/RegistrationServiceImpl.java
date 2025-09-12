package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.db.Storage;
import core.basesyntax.exception.RegistrationException;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {

    static final Integer MIN_AGE;
    static final Integer MIN_LOGIN_LENGTH;
    static final Integer MIN_PASSWORD_LENGTH;

    private final StorageDao storageDao = new StorageDaoImpl();

    static {
        MIN_AGE = 18;
        MIN_LOGIN_LENGTH = 6;
        MIN_PASSWORD_LENGTH = 6;
    }

    @Override
    public User register(User user) {
        checkCountSymbolLogin(user);
        checkCountSymbolPassword(user);
        checkAgeUser(user);
        for (User u : Storage.people) {
            if (u.getLogin().equals(user.getLogin())) {
                throw new RegistrationException("error : " + user.getLogin()
                        + " with such a login already exists.");
            }
        }
        return storageDao.add(user);
    }

    private boolean checkCountSymbolLogin(User user) {
        if (user.getLogin() == null) {
            throw new RegistrationException("Login can't be null");
        }
        if (user.getLogin().length() < MIN_LOGIN_LENGTH) {
            throw new RegistrationException("Login must be at least "
                    + MIN_LOGIN_LENGTH + " characters.");
        }
        return true;
    }

    private boolean checkCountSymbolPassword(User user) {
        if (user.getPassword() == null) {
            throw new RegistrationException("Password can't be null");
        }
        if (user.getPassword().length() < MIN_PASSWORD_LENGTH) {
            throw new RegistrationException("Password must be at least "
                    + MIN_PASSWORD_LENGTH + " characters.");
        }
        return true;
    }

    private boolean checkAgeUser(User user) {
        if (user.getAge() == null) {
            throw new RegistrationException("Age can't be null");
        }
        if (user.getAge() < MIN_AGE) {
            throw new RegistrationException("Not valid age: "
                    + user.getAge() + ". Min allowed age is " + MIN_AGE + ".");
        }
        return true;
    }
}
