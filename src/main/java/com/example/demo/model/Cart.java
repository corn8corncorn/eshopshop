package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 購物車實體類別
 * 存放客戶的購物車資訊，一個客戶擁有一個購物車
 */
@Entity
@Table(name = "carts")
public class Cart {
	
	private static final Logger logger = LoggerFactory.getLogger(Cart.class);
	
	/**
	 * 購物車ID（主鍵）
	 * 自動遞增
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 所屬客戶（關聯到 Customer）
	 * 一對一關係，一個客戶擁有一個購物車
	 */
	@OneToOne
	@JoinColumn(name = "customer_id", nullable = false, unique = true)
	private Customer customer;
	
	/**
	 * 建立時間
	 * 購物車建立的時間
	 */
	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	/**
	 * 更新時間
	 * 購物車最後更新的時間
	 */
	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	/**
	 * 預設建構子
	 */
	public Cart() {
		logger.info("建立新的 Cart 物件");
	}
	
	/**
	 * 建構子（帶客戶參數）
	 * 
	 * @param customer 所屬客戶
	 */
	public Cart(Customer customer) {
		logger.info("建立 Cart 物件 - customer: {}", 
					customer != null ? customer.getId() : null);
		this.customer = customer;
	}
	
	/**
	 * 取得購物車ID
	 * 
	 * @return 購物車ID
	 */
	public Long getId() {
		logger.debug("取得 Cart ID: {}", id);
		return id;
	}
	
	/**
	 * 設定購物車ID
	 * 
	 * @param id 購物車ID
	 */
	public void setId(Long id) {
		logger.debug("設定 Cart ID: {}", id);
		this.id = id;
	}
	
	/**
	 * 取得所屬客戶
	 * 
	 * @return 所屬客戶
	 */
	public Customer getCustomer() {
		logger.debug("取得 customer (Cart ID: {})", id);
		return customer;
	}
	
	/**
	 * 設定所屬客戶
	 * 
	 * @param customer 所屬客戶
	 */
	public void setCustomer(Customer customer) {
		logger.info("設定 customer - Customer ID: {} (Cart ID: {})", 
					customer != null ? customer.getId() : null, id);
		this.customer = customer;
	}
	
	/**
	 * 取得建立時間
	 * 
	 * @return 建立時間
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	/**
	 * 設定建立時間
	 * 
	 * @param createdAt 建立時間
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		logger.debug("設定 createdAt: {}", createdAt);
		this.createdAt = createdAt;
	}
	
	/**
	 * 取得更新時間
	 * 
	 * @return 更新時間
	 */
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	
	/**
	 * 設定更新時間
	 * 
	 * @param updatedAt 更新時間
	 */
	public void setUpdatedAt(LocalDateTime updatedAt) {
		logger.debug("設定 updatedAt: {}", updatedAt);
		this.updatedAt = updatedAt;
	}
	
	/**
	 * 取得購物車的擁有者（使用者名稱）
	 * 
	 * @return 使用者名稱，如果客戶不存在則返回空字串
	 */
	public String getOwnerName() {
		String ownerName = "";
		if (customer != null && customer.getUser() != null) {
			ownerName = customer.getUser().getUsername();
		}
		logger.debug("取得購物車擁有者: {} (Cart ID: {})", ownerName, id);
		return ownerName;
	}
	
	@Override
	public String toString() {
		return "Cart{" +
				"id=" + id +
				", customer=" + (customer != null ? customer.getId() : null) +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				'}';
	}
}

