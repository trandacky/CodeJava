package qnu.cntt.dacky.restmanager;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qnu.cntt.dacky.domain.Account;
import qnu.cntt.dacky.security.SecurityUtils;
import qnu.cntt.dacky.service.AccountService;
import qnu.cntt.dacky.service.dto.AccountInfoDTO;

@RestController
@RequestMapping("/api/resource")
public class AuthenResourceController {
	@Autowired
	private AccountService accountService;
	@GetMapping("/info")
	public AccountInfoDTO getInfo()
	{
		String username=SecurityUtils.getCurrentUserLogin().get();
		return new AccountInfoDTO((accountService.getAccountByUsername(username)));
	}
	
	@GetMapping("/get-class-uuid")
	public UUID getClassUUID()
	{
		String username=SecurityUtils.getCurrentUserLogin().get();
		return accountService.getAccountByUsername(username).getClass1().getUUID();
	}
}
