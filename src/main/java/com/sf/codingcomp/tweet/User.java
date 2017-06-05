/*
 * Copyright (c) 2016 ASU CodeDevils
Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
documentation files (the "Software"), to deal in the Software without restriction, including without 
limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies 
of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all copies or substantial 
portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT 
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.sf.codingcomp.tweet;

import java.util.ArrayList;
import java.util.List;

public class User {

	private String username;
	private Feed feed = new Feed();
	private List<User> following = new ArrayList<User>();
	private int authored = 0;
	
	public User(){
		Tweeter.addUser(this);
	}

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
	
	public int getAuthored() {
		return this.authored;
	}

	public void follow(User user) {
		following.add(user);
	}

	public void unfollow(User user) {
		following.remove(user);
	}
	
	public boolean isFollowing(User user) {
		for(User follow: following)
			if(user.equals(follow))
				return true;
		return false;
	}
	
	public void addTweet(Tweet tweet) {
		if(tweet.getAuthor().equals(this));
			this.authored++;
		feed.addTweet(tweet);
	}

	public String toString() {
		return username;
	}

}
