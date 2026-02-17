import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;


import java.util.List;

public class UserServiceTest {
    private final UserService userService = new UserServiceImpl(new UserDaoHibernateImpl());

    private final String testName = "Nick";
    private final String testLastName = "Martin";
    private final byte testAge = 15;
    private final String testWorkplace = "google";


    @Test
    public void dropUsersTable() {
        try {
            userService.dropUsersTable();
            userService.dropUsersTable();
        } catch (Exception e) {
            Assert.fail("An exception occurred while testing drop table\n" + e);
        }
    }

    @Test
    public void createUsersTable() {
        try {
            userService.dropUsersTable();
            userService.createUsersTable();
        } catch (Exception e) {
            Assert.fail("An exception occurred while testing to create a user table\n" + e.getMessage());
        }
    }

    @Test
    public void saveUser() {
        try {
            userService.dropUsersTable();
            userService.createUsersTable();
            userService.saveUser(testName, testLastName, testAge, testWorkplace);

            User user = userService.getAllUsers().get(0);

            if (!testName.equals(user.getName())
                    || !testLastName.equals(user.getLastName())
                    || testAge != user.getAge()
            ) {
                Assert.fail("User was incorrectly added to the database");
            }

        } catch (Exception e) {
            Assert.fail("An exception occurred while testing user save\n" + e);
        }
    }

    @Test
    public void removeUserById() {
        try {
            userService.dropUsersTable();
            userService.createUsersTable();
            userService.saveUser(testName, testLastName, testAge, testWorkplace);
            userService.removeUserById(1L);
        } catch (Exception e) {
            Assert.fail("An exception occurred while testing deleting a user by id\n" + e);
        }
    }

    @Test
    public void getAllUsers() {
        try {
            userService.dropUsersTable();
            userService.createUsersTable();
            userService.saveUser(testName, testLastName, testAge, testWorkplace);
            List<User> userList = userService.getAllUsers();

            if (userList.size() != 1) {
                Assert.fail("Check if the save/delete or create table method works correctly");
            }
        } catch (Exception e) {
            Assert.fail("An exception occurred while trying to get all users from the database\n" + e);
        }
    }

    @Test
    public void cleanUsersTable() {
        try {
            userService.dropUsersTable();
            userService.createUsersTable();
            userService.saveUser(testName, testLastName, testAge, testWorkplace);
            userService.cleanUsersTable();

            if (userService.getAllUsers().size() != 0) {
                Assert.fail("The method of clearing the user table is implemented incorrectly");
            }
        } catch (Exception e) {
            Assert.fail("An exception occurred while testing clearing the users table\n" + e);
        }
    }

    @Test
    public void findByWorkplacetest() {
        userService.createUsersTable();
        userService.cleanUsersTable();

        userService.saveUser("John", "Smith", (byte) 30, "Google");
        userService.saveUser("Nick", "Brown", (byte) 25, "Amazon");
        userService.saveUser("Mike", "White", (byte) 40, "Google");

        List<User> googleUsers = userService.findByWorkplace("Google");

        Assert.assertEquals(2, googleUsers.size());
    }

}
