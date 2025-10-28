package com.example.demo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * 訂單實體類別
 * 存放訂單的資訊，包含客戶、金額、狀態等
 */
@Entity
@Table(name = "orders")
public class Order {
	
	
	/**
	 * 訂單ID（主鍵）
	 * 自動遞增
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 訂單編號
	 * 唯一，用於客戶查詢訂單
	 */
	@Column(name = "order_no", unique = true, nullable = false, length = 50)
	private String orderNo;
	
	/**
	 * 客戶（關聯到 Customer）
	 * 多對一關係，一個客戶可以有多個訂單
	 */
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	
	/**
	 * 訂單總金額
	 * 使用 BigDecimal 確保精確的小數運算
	 */
	@Column(name = "total_amount", precision = 10, scale = 2)
	private BigDecimal totalAmount;
	
	/**
	 * 訂單狀態
	 * PENDING: 待處理, PROCESSING: 處理中, SHIPPED: 已出貨
	 * DELIVERED: 已送達, CANCELLED: 已取消, REFUNDED: 已退款
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 50)
	private OrderStatus status = OrderStatus.PENDING;
	
	/**
	 * 配送地址
	 * 如果與客戶預設地址不同
	 */
	@Column(name = "shipping_address", length = 500)
	private String shippingAddress;
	
	/**
	 * 付款方式
	 * CASH: 現金, CREDIT_CARD: 信用卡, TRANSFER: 轉帳
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "payment_method", length = 50)
	private PaymentMethod paymentMethod;
	
	/**
	 * 付款狀態
	 * UNPAID: 未付款, PAID: 已付款, REFUNDED: 已退款
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "payment_status", length = 50)
	private PaymentStatus paymentStatus = PaymentStatus.UNPAID;
	
	/**
	 * 備註
	 * 客戶的特別需求或備註
	 */
	@Column(name = "notes", columnDefinition = "TEXT")
	private String notes;
	
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
	 * 訂單狀態枚舉
	 */
	public enum OrderStatus {
		/**
		 * 待處理
		 * 訂單已建立，等待處理
		 */
		PENDING("待處理", "Pending"),
		
		/**
		 * 處理中
		 * 訂單正在準備中
		 */
		PROCESSING("處理中", "Processing"),
		
		/**
		 * 已出貨
		 * 訂單已出貨
		 */
		SHIPPED("已出貨", "Shipped"),
		
		/**
		 * 已送達
		 * 訂單已送達客戶
		 */
		DELIVERED("已送達", "Delivered"),
		
		/**
		 * 已取消
		 * 訂單已取消
		 */
		CANCELLED("已取消", "Cancelled"),
		
		/**
		 * 已退款
		 * 訂單已退款
		 */
		REFUNDED("已退款", "Refunded");
		
		private final String descriptionZh;
		private final String descriptionEn;
		
		OrderStatus(String descriptionZh, String descriptionEn) {
			this.descriptionZh = descriptionZh;
			this.descriptionEn = descriptionEn;
		}
		
		public String getDescriptionZh() {
			return descriptionZh;
		}
		
		public String getDescriptionEn() {
			return descriptionEn;
		}
	}
	
	/**
	 * 付款方式枚舉
	 */
	public enum PaymentMethod {
		/**
		 * 現金
		 */
		CASH("現金", "Cash"),
		
		/**
		 * 信用卡
		 */
		CREDIT_CARD("信用卡", "Credit Card"),
		
		/**
		 * 轉帳
		 */
		TRANSFER("轉帳", "Transfer"),
		
		/**
		 * 線上付款
		 */
		ONLINE("線上付款", "Online Payment");
		
		private final String descriptionZh;
		private final String descriptionEn;
		
		PaymentMethod(String descriptionZh, String descriptionEn) {
			this.descriptionZh = descriptionZh;
			this.descriptionEn = descriptionEn;
		}
		
		public String getDescriptionZh() {
			return descriptionZh;
		}
		
		public String getDescriptionEn() {
			return descriptionEn;
		}
	}
	
	/**
	 * 付款狀態枚舉
	 */
	public enum PaymentStatus {
		/**
		 * 未付款
		 */
		UNPAID("未付款", "Unpaid"),
		
		/**
		 * 已付款
		 */
		PAID("已付款", "Paid"),
		
		/**
		 * 已退款
		 */
		REFUNDED("已退款", "Refunded");
		
		private final String descriptionZh;
		private final String descriptionEn;
		
		PaymentStatus(String descriptionZh, String descriptionEn) {
			this.descriptionZh = descriptionZh;
			this.descriptionEn = descriptionEn;
		}
		
		public String getDescriptionZh() {
			return descriptionZh;
		}
		
		public String getDescriptionEn() {
			return descriptionEn;
		}
	}
	
	/**
	 * 預設建構子
	 */
	public Order() {
	}
	
	/**
	 * 建構子（帶基本參數）
	 * 
	 * @param orderNo 訂單編號
	 * @param customer 客戶
	 */
	public Order(String orderNo, Customer customer) {
					orderNo, customer != null ? customer.getId() : null);
		this.orderNo = orderNo;
		this.customer = customer;
		this.status = OrderStatus.PENDING;
		this.paymentStatus = PaymentStatus.UNPAID;
	}
	
	/**
	 * 取得訂單ID
	 * 
	 * @return 訂單ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * 設定訂單ID
	 * 
	 * @param id 訂單ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 取得訂單編號
	 * 
	 * @return 訂單編號
	 */
	public String getOrderNo() {
		return orderNo;
	}
	
	/**
	 * 設定訂單編號
	 * 
	 * @param orderNo 訂單編號
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	/**
	 * 取得客戶
	 * 
	 * @return 客戶
	 */
	public Customer getCustomer() {
		return customer;
	}
	
	/**
	 * 設定客戶
	 * 
	 * @param customer 客戶
	 */
	public void setCustomer(Customer customer) {
					customer != null ? customer.getId() : null, id);
		this.customer = customer;
	}
	
	/**
	 * 取得訂單總金額
	 * 
	 * @return 訂單總金額
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	
	/**
	 * 設定訂單總金額
	 * 
	 * @param totalAmount 訂單總金額
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	/**
	 * 取得訂單狀態
	 * 
	 * @return 訂單狀態
	 */
	public OrderStatus getStatus() {
		return status;
	}
	
	/**
	 * 設定訂單狀態
	 * 
	 * @param status 訂單狀態
	 */
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	/**
	 * 取得配送地址
	 * 
	 * @return 配送地址
	 */
	public String getShippingAddress() {
		return shippingAddress;
	}
	
	/**
	 * 設定配送地址
	 * 
	 * @param shippingAddress 配送地址
	 */
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
	/**
	 * 取得付款方式
	 * 
	 * @return 付款方式
	 */
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	
	/**
	 * 設定付款方式
	 * 
	 * @param paymentMethod 付款方式
	 */
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	/**
	 * 取得付款狀態
	 * 
	 * @return 付款狀態
	 */
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
	
	/**
	 * 設定付款狀態
	 * 
	 * @param paymentStatus 付款狀態
	 */
	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	/**
	 * 取得備註
	 * 
	 * @return 備註
	 */
	public String getNotes() {
		return notes;
	}
	
	/**
	 * 設定備註
	 * 
	 * @param notes 備註
	 */
	public void setNotes(String notes) {
		this.notes = notes;
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
		this.updatedAt = updatedAt;
	}
	
	/**
	 * 檢查訂單是否可以取消
	 * 
	 * @return true: 可以取消, false: 不可以取消
	 */
	public boolean canCancel() {
		boolean canCancel = status == OrderStatus.PENDING || status == OrderStatus.PROCESSING;
		return canCancel;
	}
	
	/**
	 * 取消訂單
	 */
	public void cancel() {
		this.status = OrderStatus.CANCELLED;
	}
	
	/**
	 * 完成付款
	 */
	public void completePayment() {
		this.paymentStatus = PaymentStatus.PAID;
	}
	
	/**
	 * 標記為已出貨
	 */
	public void markAsShipped() {
		this.status = OrderStatus.SHIPPED;
	}
	
	/**
	 * 標記為已送達
	 */
	public void markAsDelivered() {
		this.status = OrderStatus.DELIVERED;
	}
	
	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", orderNo='" + orderNo + '\'' +
				", customer=" + (customer != null ? customer.getId() : null) +
				", totalAmount=" + totalAmount +
				", status=" + status +
				", paymentMethod=" + paymentMethod +
				", paymentStatus=" + paymentStatus +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				'}';
	}
}

