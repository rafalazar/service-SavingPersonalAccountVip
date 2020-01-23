package com.rafalazar.bootcamp.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafalazar.bootcamp.app.document.SavingAccountVip;
import com.rafalazar.bootcamp.app.repository.SavingAccountVipRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SavingAccountVipServiceImpl implements SavingAccountVipService{

	@Autowired
	private SavingAccountVipRepository repo;
	
	@Override
	public Flux<SavingAccountVip> findAll() {
		return repo.findAll();
	}

	@Override
	public Mono<SavingAccountVip> findById(String id) {
		return repo.findById(id);
	}

	@Override
	public Mono<SavingAccountVip> save(SavingAccountVip sav) {
		return repo.save(sav);
	}

	@Override
	public Mono<SavingAccountVip> update(SavingAccountVip sav, String id) {
		return repo.findById(id)
				.flatMap(s ->{
					s.setNumAccount(sav.getNumAccount());
					s.setNameBank(sav.getNameBank());
					s.setTotal(sav.getTotal());
					s.setStatus(sav.getStatus());
					
					return repo.save(s);
				});
	}

	@Override
	public Mono<Void> delete(SavingAccountVip sav) {
		return repo.delete(sav);
	}

}
