package qnu.cntt.dacky.web.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import qnu.cntt.dacky.web.rest.dto.CommunicateAccountClassDTO;
@RestController
public class TestCall {
	
	@Autowired
	   private CallUAA callUAA;
	
	@GetMapping("/api/test/{uuid}")
	public List<CommunicateAccountClassDTO> test(@PathVariable("uuid") UUID uuid)
	{
		return callUAA.getAccountClass(uuid);
	}
	@GetMapping("/api/test2")
	public List<CommunicateAccountClassDTO> test2()
	{
		return callUAA.getAllAccountKhoa();
	}
}
