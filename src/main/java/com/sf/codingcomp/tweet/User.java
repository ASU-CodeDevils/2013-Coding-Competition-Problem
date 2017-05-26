package com.sf.codingcomp.tweet;

import java.util.ArrayList;
import java.util.List;

public class User {

	public String username;
	public Feed feed = new Feed();
	private List<User> following = new ArrayList<User>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Feed getFeed() {
		return feed;
	}

	public void setFeed(Feed feed) {
		this.feed = feed;
	}

	public void follow(User user) {
		following.add(user);
	}

	public void unfollow(User user) {
		following.remove(user);
	}

	public String toString() {
		return username;
	}

}
