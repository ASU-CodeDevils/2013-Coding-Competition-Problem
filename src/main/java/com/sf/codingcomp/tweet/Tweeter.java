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

import java.util.*;

public class Tweeter {
	private static List<User> users = new ArrayList<User>();

	public void tweet(String text, User user) throws TweetTooLongException {
		if(text.length()>140) throw new TweetTooLongException();
		// add tweet to the users feed
		Tweet thisTweet = new Tweet(text, user);
		Feed tempFeed = user.getFeed();
		List<Tweet> tempTweets = tempFeed.getTweets();
		tempTweets.add(thisTweet);
		tempFeed.setTweets(tempTweets);
		user.setFeed(tempFeed);
		
		// add tweet to the feed of all those mentioned in the tweet (@username)
		for(int i = 0; i < text.length(); i++){
			if(text.charAt(i)=='@'){
				int temp = i+1;
				do{
					i++;
				}while(text.charAt(i)!=' ' || i == text.length());
				String mention = text.substring(temp, i);
				for(User mentioned : users){
					if(mentioned.getUsername().equals(mention) && !mentioned.getUsername().equals(user.getUsername())){
						System.out.println("New tweet at: " + mentioned.getUsername());
						tempFeed = mentioned.getFeed();
						tempTweets = tempFeed.getTweets();
						tempTweets.add(thisTweet);
						tempFeed.setTweets(tempTweets);
						mentioned.setFeed(tempFeed);
					}
				}
			}
		}
		
	}
	public static void addUser(User user){
		users.add(user);
	}

	/**
	 * This method finds the usernames of all other users that are mentioned in
	 * the user's feed, sorted alphabetically, case-insensitive.
	 * 
	 * @param user
	 * @return
	 */
	public List<String> findMentions(User user) {
		List<String> usernames = new ArrayList<String>();
		Feed tempFeed = user.getFeed();
		List<Tweet> tempTweets = tempFeed.getTweets();
		for(Tweet tweet:tempTweets){
			// uncomment below to not count mentions in feed by other authors
			/*if(!tweet.getAuthor().equals(user.getUsername()))
				continue;*/
			String s = tweet.getText();
			for(int i = 0; i < s.length(); i++){
				if(s.charAt(i)=='@'){
					int temp = i;
					do{
						i++;
					}while(s.charAt(i)!=' ' || i == s.length());
					String mention = s.substring(temp, i);
					//System.out.println(mention);
					if(!usernames.contains(mention) && !mention.equals("@" + user.getUsername())) {
						usernames.add(mention);
						//System.out.println(usernames.toString());
					}
				}
			}
		}
		Collections.sort(usernames);
		return usernames;
	}

	/**
	 * This method finds the hashtags that appear most often in the user's feed,
	 * sorted by number of occurrences.
	 * 
	 * @param user
	 * @param howMany
	 * @return
	 */
	public List<Hashtag> findMostPopularHashtags(User user, int howMany) {
		// TODO implement me
		return null;
	}

	/**
	 * This method finds the most recent tweets authored by the user.
	 * 
	 * @param user
	 * @param howMany
	 *            - number of results
	 * @return
	 */
	public List<Tweet> findMostRecentTweets(User user, int howMany) {
		// TODO implement me
		return null;
	}

	/**
	 * This method finds the follower of the user with the most authored tweets.
	 * Returns null if the user has no followers
	 * 
	 * @param user
	 * @return
	 */
	public User findMostActiveFollower(User user) {
		// TODO implement me
		return null;
	}
}
