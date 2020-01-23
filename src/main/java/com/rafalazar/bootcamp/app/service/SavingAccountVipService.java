package com.rafalazar.bootcamp.app.service;

import com.rafalazar.bootcamp.app.document.SavingAccountVip;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SavingAccountVipService {

	public Flux<SavingAccountVip> findAll();
	
	public Mono<SavingAccountVip> findById(String id);
	
	public Mono<SavingAccountVip> save(SavingAccountVip sav);
	
	public Mono <SavingAccountVip> update(SavingAccountVip sav,String id);
	
	public Mono<Void> delete(SavingAccountVip sav);
}
