package com.rafalazar.bootcamp.app.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="savingAccountVip_clients")
public class SavingAccountVip {
	
	@Id
	private String id;
	private String numAccount;
	private String nameBank;
	private Double total;
	private String status;
	private Date createAt;
	private Date updateAt;
	
	public SavingAccountVip() {
		
	}
	
	public SavingAccountVip(String numAccount, String nameBank, Double total, String status) {
		this.numAccount = numAccount;
		this.nameBank = nameBank;
		this.total = total;
		this.status = status;
	}

}
