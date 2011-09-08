package com.googlecode.openrest.v1_1;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

public class QueryStringBuilder {
	public QueryStringBuilder() {}
	
	public void append(String name, String value) {
		builder.append(first ? '?' : '&')
			.append(urlEncode(name))
			.append('=')
			.append(urlEncode(value));
		first = false;
	}
	
	public void append(String name, List<String> values) {
		if ((values == null) || (values.isEmpty())) {
			return;
		}
		
		append(name, merge(values));
	}
	
	@Override
	public String toString() {
		return builder.toString();
	}
	
	private static String merge(List<String> strings) {
		final StringBuilder builder = new StringBuilder();
		
		final Iterator<String> it = strings.iterator();
		builder.append(it.next());
		while (it.hasNext()) {
			builder.append(',').append(it.next());
		}
		
		return builder.toString();
	}
	
	private static String urlEncode(String str) {
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	private boolean first = true;
	private final StringBuilder builder = new StringBuilder();
}
