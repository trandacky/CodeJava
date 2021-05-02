package qnu.cntt.dacky.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class TestCall {
	
	@Autowired
	   private CallUAA callUAA;
	
	@GetMapping("/api/test")
	public String test()
	{
		return callUAA.gethello();
	}
}
