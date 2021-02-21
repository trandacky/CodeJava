package qnu.cntt.dacky.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qnu.cntt.dacky.domain.Authority;
import qnu.cntt.dacky.service.AuthorityService;
import qnu.cntt.dacky.service.dto.AuthorityDTO;

@RestController
@RequestMapping("/api/auth")
public class AuthorityResource {
	private final Logger log = LoggerFactory.getLogger(UserResource.class);
	@Autowired
	private AuthorityService authorityService;
	
	@GetMapping("/get-all-authority")
	public List<Authority> getAllAuthority()
	{
		return authorityService.getAllAuthority();
	}
	@GetMapping("/get-authority")
	public Authority getAuthority(String authorities)
	{
		return authorityService.getAuthority(authorities).get();
	}
	@PostMapping("/create-authority")
	public void createAuthorites(AuthorityDTO authorityDTO)
	{
		authorityService.createAuthority(authorityDTO);
	}
	@DeleteMapping("/delete-authority")
	public void deleteAuthority(String authorities)
	{
		authorityService.deleteAuthority(authorities);
	}
}
