package qnu.cntt.dacky.restmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

import qnu.cntt.dacky.domain.Account;
import qnu.cntt.dacky.domain.ClaSs;
import qnu.cntt.dacky.service.AccountService;
import qnu.cntt.dacky.service.ClassService;
import qnu.cntt.dacky.service.CourseAndDepartmentService;
import qnu.cntt.dacky.service.dto.AccountDTO;
import qnu.cntt.dacky.service.dto.ClassDTO;
import qnu.cntt.dacky.service.dto.ClassDTOUpdate;

@RestController
@RequestMapping("/api/admin")
public class ClassController {
	private final int sizePage = 10;
	@Autowired
	private ClassService classService;

//	@Autowired
//	private CourseAndDepartmentService courseAndDepartmentService;

	@GetMapping("/get-all-class")
	private List<ClaSs> getAllClass() {
		return classService.getAllClass();
	}

	@GetMapping("/get-paging-class")
	public ResponseEntity<Map<String, Object>> getPagingClass(@RequestParam(defaultValue = "0") int page) {
		try {
			Pageable paging = PageRequest.of(page, sizePage, Sort.by("createdDate").descending());
			Page<ClaSs> pageable = classService.getAllByIdAndPageable(paging);
			List<ClaSs> clasS = pageable.getContent();
			Map<String, Object> response = new HashMap<>();
			response.put("classList", clasS);
			response.put("currentPage", pageable.getNumber());
			response.put("totalItems", pageable.getTotalElements());
			response.put("totalPages", pageable.getTotalPages());
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

	@PostMapping("/add-class")
	private ClaSs addClass(@RequestBody ClassDTO classDTO) {
		return classService.addClass(classDTO);
	}

	@PutMapping("/update-class-enable")
	private ClaSs updateEnable(@RequestParam("uuid") UUID uuid, @RequestParam("enable") boolean enable) {
		return classService.updateEnable(uuid, enable);
	}
	@PutMapping("/update-class-name")
	private ClaSs updateName(@RequestBody ClassDTOUpdate dtoUpdate) {
		return classService.updateName(dtoUpdate);
	}

	@DeleteMapping("/delete-class-by-id/{id}")
	private ClaSs deleteClassById(@PathVariable UUID id) {
		return classService.deleteClassById(id);
	}

	@DeleteMapping("/delete-class-by-name/{classname}")
	private String deleteClassByName(@PathVariable String className) {
		return classService.deleteClassByName(className);
	}
}