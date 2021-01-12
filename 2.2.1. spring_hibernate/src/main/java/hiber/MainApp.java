package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User5", "Lastname5", "user5@mail.ru", new Car("MAZ", 555)));
        userService.add(new User("User6", "Lastname6", "user6@mail.ru", new Car("VAZ", 666)));
        userService.add(new User("User6", "Lastname6", "user6@mail.ru", new Car("VAZ", 111)));
        userService.add(new User("User6", "Lastname6", "user6@mail.ru", new Car("VAZ", 222)));
        userService.add(new User("User7", "Lastname7", "user7@mail.ru", new Car("GAZ", 777)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Model car = " + user.getCar().getModel());
            System.out.println("Series car = " + user.getCar().getSeries());
            System.out.println();
        }

        System.out.println("====================================");
        try {
           User user = userService.getUserByCar("MAZ", 555);

           System.out.println("Id = " + user.getId());
           System.out.println("First Name = " + user.getFirstName());
           System.out.println("Last Name = " + user.getLastName());
           System.out.println("Email = " + user.getEmail());
           System.out.println("Model car = " + user.getCar().getModel());
           System.out.println("Series car = " + user.getCar().getSeries());
           System.out.println();
        } catch (NoResultException e) {
           System.out.println("Владельца такого автомобиля нет");
        }

        context.close();
    }
}
