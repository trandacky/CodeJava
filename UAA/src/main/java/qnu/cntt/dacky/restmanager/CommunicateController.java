package qnu.cntt.dacky.restmanager;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@FeignClient(name = "uaa")
@RequestMapping("/api/khoa")

public class CommunicateController {
	@GetMapping("/hello")
	public String hello()
	{
		return "hello";
	}

}
