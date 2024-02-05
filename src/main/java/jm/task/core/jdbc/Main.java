package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService us = new UserServiceImpl();
        us.createUsersTable();
        us.saveUser("Anime", "Naruto", (byte) 23);
        us.saveUser("Stas", "Mihailov", (byte) 99);
        us.saveUser("Jafar", "Harisa", (byte) 20);
        us.saveUser("asdads", "asfafdf", (byte) 55);
        List<User> list = us.getAllUsers();
        list.forEach(System.out::println);
        us.cleanUsersTable();
        us.dropUsersTable();
    }
}
