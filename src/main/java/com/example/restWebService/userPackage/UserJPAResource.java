package com.example.restWebService.userPackage;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class UserJPAResource {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	

	// Retrieve All users
	@GetMapping(path = "/jpa/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// retrieve one user
	@GetMapping(path = "/jpa/users/{id}")
	public User getUserWithId(@PathVariable Integer id) {
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent())
			throw new UserNotFoundException("Id:: "+id);
		
		//also want to return all users link with this
		//HATEOS can be used
	
		
		return user.get();
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);

		// We have to send the status and the Url to the Client

		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping(path = "/jpa/users/{id}")
	public void deleteUserWithId(@PathVariable Integer id) {
		userRepository.deleteById(id);
	}
	
	@GetMapping(path = "/jpa/users/{uid}/posts")
	public List<Posts> getallPostsForUser(@PathVariable Integer uid) {
		
		Optional<User> userop = userRepository.findById(uid);
		if(!userop.isPresent()) {
			throw new UserNotFoundException("No posts for Uid - "+uid);
		}
		
		return userop.get().getPost();
	}
	
	@PostMapping("/jpa/users/{uid}/posts")
	public ResponseEntity<Object> createPost(@PathVariable Integer uid, @RequestBody Posts post) {
		
		Optional<User> userop = userRepository.findById(uid);
		if(!userop.isPresent()) {
			throw new UserNotFoundException("No posts for Uid - "+uid);
		}
		
		User user = userop.get();
		post.setUser(user);
		
		Posts savedPost = postRepository.save(post);

		
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(savedPost.getPid()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@GetMapping(path = "/jpa/posts/{pid}")
	public Posts getUserpost(@PathVariable Integer pid) {
		
		Optional<Posts> postop = postRepository.findById(pid);
		
		if(!postop.isPresent())
			throw new UserNotFoundException("No posts for pid - "+pid);
		
		return postop.get();
	}
	
	
	
	
}
