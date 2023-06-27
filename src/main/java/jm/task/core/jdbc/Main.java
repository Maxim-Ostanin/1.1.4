package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Alex", "Grin", (byte) 20);
        userService.saveUser("Ben", "Red", (byte) 25);
        userService.saveUser("Djo", "Black", (byte) 31);
        userService.saveUser("Sam", "Gold", (byte) 38);

        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
