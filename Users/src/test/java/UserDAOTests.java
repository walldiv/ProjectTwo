import com.ex.dao.UserDAO;
import com.ex.dao.UserDAOImpl_PGR;
import com.ex.model.Address;
import com.ex.model.PhoneCarrier;
import com.ex.model.User;
import com.ex.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserDAOTests {
    private UserDAO dao;
    private UserService service;

    private PhoneCarrier carrier = new PhoneCarrier();
    private Address address = new Address(1234, "Memory Lane", "null", "Grand Rapids", "MI",
            "USA", 49341);
    private User user = new User("John", "Sheerin", "7752305812", carrier,
            "john@mail.com", "password", address, 0, true);

    @Before
    public void init() {
        dao  = new UserDAOImpl_PGR();
    }

    @Test
    public void loginUser_Success() throws Exception {
        User tmp = dao.loginUser(user);
        Assert.assertNotNull("loginUser_Success() - USER RETURNED WAS NULL", tmp);
    }

    @Test
    public void displayUser_Success() {
        User tmp = dao.displayUser(user);
        Assert.assertNotNull("loginUser_Success() - USER RETURNED WAS NULL", tmp);
    }
}
