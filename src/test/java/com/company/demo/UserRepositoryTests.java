package com.company.demo;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.demo.access.UserRepository;
import com.company.demo.domain.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired private UserRepository repo;

    @Test
    public void testCreateUser() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //String password = passwordEncoder.encode("nam2020");
        String password = passwordEncoder.encode("12345678");
        System.out.println("Encriptado: " + password);

        User newUser = new User("nam@codejava.net", password);
        //password cifrado:
        //$2a$10$9gGwcrzVXDBZe0rN7Eye6u90x4em6w9g96zhmLQY2B5/eeJt5zICC

        //User savedUser = repo.save(newUser);

        //assertThat(savedUser).isNotNull();
        //assertThat(savedUser.getId()).isGreaterThan(0);
    }
}