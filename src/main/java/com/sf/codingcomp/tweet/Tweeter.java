package com.sf.codingcomp.tweet;

import java.util.*;

public class Tweeter {

	public void tweet(String text, User user) throws TweetTooLongException {
		if(text.length()>140) throw new TweetTooLongException();
		Tweet thisTweet = new Tweet(text, user);
		Feed tempFeed = user.getFeed();
		List<Tweet> tempTweets = tempFeed.getTweets();
		tempTweets.add(thisTweet);
		tempFeed.setTweets(tempTweets);
		user.setFeed(tempFeed);
		
		// TODO implement me
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
			String s = tweet.getText();
			for(int i = 0; i < s.length(); i++){
				if(s.charAt(i)=='@'){
					int temp = i;
					do{
						i++;
					}while(s.charAt(i)!=' ' || i == s.length());
					usernames.add(s.substring(temp, i));
				}
			}
		}
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
