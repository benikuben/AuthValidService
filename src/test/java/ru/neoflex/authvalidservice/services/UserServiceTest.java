package ru.neoflex.authvalidservice.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.neoflex.authvalidservice.models.User;
import ru.neoflex.authvalidservice.repositories.UserRepository;
import ru.neoflex.authvalidservice.services.impl.UserServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void findByUsername() {
        String username = "name";
        Optional<User> expectedUser1 = Optional.of(
                User.builder().username(username).build()
        );
        Optional<User> expectedUser2 = Optional.empty();

        when(userRepository.findByUsername(any()))
                .thenReturn(expectedUser1, expectedUser2);

        Optional<User> actualUser1 = userService.findByUsername(username);
        Optional<User> actualUser2 = userService.findByUsername(username);

        assertEquals(expectedUser1, actualUser1);
        assertEquals(expectedUser2, actualUser2);
    }

    @Test
    void save() {
        userService.save(new User());
        verify(userRepository, times(1)).save(any());
    }
}