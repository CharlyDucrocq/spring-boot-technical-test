package com.supralog.recruitment.springexamusercrud.users;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.supralog.recruitment.springexamusercrud.users.models.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	@Test
	public void getAll_working(){
		List<User> expectedUsers = List.of(new User(1L), new User(2L));
		when(userRepository.findAll()).thenReturn(expectedUsers);

		List<User> output = userService.getAll();

		assertEquals(expectedUsers, output);
	}

	@Test
	public void create_working(){
		User input = new User();
		User expectedOutput = new User(2L);
		when(userRepository.save(eq(input))).thenReturn(expectedOutput);

		User output = userService.create(input);

		assertEquals(expectedOutput, output);
	}
}
