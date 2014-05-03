package Presentation;

import java.io.File;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.media.ImageUpload;

public abstract class PublicarTweet {
	 abstract void uploadTweet(String status, Twitter twitter) throws TwitterException;
	 abstract void uploadTweetWithImg(Twitter twitter, String status, ImageUpload o, File f) throws TwitterException;
	 public PublicarTweet(Twitter twitter, String status) throws TwitterException{
		 uploadTweet(status, twitter);
	 }
	 public PublicarTweet(Twitter twitter, String status, ImageUpload o, File f) throws TwitterException{
		 uploadTweetWithImg(twitter, status, o,f);
	 }
}
