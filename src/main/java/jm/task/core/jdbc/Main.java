package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Alex", "Grin", (byte) 20);
        System.out.println("User с именем Alex добавлен в базу данных");
        userService.saveUser("Ben", "Red", (byte) 25);
        System.out.println("User с именем Ben добавлен в базу данных");
        userService.saveUser("Djo", "Black", (byte) 31);
        System.out.println("User с именем Djo добавлен в базу данных");
        userService.saveUser("Sam", "Gold", (byte) 38);
        System.out.println("User с именем Sam добавлен в базу данных");

        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
