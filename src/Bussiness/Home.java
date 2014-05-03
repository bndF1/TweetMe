package Bussiness;

import twitter4j.Twitter;

public class Home {
	private static Twitter twitter;
	//private OAuth oauth;

	public static void main(String args[]) throws Exception {
	//	oauth = new OAuth();
	}
	public static Twitter getTwitter() {
		return twitter;
	}
}
