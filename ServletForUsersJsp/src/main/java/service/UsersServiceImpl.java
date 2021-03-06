package service;

import dao.UsersDao;
import models.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;
import java.security.SecureRandom;
import java.math.BigInteger;

public class UsersServiceImpl implements UsersService {
    private UsersDao usersDao;

    public UsersServiceImpl(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public List<User> getAllUser() {
        return this.usersDao.getAll();
    }

    @Override
    public User findUserById(int id) {
        return this.usersDao.find(id);
    }

    private String passwordHashing(String password) {
        return DigestUtils.md5Hex(password);
    }

    @Override
    public void addUser(User user) {
        String password = this.passwordHashing(user.getPassword());
        User updateUser = new User(user.getId(), user.getFirstName(), user.getLastName(),
                user.getDateOfBirth(), user.getCity(), user.getLogin(), password, user.getToken());
        this.usersDao.add(updateUser);
    }

    @Override
    public void updateUser(User user) {
        String password = this.passwordHashing(user.getPassword());
        User updateUser = new User(user.getId(), user.getFirstName(), user.getLastName(),
                user.getDateOfBirth(), user.getCity(), user.getLogin(), password, user.getToken());
        this.usersDao.update(updateUser);
    }

    @Override
    public void deleteUser(int id) {
        this.usersDao.delete(id);
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) {
        List<User> allUsers = this.usersDao.getAll();
        for (User oneUser : allUsers) {
            if (oneUser.getLogin().equals(login) && oneUser.getPassword().equals(DigestUtils.md5Hex(password))){
                return oneUser;
            }
        }
        return null;
    }

    @Override
    public User findUserByToken(String token) {
        List<User> allUsers = this.usersDao.getAll();
        for (User oneUser : allUsers) {
            if (oneUser.getToken().equals(token)){
                return oneUser;
            }
        }
        return null;
    }

    @Override
    public String setToken(User user) {
        SecureRandom random = new SecureRandom();
        String token = new BigInteger(130, random).toString(32);
        user.setToken(token);
        this.usersDao.setToken(user);
        return token;
    }
}
