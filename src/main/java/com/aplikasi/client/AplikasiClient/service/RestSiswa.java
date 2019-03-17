package com.aplikasi.client.AplikasiClient.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aplikasi.client.AplikasiClient.controller.SiswaController;
import com.aplikasi.client.AplikasiClient.model.Siswa;

@Service
public class RestSiswa {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RestSiswa.class);

	@Value("${backendUrlSiswa}")
	private String backendUrlSiswa;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	private String pathSiswa = "/siswa";
	
	public List<Siswa> dataSiswa(){
		ResponseEntity<Siswa[]> result = restTemplate.getForEntity(backendUrlSiswa+pathSiswa, Siswa[].class);
		return Arrays.asList(result.getBody());
	}
	
	public void deleteSiswa(String id){
		restTemplate.delete(backendUrlSiswa+pathSiswa+"/"+id);
	}
	
	public Siswa findSiswaById(String id){
		return restTemplate.getForObject(backendUrlSiswa+pathSiswa+"/"+id, Siswa.class);
	}

	public void saveSiswa(Siswa siswa) {
		LOGGER.info("id siswanya >> "+siswa.getId());
		if(siswa.getId() == null || "".equals(siswa.getId().trim())){
			restTemplate.postForLocation(backendUrlSiswa+pathSiswa, siswa);
		}else{
			restTemplate.put(backendUrlSiswa+pathSiswa+"/"+siswa.getId(), siswa);
		}
		
	}
	
}
