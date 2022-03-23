package mk.ukim.finki.emt.eshop.repository;

import mk.ukim.finki.emt.eshop.model.Role;
import mk.ukim.finki.emt.eshop.model.User;
import mk.ukim.finki.emt.eshop.model.exceptions.UserNotFoundException;
import mk.ukim.finki.emt.eshop.model.projections.UserProjection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;


    @Test
    public void testFindAll(){
        List<User> userList = this.userRepository.findAll();
        Assert.assertEquals(4L, userList.size());
    }

    @Test
    public void testFetchAll(){
        List<User> userList = this.userRepository.fetchAll();
        Assert.assertEquals(4L, userList.size());
    }

    @Test
    public void testLoadAll(){
        List<User> userList = this.userRepository.loadAll();
        Assert.assertEquals(4L, userList.size());
    }

    @Test
    public void testProjectUsernameAndUsernameAndSurname() {
        UserProjection userProjection = this.userRepository.findByRole(Role.ROLE_USER);
        Assert.assertEquals("user", userProjection.getUsername());
        Assert.assertEquals("user", userProjection.getName());
        Assert.assertEquals("user", userProjection.getSurname());
    }

    @Test
    public void testOptimisticLock() {
        User user1 = this.userRepository.findByUsername("user")
                .orElseThrow(() -> new UserNotFoundException("user"));
        User user2 = this.userRepository.findByUsername("user")
                .orElseThrow(() -> new UserNotFoundException("user"));

        user1.setUsername("user1");
        user2.setUsername("user2");

        this.userRepository.save(user1);
        this.userRepository.save(user2);
    }

}
