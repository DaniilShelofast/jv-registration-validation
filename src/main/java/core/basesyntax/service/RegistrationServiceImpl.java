package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.exception.RegistrationException;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        checkLogin(user);
        checkCountSymbolLogin(user);
        checkCountSymbolPassword(user);
        checkAgeUser(user);
        return storageDao.add(user);
    }

    private boolean checkLogin(User loginUser) {
        if (storageDao.get(loginUser.getLogin()) != null) {
            throw new RegistrationException("error data with such a login already exists.");
        }
        return true;
    }

    private boolean checkCountSymbolLogin(User user) {
        if (user.getLogin().length() < 6) {
            throw new RegistrationException("");
        }
        return true;
    }

    private boolean checkCountSymbolPassword(User user) {
        if (user.getPassword().length() < 6) {
            throw new RegistrationException("");
        }
        return true;
    }

    private boolean checkAgeUser(User user) {
        int ageMin = 18;
        if (user.getAge() < ageMin) {
            throw new RegistrationException("");
        }
        return true;
    }
}
