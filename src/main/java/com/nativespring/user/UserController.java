package com.nativespring.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class UserController {
	@GetMapping("/test")
	public User test() {
		User user = new User((long) 90001, "test", "1234");
		return user;
	}
	
	@PostMapping("/user")
	public User login(@RequestBody User user) {
		System.out.println(user);
		return user;
	}
}
