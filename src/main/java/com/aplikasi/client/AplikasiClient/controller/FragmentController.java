package com.aplikasi.client.AplikasiClient.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FragmentController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
}
