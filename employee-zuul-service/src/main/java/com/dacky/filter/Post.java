package com.dacky.filter;

import com.netflix.zuul.ZuulFilter;

public class Post extends ZuulFilter {

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	public boolean shouldFilter() {
		return true;
	}

	public Object run() {
		System.out.println("Using Post Filter");

		return null;
	}
}
