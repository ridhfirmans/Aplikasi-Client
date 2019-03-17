package com.aplikasi.client.AplikasiClient.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.aplikasi.client.AplikasiClient.model.Siswa;
import com.aplikasi.client.AplikasiClient.service.RestSiswa;

@Controller
@RequestMapping("/siswa")
public class SiswaController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SiswaController.class);
	
	@Autowired
	private RestSiswa restSiswa;
	
	@GetMapping("/list-siswa")
	public ModelMap daftarSiswa(){
		return new ModelMap().addAttribute("daftarSiswa", restSiswa.dataSiswa());
	}
	
	@GetMapping("/delete")
	public String deleteSiswa(@RequestParam("id") String id){
		restSiswa.deleteSiswa(id);
		return "redirect:list-siswa";
	}
	
	@GetMapping("/form-siswa")
	public ModelMap findById(@RequestParam(name="id", required=false) String id){
		Siswa siswa = new Siswa();
		if(id != null){
			siswa = restSiswa.findSiswaById(id);
			if(siswa == null){
				siswa = new Siswa();
			}
		}
		siswa = restSiswa.findSiswaById(id);
		return new ModelMap().addAttribute("siswa", siswa);
	}
	
	@PostMapping("/form-siswa")
	public String prosesForm(@ModelAttribute @Valid Siswa siswa, BindingResult errors, SessionStatus sessionStatus){
		LOGGER.info("Menyimpan data Siswa");
		
		LOGGER.info("Id : "+siswa.getId());
		if(errors.hasErrors()){
			return "siswa/form-siswa";
		}

		restSiswa.saveSiswa(siswa);
		sessionStatus.setComplete();
		return "redirect:list-siswa";
	}
	
	

}
