package com.example.demo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 商品實體類別
 * 存放商品的資訊，包含名稱、價格、描述、狀態等
 */
@Entity
@Table(name = "product")
public class Product {
	
	private static final Logger logger = LoggerFactory.getLogger(Product.class);
	
	/**
	 * 商品ID（主鍵）
	 * 自動遞增
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 商品名稱
	 * 必填，最大長度 50 字元
	 */
	@Column(name = "name", nullable = false, length = 50)
	private String name;

	/**
	 * 商品類型/分類
	 * 必填，最大長度 100 字元
	 */
	@Column(name = "type", nullable = false, length = 100)
	private String type;

	/**
	 * 商品價格
	 * 使用 BigDecimal 確保精確的小數運算
	 */
	@Column(name = "price", precision = 10, scale = 0)
	private BigDecimal price;

	/**
	 * 商品描述
	 * 使用 TEXT 類型支援較長文字
	 */
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	/**
	 * 商品圖片 URL
	 * 存放商品圖片的網址，最大長度 500 字元
	 */
	@Column(name = "image_url", length = 500)
	private String imageUrl;

	/**
	 * 商品狀態
	 * ACTIVE: 上架中，INACTIVE: 已下架，OUT_OF_STOCK: 缺貨中
	 * 預設為 ACTIVE
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private ProductStatus status = ProductStatus.ACTIVE;

	/**
	 * 建立時間
	 * 由 Hibernate 自動設定，建立後不可修改
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
	public Product() {
		logger.info("建立新的 Product 物件");
	}

	/**
	 * 建構子（帶基本參數）
	 * 
	 * @param name 商品名稱
	 * @param type 商品類型
	 * @param price 商品價格
	 */
	public Product(String name, String type, BigDecimal price) {
		logger.info("建立 Product 物件 - name: {}, type: {}, price: {}", name, type, price);
		this.name = name;
		this.type = type;
		this.price = price;
		this.status = ProductStatus.ACTIVE;
	}

	/**
	 * 商品狀態枚舉
	 */
	public enum ProductStatus {
		/**
		 * 上架中
		 * 商品正常販售中
		 */
		ACTIVE("上架中", "Active"),
		
		/**
		 * 已下架
		 * 商品已停止販售
		 */
		INACTIVE("已下架", "Inactive"),
		
		/**
		 * 缺貨中
		 * 商品暫時缺貨
		 */
		OUT_OF_STOCK("缺貨中", "Out of Stock");

		private final String descriptionZh;
		private final String descriptionEn;

		ProductStatus(String descriptionZh, String descriptionEn) {
			this.descriptionZh = descriptionZh;
			this.descriptionEn = descriptionEn;
		}

		/**
		 * 取得中文描述
		 * 
		 * @return 中文描述
		 */
		public String getDescriptionZh() {
			return descriptionZh;
		}
		
		/**
		 * 取得英文描述
		 * 
		 * @return 英文描述
		 */
		public String getDescriptionEn() {
			return descriptionEn;
		}
		
		/**
		 * 取得中文描述（向後相容）
		 * 
		 * @return 中文描述
		 */
		public String getDescription() {
			return descriptionZh;
		}
	}

	/**
	 * 取得商品ID
	 * 
	 * @return 商品ID
	 */
	public Long getId() {
		logger.debug("取得 Product ID: {}", id);
		return id;
	}
	
	/**
	 * 設定商品ID
	 * 
	 * @param id 商品ID
	 */
	public void setId(Long id) {
		logger.debug("設定 Product ID: {}", id);
		this.id = id;
	}

	/**
	 * 取得商品名稱
	 * 
	 * @return 商品名稱
	 */
	public String getName() {
		logger.debug("取得 name: {}", name);
		return name;
	}

	/**
	 * 設定商品名稱
	 * 
	 * @param name 商品名稱
	 */
	public void setName(String name) {
		logger.debug("設定 name: {}", name);
		this.name = name;
	}

	/**
	 * 取得商品描述
	 * 
	 * @return 商品描述
	 */
	public String getDescription() {
		logger.debug("取得 description");
		return description;
	}

	/**
	 * 設定商品描述
	 * 
	 * @param description 商品描述
	 */
	public void setDescription(String description) {
		logger.debug("設定 description");
		this.description = description;
	}

	/**
	 * 取得商品圖片 URL
	 * 
	 * @return 圖片 URL
	 */
	public String getImageUrl() {
		logger.debug("取得 imageUrl: {}", imageUrl);
		return imageUrl;
	}

	/**
	 * 設定商品圖片 URL
	 * 
	 * @param imageUrl 圖片 URL
	 */
	public void setImageUrl(String imageUrl) {
		logger.debug("設定 imageUrl: {}", imageUrl);
		this.imageUrl = imageUrl;
	}

	/**
	 * 取得商品類型
	 * 
	 * @return 商品類型
	 */
	public String getType() {
		logger.debug("取得 type: {}", type);
		return type;
	}

	/**
	 * 設定商品類型
	 * 
	 * @param type 商品類型
	 */
	public void setType(String type) {
		logger.debug("設定 type: {}", type);
		this.type = type;
	}

	/**
	 * 取得商品價格
	 * 
	 * @return 商品價格
	 */
	public BigDecimal getPrice() {
		logger.debug("取得 price: {}", price);
		return price;
	}

	/**
	 * 設定商品價格
	 * 
	 * @param price 商品價格
	 */
	public void setPrice(BigDecimal price) {
		logger.debug("設定 price: {}", price);
		this.price = price;
	}

	/**
	 * 取得商品狀態
	 * 
	 * @return 商品狀態
	 */
	public ProductStatus getStatus() {
		logger.debug("取得 status: {}", status);
		return status;
	}

	/**
	 * 設定商品狀態
	 * 
	 * @param status 商品狀態
	 */
	public void setStatus(ProductStatus status) {
		logger.info("設定 Product status: {} (Product ID: {})", status, id);
		this.status = status;
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
	 * 檢查商品是否上架中
	 * 
	 * @return true: 上架中, false: 非上架中
	 */
	public boolean isActive() {
		boolean active = status == ProductStatus.ACTIVE;
		logger.debug("檢查 Product 是否上架: {} (Product ID: {})", active, id);
		return active;
	}
	
	/**
	 * 檢查商品是否缺貨
	 * 
	 * @return true: 缺貨, false: 有庫存
	 */
	public boolean isOutOfStock() {
		boolean outOfStock = status == ProductStatus.OUT_OF_STOCK;
		logger.debug("檢查 Product 是否缺貨: {} (Product ID: {})", outOfStock, id);
		return outOfStock;
	}
	
	/**
	 * 下架商品
	 */
	public void deactivate() {
		logger.info("下架商品 (Product ID: {}, name: {})", id, name);
		this.status = ProductStatus.INACTIVE;
	}
	
	/**
	 * 上架商品
	 */
	public void activate() {
		logger.info("上架商品 (Product ID: {}, name: {})", id, name);
		this.status = ProductStatus.ACTIVE;
	}
	
	/**
	 * 標記為缺貨
	 */
	public void markAsOutOfStock() {
		logger.info("標記商品為缺貨 (Product ID: {}, name: {})", id, name);
		this.status = ProductStatus.OUT_OF_STOCK;
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", name='" + name + '\'' +
				", type='" + type + '\'' +
				", price=" + price +
				", status=" + status +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				'}';
	}
}
