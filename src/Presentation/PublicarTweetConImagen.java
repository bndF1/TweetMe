package Presentation;

import java.io.File;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.media.ImageUpload;

public class PublicarTweetConImagen extends PublicarTweet {

	public PublicarTweetConImagen(Twitter twitter, String status,
			ImageUpload o, File fileName) throws TwitterException {
		super(twitter, status, o, fileName);
		// TODO Auto-generated constructor stub
	}

	@Override
	void uploadTweet(String status, Twitter twitter) throws TwitterException {
		// TODO Auto-generated method stub
		// Do nothing
	}

	@Override
	void uploadTweetWithImg(Twitter twitter, String status, ImageUpload o,
			File f) throws TwitterException {
		String name = f.getAbsolutePath();
		o.upload(new File(name), status);
		
	}

}