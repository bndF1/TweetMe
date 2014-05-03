package Presentation;

import java.io.File;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.media.ImageUpload;

public class PublicarTweetSinImagen extends PublicarTweet {

	public PublicarTweetSinImagen(Twitter twitter, String status)
			throws TwitterException {
		super(twitter, status);
		// TODO Auto-generated constructor stub
	}

	@Override
	void uploadTweet(String status, Twitter twitter) throws TwitterException {
		twitter.updateStatus(status);
	}

	@Override
	void uploadTweetWithImg(Twitter twitter, String status, ImageUpload o, File f) {
		// TODO Auto-generated method stub
		//do nothing
	}
}
