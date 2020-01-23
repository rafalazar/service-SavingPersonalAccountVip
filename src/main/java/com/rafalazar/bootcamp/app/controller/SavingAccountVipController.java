package com.rafalazar.bootcamp.app.controller;

import java.net.URI;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafalazar.bootcamp.app.document.SavingAccountVip;
import com.rafalazar.bootcamp.app.service.SavingAccountVipService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/savingAccountVip")
public class SavingAccountVipController {
	
	@Autowired
	private SavingAccountVipService service;
	
	@GetMapping("/findAll")
	Flux<SavingAccountVip> findAll() {
		return service.findAll();
	}
	
	@GetMapping("/findById/{id}")
	Mono<SavingAccountVip> findById(@PathVariable("id") String id) {
		return service.findById(id);
	}
	
	@PostMapping("/create")
	public Mono<ResponseEntity<SavingAccountVip>> create(@RequestBody SavingAccountVip sav) {
		if(sav.getCreateAt() == null) {
			sav.setCreateAt(new Date());
		}
		
		if(sav.getUpdateAt() == null) {
			sav.setUpdateAt(new Date());
		}
		
		return service.save(sav).map(s -> ResponseEntity.created(URI.create("/savingAccountVip/".concat(s.getId())))
				.contentType(MediaType.APPLICATION_JSON).body(s));
	}
	
	//Esta es la forma correcta de eliminar un producto.F!
	@DeleteMapping("/deleteById/{id}")
	Mono<ResponseEntity<Void>> deleteById(@PathVariable String id) {
		return service.findById(id)
				.flatMap(p -> {
					return service.delete(p)
							.then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
				}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}
	
	//Forma correcta de actualizar ---- F!
	@PutMapping("/update/{id}")
	Mono<ResponseEntity<SavingAccountVip>> update(@PathVariable String id, @RequestBody SavingAccountVip sav) {
		return service.update(sav, id)
				.map(s -> ResponseEntity.created(URI.create("/savingAccountVip".concat(s.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(s))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
}
