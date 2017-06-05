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
	private List<Hashtag> hashtags = new ArrayList<Hashtag>();
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
		String text = tweet.getText();
		for(int i = 0; i < text.length(); i++){
			if(text.charAt(i)=='#'){
				int temp = i;
				do{
					i++;
				}while(i < text.length() && text.charAt(i)!=' ');
				String substring = text.substring(temp, i);
				this.addHashtag(substring);
			}
		}
		if(tweet.getAuthor().equals(this));
			this.authored++;
		feed.addTweet(tweet);
	}
	
	public void addHashtag(String text) {
		for(Hashtag hashtag: hashtags) {
			if(hashtag.getText().equals(text)) {
				hashtag.setOccurrences(hashtag.getOccurrences()+1);
				return;
			}
		}
		hashtags.add(new Hashtag(text, 1));
	}
	
	public List<Hashtag> getHashtags(){
		List<Hashtag> newHashtags = new ArrayList<Hashtag>();
		for(Hashtag hashtag: hashtags){
			newHashtags.add(hashtag);
		}
		return newHashtags;
	}

	public String toString() {
		return username;
	}

}
