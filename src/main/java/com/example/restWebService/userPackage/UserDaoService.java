package com.example.restWebService.userPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<User>();
	
	public int UserCount = 3;
	
	static {
		users.add(new User(1, "Komal", new Date()));
//				new ArrayList<Posts>(Arrays.asList(new Posts(1, "1st Post", "Nice to post"),
//						new Posts(2, "2nd Post", "Nice to 2 post")))));
		users.add(new User(2, "Kapil", new Date()));
//				new ArrayList<Posts>(Arrays.asList(new Posts(3, "1st Post", "Nice to post"),
//						new Posts(4, "2nd Post", "Nice to 2 post")))));
		users.add(new User(3, "Kajal", new Date()));
//				new ArrayList<Posts>(Arrays.asList(new Posts(5, "1st Post", "Nice to post"),
//				new Posts(6, "2nd Post", "Nice to 2 post")))));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User save(User user) {
		if(user.getId() == null) {
			user.setId(++UserCount);
		}
		users.add(user);
		return user;
	}
	
	public User finsOne(int id) {
		for(User user : users ) {
			if(user.getId() == id)
				return user;
		}
		return null;
	}
	
	public User deleteById(Integer id) {
		Iterator<User> itr = users.iterator();
		while(itr.hasNext()) {
			User user = itr.next();
			if(user.getId() == id) {
				itr.remove();
				return user;
			}
		}
		return null;
	}
	
	/*public List<Posts> getPostsForUser(Integer uid) {
		for(User user: users) {
			if(user.getId() == uid) {
				return user.getPost();
			}
		}
		return null;
	}
	
	public Posts getPostFromUserPostId(Integer uid, Integer pid) {
		for(User user : users) {
			if(user.getId() == uid) {
				for(Posts post : user.getPost()) {
					if(post.getPid() == pid) {
						return post;
					}
				}
			}
		}
		return null;
	} */

}
