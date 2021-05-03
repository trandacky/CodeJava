package qnu.cntt.dacky.restmanager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.jhipster.web.util.HeaderUtil;
import qnu.cntt.dacky.domain.Account;
import qnu.cntt.dacky.domain.ClaSs;
import qnu.cntt.dacky.domain.CourseAndDepartment;
import qnu.cntt.dacky.security.SecurityUtils;
import qnu.cntt.dacky.service.AccountDepartmentService;
import qnu.cntt.dacky.service.AccountService;
import qnu.cntt.dacky.service.ClassService;
import qnu.cntt.dacky.service.CourseAndDepartmentService;
import qnu.cntt.dacky.service.DepartmentService;
import qnu.cntt.dacky.service.dto.AccountDTO;
import qnu.cntt.dacky.service.dto.AccountSVDTO;
import qnu.cntt.dacky.service.dto.ClassDTO;
import qnu.cntt.dacky.service.dto.ClassDTOUpdate;
import qnu.cntt.dacky.service.dto.ClassReturnDTO;
import qnu.cntt.dacky.service.dto.CourseAndDepartmentReturnDTO;
import qnu.cntt.dacky.web.rest.UserResource;
import qnu.cntt.dacky.web.rest.vm.ManagedUserVM;

@RestController
@RequestMapping("/api/khoa")
public class KhoaController {
	private final Logger log = LoggerFactory.getLogger(UserResource.class);

	@Value("${jhipster.clientApp.name}")
	private String applicationName;
	@Autowired
	private AccountService userService;
	private final int sizePage = 10;
	@Autowired
	private ClassService classService; 
	@Autowired
	private CourseAndDepartmentService courseAndDepartmentService;
	@Autowired
	private AccountDepartmentService accountDepartmentService;
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private DepartmentService departmentService;

	@GetMapping("/get-paging-class")
	public ResponseEntity<Map<String, Object>> getPagingClassByCADId(@RequestParam("uuid") UUID uuid) {
		try {
			List<ClaSs> sses = courseAndDepartmentService.getClassByCADId(uuid);
			List<ClassReturnDTO> classReturnDTOs = new ArrayList<>();
			ClassReturnDTO classReturnDTO;
			for (ClaSs ss : sses) {
				classReturnDTO = new ClassReturnDTO(ss);
				classReturnDTO.setCount(accountService.getCount(ss));
				classReturnDTOs.add(classReturnDTO);
			}
			classReturnDTOs.sort(Comparator.comparing(ClassReturnDTO::getCreatedDate));
			Map<String, Object> response = new HashMap<>();
			response.put("classList", classReturnDTOs);
			response.put("courseAndDepartment",
					new CourseAndDepartmentReturnDTO(courseAndDepartmentService.getCourseAndDepartmentById(uuid)));
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/get-class-by-id")
	private ClaSs getClassById(@RequestParam("id") UUID id) {
		return classService.getClassById(id);
	}

	@GetMapping("/get-account-by-class")
	private ResponseEntity<Map<String, Object>> getAccountByClass(@RequestParam(defaultValue = "0") int page,
			@RequestParam UUID uuid) {
		try {
			Pageable paging = PageRequest.of(page, sizePage, Sort.by("createdDate").descending());
			Page<Account> pageable = classService.getAccountByClass(paging, uuid);
			List<Account> accountList = pageable.getContent();
			List<AccountDTO> accountDTOs = new ArrayList<>();
			AccountDTO accountDTO;
			for (Account account : accountList) {
				accountDTO = new AccountDTO(account);
				accountDTOs.add(accountDTO);
			}
			Map<String, Object> response = new HashMap<>();
			response.put("accountList", accountDTOs);
			response.put("class", classService.getClassById(uuid));
			response.put("currentPage", pageable.getNumber());
			response.put("totalItems", pageable.getTotalElements());
			response.put("totalPages", pageable.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/get-class-by-cad-id")
	private ResponseEntity<Map<String, Object>> getClassByCADId(@RequestParam("uuid") UUID uuid) {
		try {
			List<ClaSs> sses = courseAndDepartmentService.getClassByCADId(uuid);
			List<ClassReturnDTO> classReturnDTOs= new ArrayList<>();
			ClassReturnDTO classReturnDTO;
			for(ClaSs ss:sses)
			{
				classReturnDTO= new ClassReturnDTO(ss);
				classReturnDTO.setCount(accountService.getCount(ss));
				classReturnDTOs.add(classReturnDTO);
			} 
			classReturnDTOs.sort(Comparator.comparing(ClassReturnDTO::getCreatedDate));
			Map<String, Object> response = new HashMap<>();
			response.put("classList", classReturnDTOs); 
			response.put("courseAndDepartment",
					new CourseAndDepartmentReturnDTO(courseAndDepartmentService.getCourseAndDepartmentById(uuid)));
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/get-cad-by-username")
	private ResponseEntity<Map<String, Object>> getCADByUsername(@RequestParam(defaultValue = "0") int page) {
		try {
			Pageable paging = PageRequest.of(page, sizePage, Sort.by("createdDate").descending());
			
			Optional<String> username = SecurityUtils.getCurrentUserLogin();
			Page<CourseAndDepartment> pageable= accountDepartmentService.getByUsername(username.get(),paging);
			
			List<CourseAndDepartmentReturnDTO> returnDTOs= new ArrayList<>();
			for(CourseAndDepartment courseAndDepartment: pageable.getContent())
			{
				returnDTOs.add(new CourseAndDepartmentReturnDTO(courseAndDepartment));
			}
			Map<String, Object> response = new HashMap<>();
			response.put("courseAndDepartments", returnDTOs);
			response.put("currentPage", pageable.getNumber());
			response.put("totalItems", pageable.getTotalElements());
			response.put("totalPages", pageable.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/get-class-paging-by-username")
	private ResponseEntity<Map<String, Object>> getClassPagingByUserName(@RequestParam(defaultValue = "0") int page) {
		try {
			Pageable paging = PageRequest.of(page, sizePage, Sort.by("createdDate").descending());
			
			Optional<String> username = SecurityUtils.getCurrentUserLogin();
			Page<ClaSs> pageable=accountDepartmentService.getCADByUsernameKhoaAndPaging(username.get(),paging);
			List<ClassReturnDTO> classReturnDTOs = new ArrayList<>();
			ClassReturnDTO classReturnDTO;
			for (ClaSs ss : pageable.getContent()) {
				classReturnDTO = new ClassReturnDTO(ss);
				classReturnDTO.setCount(accountService.getCount(ss));
				classReturnDTOs.add(classReturnDTO);
			}
			Map<String, Object> response = new HashMap<>();
			response.put("classList", classReturnDTOs);
			response.put("currentPage", pageable.getNumber());
			response.put("totalItems", pageable.getTotalElements());
			response.put("totalPages", pageable.getTotalPages()); 
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/add-class")
	private ClaSs addClass(@RequestBody ClassDTO classDTO) {
		return classService.addClass(classDTO);
	}
	@PostMapping("/create-account")
	private Account createAccount(@RequestBody AccountSVDTO accountSVDTO) {
		return accountService.createAccountOfClass(accountSVDTO);
	}

	@PutMapping("/update-class-enable")
	private ClaSs updateClassEnable(@RequestParam("uuid") UUID uuid, @RequestParam("enable") boolean enable) {
		return classService.updateEnable(uuid, enable);
	}
	
	@PutMapping("/update-course-and-department-enable")
	private CourseAndDepartment updateCADEnable(@RequestParam("uuid") UUID uuid, @RequestParam("enable") boolean enable) {
		return courseAndDepartmentService.updateEnable(uuid, enable);
	}

	@PutMapping("/update-class-name")
	private ClaSs updateName(@RequestBody ClassDTOUpdate dtoUpdate) {
		return classService.updateName(dtoUpdate);
	}

	@DeleteMapping("/delete-class-by-id/{id}")
	private ClaSs deleteClassById(@PathVariable UUID id) {
		return classService.deleteClassById(id);
	}
	

	@PutMapping("/account")
	public ResponseEntity<Void> updateEnable(@RequestParam("username") String username,
			@RequestParam("activated") boolean activated) {
		log.debug("REST request to delete User: {}", username);
		userService.updateActivated(username, activated);
		return ResponseEntity.noContent().headers(
				HeaderUtil.createAlert(applicationName, "A user is deleted with identifier " + username, username))
				.build();
	}

	@DeleteMapping("/account")
	public ResponseEntity<Void> deletedRole(@RequestParam("username") String username) {
		log.debug("REST request to delete User: {}", username);
		userService.deleteUserKhoa(username);
		return ResponseEntity.noContent().headers(
				HeaderUtil.createAlert(applicationName, "A user is deleted with identifier " + username, username))
				.build();
	}
	@DeleteMapping("/delete-course-and-department/{uuid}")
	private CourseAndDepartment deletedCADById(@PathVariable("uuid") UUID uuid) {
		return courseAndDepartmentService.deletedById(uuid);
	}
}
