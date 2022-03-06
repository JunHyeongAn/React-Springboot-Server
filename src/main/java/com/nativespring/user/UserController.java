package com.nativespring.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nativespring.jwt.JwtProvider;

@RestController
@RequestMapping("/v1/user")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	JwtProvider jwtProvider;
	
	@PostMapping("/login")
	public Map<String, String> login(@RequestBody User user, HttpServletResponse response) {
		UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
		String token = jwtProvider.createToken(userDetails.getUsername());
		response.setHeader("X-AUTH-TOKEN", token);
		Map<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		return map;
	}
	
	@PostMapping("/")
	public UserDetails join(@RequestBody User user) {
		String password = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(password);
		userService.saveUser(user);
		return user;
	}
}
