package org.hbhk.aili.route.http.context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.camel.http.common.HttpConsumer;
import org.apache.camel.support.RestConsumerContextPathMatcher;

public class DynRestConsumerContextPathMatcher {
	private DynRestConsumerContextPathMatcher() {

	}

	/**
	 * Does the incoming request match the given consumer path (ignore case)
	 * 
	 * @param requestPath
	 *            the incoming request context path
	 * @param consumerPath
	 *            a consumer path
	 * @param matchOnUriPrefix
	 *            whether to use the matchOnPrefix option
	 * @return <tt>true</tt> if matched, <tt>false</tt> otherwise
	 */
	public static boolean matchPath(String requestPath, String consumerPath,
			boolean matchOnUriPrefix) {
		// deal with null parameters
		if (requestPath == null && consumerPath == null) {
			return true;
		}
		if (requestPath == null || consumerPath == null) {
			return false;
		}

		String p1 = requestPath.toLowerCase(Locale.ENGLISH);
		String p2 = consumerPath.toLowerCase(Locale.ENGLISH);

		if (p1.equals(p2)) {
			return true;
		}

		if (matchOnUriPrefix && p1.startsWith(p2)) {
			return true;
		}

		return false;
	}

	/**
	 * Finds the best matching of the list of consumer paths that should service
	 * the incoming request.
	 * 
	 * @param requestMethod
	 *            the incoming request HTTP method
	 * @param requestPath
	 *            the incoming request context path
	 * @param consumerPaths
	 *            the list of consumer context path details
	 * @return the best matched consumer, or <tt>null</tt> if none could be
	 *         determined.
	 */
	public static RestConsumerContextPathMatcher.ConsumerPath<HttpConsumer> matchBestPath(
			String requestMethod, String requestPath,
			List<RestConsumerContextPathMatcher.ConsumerPath<HttpConsumer>> consumerPaths) {
		RestConsumerContextPathMatcher.ConsumerPath<HttpConsumer> answer = null;

		List<RestConsumerContextPathMatcher.ConsumerPath<HttpConsumer>> candidates = new ArrayList<RestConsumerContextPathMatcher.ConsumerPath<HttpConsumer>>();

		// first match by http method
		for (RestConsumerContextPathMatcher.ConsumerPath<HttpConsumer> entry : consumerPaths) {
			if (matchRestMethod(requestMethod, entry.getRestrictMethod())) {
				candidates.add(entry);
			}
		}

		// then see if we got a direct match
		Iterator<RestConsumerContextPathMatcher.ConsumerPath<HttpConsumer>> it = candidates
				.iterator();
		while (it.hasNext()) {
			RestConsumerContextPathMatcher.ConsumerPath<HttpConsumer> consumer = it.next();
			if (matchRestPath(requestPath, consumer.getConsumerPath(), false)) {
				answer = consumer;
				break;
			}
		}

		// then match by wildcard path
		if (answer == null) {
			it = candidates.iterator();
			while (it.hasNext()) {
				RestConsumerContextPathMatcher.ConsumerPath<HttpConsumer> consumer = it
						.next();
				// filter non matching paths
				if (!matchRestPath(requestPath, consumer.getConsumerPath(),
						true)) {
					it.remove();
				}
			}

			// if there is multiple candidates with wildcards then pick anyone
			// with the least number of wildcards
			int bestWildcard = Integer.MAX_VALUE;
			RestConsumerContextPathMatcher.ConsumerPath<HttpConsumer> best = null;
			if (candidates.size() > 1) {
				it = candidates.iterator();
				while (it.hasNext()) {
					RestConsumerContextPathMatcher.ConsumerPath<HttpConsumer> entry = it
							.next();
					int wildcards = countWildcards(entry.getConsumerPath());
					if (wildcards > 0) {
						if (best == null || wildcards < bestWildcard) {
							best = entry;
							bestWildcard = wildcards;
						}
					}
				}

				if (best != null) {
					// pick the best among the wildcards
					answer = best;
				}
			}

			// if there is one left then its our answer
			if (answer == null && candidates.size() == 1) {
				answer = candidates.get(0);
			}
		}

		return answer;
	}

	/**
	 * Matches the given request HTTP method with the configured HTTP method of
	 * the consumer
	 * 
	 * @param method
	 *            the request HTTP method
	 * @param restrict
	 *            the consumer configured HTTP restrict method
	 * @return <tt>true</tt> if matched, <tt>false</tt> otherwise
	 */
	private static boolean matchRestMethod(String method, String restrict) {
		if (restrict == null) {
			return true;
		}

		return restrict.toLowerCase(Locale.ENGLISH).contains(
				method.toLowerCase(Locale.ENGLISH));
	}

	/**
	 * Matches the given request path with the configured consumer path
	 * 
	 * @param requestPath
	 *            the request path
	 * @param consumerPath
	 *            the consumer path which may use { } tokens
	 * @return <tt>true</tt> if matched, <tt>false</tt> otherwise
	 */
	private static boolean matchRestPath(String requestPath,
			String consumerPath, boolean wildcard) {
		// remove starting/ending slashes
		if (requestPath.startsWith("/")) {
			requestPath = requestPath.substring(1);
		}
		if (requestPath.endsWith("/")) {
			requestPath = requestPath.substring(0, requestPath.length() - 1);
		}
		// remove starting/ending slashes
		if (consumerPath.startsWith("/")) {
			consumerPath = consumerPath.substring(1);
		}
		if (consumerPath.endsWith("/")) {
			consumerPath = consumerPath.substring(0, consumerPath.length() - 1);
		}
		//TODO 
		if (requestPath.startsWith(consumerPath)) {
			return true;
		}
		// split using single char / is optimized in the jdk
		String[] requestPaths = requestPath.split("/");
		String[] consumerPaths = consumerPath.split("/");

		// must be same number of path's
		 if (requestPaths.length != consumerPaths.length) {
			 return false;
		 }

		for (int i = 0; i < requestPaths.length; i++) {
			String p1 = requestPaths[i];
			String p2 = consumerPaths[i];

			if (wildcard && p2.startsWith("{") && p2.endsWith("}")) {
				// always matches
				continue;
			}
			if (!matchPath(p1, p2, true)) {
				return false;
			}
		}

		// assume matching
		return true;
	}

	/**
	 * Counts the number of wildcards in the path
	 * 
	 * @param consumerPath
	 *            the consumer path which may use { } tokens
	 * @return number of wildcards, or <tt>0</tt> if no wildcards
	 */
	private static int countWildcards(String consumerPath) {
		int wildcards = 0;

		// remove starting/ending slashes
		if (consumerPath.startsWith("/")) {
			consumerPath = consumerPath.substring(1);
		}
		if (consumerPath.endsWith("/")) {
			consumerPath = consumerPath.substring(0, consumerPath.length() - 1);
		}

		String[] consumerPaths = consumerPath.split("/");
		for (String p2 : consumerPaths) {
			if (p2.startsWith("{") && p2.endsWith("}")) {
				wildcards++;
			}
		}

		return wildcards;
	}

	public static boolean isMatch(char[] s, char[] t) {
		// cut off the tail of non-* characters
		int i = s.length - 1, j = t.length - 1;
		while (i > -1 && j > -1 && s[i] == t[j]) {
			i--;
			j--;
		}

		int endS = i + 1, endT = j + 1;
		i = j = 0;
		while (i < endS && j < endT && s[i] == t[j]) {
			i++;
			j++;
		}
		if (j == endT) {// t has no *
			if (i == endS) {// t equals to s
				return true;
			} else {
				return false;// s is longer than t, unequal
			}
		}
		if (t[j] != '*') {// not match for a non-* character
			return false;
		}
		// the first * is found

		int subStringStart = j;
		int subStringEnd = j;
		while (j < endT) {
			while (j < endT && t[j] == '*') {
				j++;
			}
			if (j == endT) {
				return true;
			}
			// get the first non-* char
			subStringStart = j;

			while (j < endT && t[j] != '*') {
				j++;
			}
			// get the last non-* char
			subStringEnd = j - 1;

			// match it in s using normal string match algorithm
			i = isNormalMatch(s, i, endS - 1, t, subStringStart, subStringEnd);
			if (i == -1) {
				return false;
			} else if (i == endS) {
				return true;
			}
		}

		// t ends as a non-* character but s not ends yet
		return false;
	}

	private static int isNormalMatch(char[] text, int textStart, int textEnd,
			char[] pattern, int patternStart, int patternEnd) {
		if (textStart > textEnd || patternStart > patternEnd) {
			return -1;
		}
		char[] s1 = Arrays.copyOfRange(text, textStart, textEnd + 1);
		char[] s2 = Arrays.copyOfRange(pattern, patternStart, patternEnd + 1);
		int[] next = new int[patternEnd - patternStart + 1];
		caculateNext(s2, next);
		int i = isMatch(s2, s1, next);
		if (i != -1) {
			i = i + textStart + 1;
		}
		return i;
	}

	private static void caculateNext(char[] pattern, int[] next) {
		next[0] = -1;

		int i = 0, j = -1;
		while (i < pattern.length - 1) {
			if (j == -1 || pattern[i] == pattern[j]) {
				i++;
				j++;
				next[i] = j;
			} else {
				j = next[j];
			}
		}
	}

	private static int isMatch(char[] patternStamp, char[] textStamp, int[] next) {
		int i = 0, j = 0;
		while (j < patternStamp.length && i < textStamp.length) {
			if (j == -1 || patternStamp[j] == textStamp[i]) {
				i++;
				j++;
			} else {
				j = next[j];
			}
		}

		if (j == patternStamp.length) {
			return i - j;
		} else {
			return -1;
		}
	}
	
	public static void main(String[] args) {
	 boolean f =isMatch("/asd/asd/as".toCharArray(), "/asd/*/*".toCharArray());
	 
	 System.out.println(f);
	}
}
