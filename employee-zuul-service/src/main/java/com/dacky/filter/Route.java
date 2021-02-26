package com.dacky.filter;

import com.netflix.zuul.ZuulFilter;

public class Route extends ZuulFilter {

	@Override
	public String filterType() {
		return "route";
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
