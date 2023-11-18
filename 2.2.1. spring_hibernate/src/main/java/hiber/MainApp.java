package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
        User u1 = new User("Mike", "Sidorov", "ms@mail.ru");
        User u2 = new User("Steven", "Ivanov", "si@mail.ru");
        User u3 = new User("Irina", "Hakamada", "ih@gmail.ru");
        Car car1 = new Car("Mercedes-Benz W", 140);
        Car car2 = new Car("Kia K", 5);
        Car car3 = new Car("Infinity QX", 70);
        u1.setCar(car1);
        u2.setCar(car2);
        u3.setCar(car3);
        userService.add(u1);
        userService.add(u2);
        userService.add(u3);
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("Getting users by car");
        User userWithCar = userService.getUserByCar(new Car("Mercedes-Benz W", 140));
        User noUserWithTheCar = userService.getUserByCar(new Car("Merc", 14));
        System.out.println(userWithCar);
        System.out.println(noUserWithTheCar);
        context.close();
    }
}
