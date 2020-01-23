package com.rafalazar.bootcamp.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.rafalazar.bootcamp.app.document.SavingAccountVip;

public interface SavingAccountVipRepository extends ReactiveMongoRepository<SavingAccountVip, String>{
	
}
