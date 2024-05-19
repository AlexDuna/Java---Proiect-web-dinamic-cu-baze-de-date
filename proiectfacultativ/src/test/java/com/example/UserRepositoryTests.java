package com.example;

import com.example.Entity.User;
import com.example.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

    @Test
    public void testCreateUser(){
        User user = new User();
        user.setUsername("username");
        user.setPassword("userpassword");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setRole("USER");

        User savedUser=repo.save(user);
        User existUser=entityManager.find(User.class,savedUser.getId());
        assertThat(user.getUsername()).isEqualTo(existUser.getUsername());
    }
}
