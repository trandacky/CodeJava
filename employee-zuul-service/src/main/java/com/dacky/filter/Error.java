package com.dacky.filter;

import com.netflix.zuul.ZuulFilter;

public class Error extends ZuulFilter {

	@Override
	public String filterType() {
		return "error";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	public boolean shouldFilter() {
		return true;
	}
	public Object run() {
		System.out.println("Using Route Filter");

		return null;
	}
}