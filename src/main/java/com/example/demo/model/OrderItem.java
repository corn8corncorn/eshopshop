package com.example.demo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 訂單明細實體類別
 * 存放訂單中的商品明細資訊，包含商品、數量、價格等
 */
@Entity
@Table(name = "order_items")
public class OrderItem {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderItem.class);
	
	/**
	 * 訂單明細ID（主鍵）
	 * 自動遞增
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 所屬訂單（關聯到 Order）
	 * 多對一關係，一個訂單可以有多個訂單明細
	 */
	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;
	
	/**
	 * 商品（關聯到 Product）
	 * 多對一關係，一個商品可以在多個訂單明細中
	 */
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	/**
	 * 購買數量
	 * 客戶購買此商品的數量
	 */
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	
	/**
	 * 訂單時的商品單價
	 * 記錄下單時的商品價格，避免商品價格變動影響已成立的訂單
	 */
	@Column(name = "unit_price", precision = 10, scale = 2, nullable = false)
	private BigDecimal unitPrice;
	
	/**
	 * 此明細的小計
	 * 計算方式：unitPrice * quantity
	 */
	@Column(name = "subtotal", precision = 10, scale = 2, nullable = false)
	private BigDecimal subtotal;
	
	/**
	 * 建立時間
	 * 由 Hibernate 自動設定
	 */
	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	/**
	 * 更新時間
	 * 由 Hibernate 自動更新
	 */
	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	/**
	 * 預設建構子
	 */
	public OrderItem() {
		logger.info("建立新的 OrderItem 物件");
	}
	
	/**
	 * 建構子（帶基本參數）
	 * 
	 * @param order 所屬訂單
	 * @param product 商品
	 * @param quantity 購買數量
	 * @param unitPrice 商品單價
	 */
	public OrderItem(Order order, Product product, Integer quantity, BigDecimal unitPrice) {
		logger.info("建立 OrderItem 物件 - order: {}, product: {}, quantity: {}, unitPrice: {}", 
					order != null ? order.getId() : null, 
					product != null ? product.getId() : null, 
					quantity, unitPrice);
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		// 自動計算小計
		this.subtotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
	}
	
	/**
	 * 取得訂單明細ID
	 * 
	 * @return 訂單明細ID
	 */
	public Long getId() {
		logger.debug("取得 OrderItem ID: {}", id);
		return id;
	}
	
	/**
	 * 設定訂單明細ID
	 * 
	 * @param id 訂單明細ID
	 */
	public void setId(Long id) {
		logger.debug("設定 OrderItem ID: {}", id);
		this.id = id;
	}
	
	/**
	 * 取得所屬訂單
	 * 
	 * @return 所屬訂單
	 */
	public Order getOrder() {
		logger.debug("取得 order (OrderItem ID: {})", id);
		return order;
	}
	
	/**
	 * 設定所屬訂單
	 * 
	 * @param order 所屬訂單
	 */
	public void setOrder(Order order) {
		logger.info("設定 order - Order ID: {} (OrderItem ID: {})", 
					order != null ? order.getId() : null, id);
		this.order = order;
	}
	
	/**
	 * 取得商品
	 * 
	 * @return 商品
	 */
	public Product getProduct() {
		logger.debug("取得 product (OrderItem ID: {})", id);
		return product;
	}
	
	/**
	 * 設定商品
	 * 
	 * @param product 商品
	 */
	public void setProduct(Product product) {
		logger.info("設定 product - Product ID: {} (OrderItem ID: {})", 
					product != null ? product.getId() : null, id);
		this.product = product;
	}
	
	/**
	 * 取得購買數量
	 * 
	 * @return 購買數量
	 */
	public Integer getQuantity() {
		logger.debug("取得 quantity: {}", quantity);
		return quantity;
	}
	
	/**
	 * 設定購買數量
	 * 設定時會自動重新計算小計
	 * 
	 * @param quantity 購買數量
	 */
	public void setQuantity(Integer quantity) {
		logger.info("設定 quantity: {} (OrderItem ID: {})", quantity, id);
		this.quantity = quantity;
		// 自動重新計算小計
		if (this.unitPrice != null) {
			this.subtotal = this.unitPrice.multiply(BigDecimal.valueOf(quantity));
			logger.debug("重新計算 subtotal: {}", this.subtotal);
		}
	}
	
	/**
	 * 取得訂單時的商品單價
	 * 
	 * @return 商品單價
	 */
	public BigDecimal getUnitPrice() {
		logger.debug("取得 unitPrice: {}", unitPrice);
		return unitPrice;
	}
	
	/**
	 * 設定訂單時的商品單價
	 * 設定時會自動重新計算小計
	 * 
	 * @param unitPrice 商品單價
	 */
	public void setUnitPrice(BigDecimal unitPrice) {
		logger.info("設定 unitPrice: {} (OrderItem ID: {})", unitPrice, id);
		this.unitPrice = unitPrice;
		// 自動重新計算小計
		if (this.quantity != null) {
			this.subtotal = unitPrice.multiply(BigDecimal.valueOf(this.quantity));
			logger.debug("重新計算 subtotal: {}", this.subtotal);
		}
	}
	
	/**
	 * 取得此明細的小計
	 * 
	 * @return 小計
	 */
	public BigDecimal getSubtotal() {
		logger.debug("取得 subtotal: {}", subtotal);
		return subtotal;
	}
	
	/**
	 * 設定此明細的小計
	 * 
	 * @param subtotal 小計
	 */
	public void setSubtotal(BigDecimal subtotal) {
		logger.debug("設定 subtotal: {}", subtotal);
		this.subtotal = subtotal;
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
	 * 計算並更新小計
	 * 手動觸發重新計算小計
	 */
	public void calculateSubtotal() {
		if (this.unitPrice != null && this.quantity != null && this.quantity > 0) {
			this.subtotal = this.unitPrice.multiply(BigDecimal.valueOf(this.quantity));
			logger.info("計算 subtotal: {} (quantity: {}, unitPrice: {})", 
						this.subtotal, this.quantity, this.unitPrice);
		} else {
			logger.warn("無法計算 subtotal - unitPrice: {}, quantity: {}", 
						this.unitPrice, this.quantity);
		}
	}
	
	/**
	 * 增加購買數量
	 * 
	 * @param additionalQuantity 增加的數量
	 */
	public void increaseQuantity(Integer additionalQuantity) {
		if (additionalQuantity != null && additionalQuantity > 0) {
			logger.info("增加數量: {} -> {} (OrderItem ID: {})", 
						this.quantity, this.quantity + additionalQuantity, id);
			setQuantity(this.quantity + additionalQuantity);
		}
	}
	
	/**
	 * 減少購買數量
	 * 
	 * @param decreaseQuantity 減少的數量
	 */
	public void decreaseQuantity(Integer decreaseQuantity) {
		if (decreaseQuantity != null && decreaseQuantity > 0 && this.quantity > decreaseQuantity) {
			logger.info("減少數量: {} -> {} (OrderItem ID: {})", 
						this.quantity, this.quantity - decreaseQuantity, id);
			setQuantity(this.quantity - decreaseQuantity);
		} else {
			logger.warn("無法減少數量 - 當前數量: {}, 嘗試減少: {}", this.quantity, decreaseQuantity);
		}
	}
	
	@Override
	public String toString() {
		return "OrderItem{" +
				"id=" + id +
				", product=" + (product != null ? product.getId() : null) +
				", quantity=" + quantity +
				", unitPrice=" + unitPrice +
				", subtotal=" + subtotal +
				", order=" + (order != null ? order.getId() : null) +
				'}';
	}
}

