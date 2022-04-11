package com.willams.twowaytls;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HelloController {

	@GetMapping(value = "/resource/{id}")
	public ResponseEntity<String> hello(@PathVariable String id) {
		System.out.println("Recebido request do cliente: " + id);
		return ResponseEntity.ok("Hello, " + id);
	}
}
