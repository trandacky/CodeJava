package qnu.cntt.dacky.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import qnu.cntt.dacky.client.AuthorizedFeignClient;
import qnu.cntt.dacky.client.AuthorizedUserFeignClient;

@AuthorizedUserFeignClient(name = "uaa")
public interface CallUAA {
	 @GetMapping(value = "/api/khoa/hello")
	 String gethello();
}
