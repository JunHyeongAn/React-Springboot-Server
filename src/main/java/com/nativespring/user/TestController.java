package com.nativespring.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@GetMapping("/test")
	public User test() {
		User user = new User((long) 90001, "test1", "1234");
		return user;
	} 
}
