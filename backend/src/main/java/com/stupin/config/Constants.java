package com.stupin.config;

public final class Constants {

	public static final class Message {
		public static final String NOT_FOUND_MESSAGE = "User with given username not found";
		public static final String
			NOT_ACCEPTABLE_MESSAGE = "Unsupported 'Media type'. Must accept 'application/json'";

		private Message() {
		}
	}

	public static final class URL {
		public static final String API_GITHUB_COM_URL = "https://api.github.com";

		private URL() {
		}
	}
}
