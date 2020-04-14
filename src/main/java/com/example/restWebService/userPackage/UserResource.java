package com.example.restWebService.userPackage;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

//import org.springframework.hateoas.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.restWebService.userPackage.exception.UserNotFoundException;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService userDaoService;

	// Retrieve All users
	@GetMapping(path = "/users")
	public List<User> getAllUsers() {
		return userDaoService.findAll();
	}

	// retrieve one user
	@GetMapping(path = "/users/{id}")
	public User getUserWithId(@PathVariable Integer id) {
		User user = userDaoService.finsOne(id);
		
		if(user == null)
			throw new UserNotFoundException("Id:: "+id);
		
		//also want to return all users link with this
		//HATEOS can be used
	
		
		return user;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.save(user);

		// We have to send the status and the Url to the Client

		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping(path = "/users/{id}")
	public User deleteUserWithId(@PathVariable Integer id) {
		User user = userDaoService.deleteById(id);
		if(user == null)
			throw new UserNotFoundException("Id:: "+id);
		return user;
	}
	
	/* @GetMapping(path = "/users/{uid}/posts")
	public List<Posts> getallPostsForUser(@PathVariable Integer uid) {
		List<Posts> posts = userDaoService.getPostsForUser(uid);
		if(posts == null || posts.size() == 0) {
			throw new UserNotFoundException("No posts for Uid - "+uid);
		}
		return posts;
	}
	
	@GetMapping(path = "/users/{uid}/posts/{pid}")
	public Posts getUserpost(@PathVariable Integer uid, @PathVariable Integer pid) {
		Posts post = userDaoService.getPostFromUserPostId(uid, pid);
		if(post == null) {
			throw new UserNotFoundException("No post for Pid - "+pid);
		}
		return post;
	}*/
	
	
	
	
}
