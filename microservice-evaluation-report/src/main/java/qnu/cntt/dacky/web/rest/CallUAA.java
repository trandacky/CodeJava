package qnu.cntt.dacky.web.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import qnu.cntt.dacky.client.AuthorizedFeignClient;
import qnu.cntt.dacky.client.AuthorizedUserFeignClient;
import qnu.cntt.dacky.web.rest.dto.CommunicateAccountClassDTO;

@AuthorizedUserFeignClient(name = "uaa")
public interface CallUAA {
	 @GetMapping(value = "/api/khoa/get-list-account/{uuid}")
	 List<CommunicateAccountClassDTO> getAccountClass(@PathVariable("uuid") UUID uuid);
	 @GetMapping(value = "/api/khoa/get-list-account-by-khoa")
	 List<CommunicateAccountClassDTO> getAllAccountKhoa();
	 @GetMapping(value = "/api/resource/get-class-uuid")
	 UUID getClassUUID();
	 @GetMapping(value="/api/canbolop/get-account-info-class?username={username}")
	 Object getAccountInfoClass(@PathVariable("username") String username);
}
