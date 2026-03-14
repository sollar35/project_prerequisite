package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");

      Car car1 = new Car();
      car1.setModel("BMW");
      car1.setSeries(5);
      user1.setCar(car1);

      User user2 = new User("User2", "Lastname2", "user2@mail.ru");

      Car car2 = new Car();
      car2.setModel("BMW");
      car2.setSeries(3);
      user2.setCar(car2);

      User user3 = new User("User3", "Lastname3", "user3@mail.ru");

      Car car3 = new Car();
      car3.setModel("BMW");
      car3.setSeries(2);
      user3.setCar(car3);

      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      Car car4 = new Car();
      car4.setModel("BMW");
      car4.setSeries(1);
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car model = " + user.getCar().getModel());
         System.out.println("Car series = " + user.getCar().getSeries());
         System.out.println();
      }

      User user = userService.getUserByCar("BMW", 3);

      System.out.println(user.getFirstName());
      System.out.println(user.getLastName());

      context.close();
   }
}
