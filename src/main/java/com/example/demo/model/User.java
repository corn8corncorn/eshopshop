package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用者實體類別
 * 存放使用者基本資訊、認證資訊及角色權限
 */
@Entity
@Table(name = "users")
public class User {
    
    private static final Logger logger = LoggerFactory.getLogger(User.class);
    
    /**
     * 使用者ID（主鍵）
     * 自動遞增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * 使用者名稱
     * 用於登入，必須唯一且非空
     */
    @Column(unique = true, nullable = false, length = 50)
    private String username;
    
    /**
     * 電子郵件
     * 用於聯絡和帳號驗證，必須唯一且非空
     */
    @Column(nullable = false, length = 100)
    private String email;
    
    /**
     * 密碼（加密後）
     * 使用 BCrypt 或其他加密方式儲存
     */
    @Column(nullable = false, length = 100)
    private String password;
    
    /**
     * 帳號建立時間
     * 由 Hibernate 自動設定，建立後不可修改
     */
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    /**
     * 帳號更新時間
     * 由 Hibernate 自動更新
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    /**
     * 帳號是否啟用
     * true: 帳號啟用，可正常使用
     * false: 帳號停用，無法登入
     */
    @Column(name = "is_enabled")
    private Boolean enabled = true;
    
    /**
     * 使用者角色
     * 決定使用者的權限等級（USER, ADMIN）
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role = UserRole.USER;
    
    /**
     * 預設建構子
     */
    public User() {
        logger.info("建立新的 User 物件");
    }
    
    /**
     * 建構子（帶參數）
     * 
     * @param username 使用者名稱
     * @param email 電子郵件
     * @param password 密碼
     */
    public User(String username, String email, String password) {
        logger.info("建立 User 物件 - username: {}", username);
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = true;
        this.role = UserRole.USER;
    }
    
    /**
     * 取得使用者ID
     * 
     * @return 使用者ID
     */
    public Long getId() {
        logger.debug("取得 User ID: {}", id);
        return id;
    }
    
    /**
     * 設定使用者ID
     * 
     * @param id 使用者ID
     */
    public void setId(Long id) {
        logger.debug("設定 User ID: {}", id);
        this.id = id;
    }
    
    /**
     * 取得使用者名稱
     * 
     * @return 使用者名稱
     */
    public String getUsername() {
        logger.debug("取得 username: {}", username);
        return username;
    }
    
    /**
     * 設定使用者名稱
     * 
     * @param username 使用者名稱
     */
    public void setUsername(String username) {
        logger.debug("設定 username: {}", username);
        this.username = username;
    }
    
    /**
     * 取得電子郵件
     * 
     * @return 電子郵件
     */
    public String getEmail() {
        logger.debug("取得 email: {}", email);
        return email;
    }
    
    /**
     * 設定電子郵件
     * 
     * @param email 電子郵件
     */
    public void setEmail(String email) {
        logger.debug("設定 email: {}", email);
        this.email = email;
    }
    
    /**
     * 取得密碼
     * 
     * @return 密碼（加密後的）
     */
    public String getPassword() {
        logger.debug("取得密碼");
        return password;
    }
    
    /**
     * 設定密碼
     * 
     * @param password 密碼
     */
    public void setPassword(String password) {
        logger.debug("設定密碼");
        this.password = password;
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
     * 取得帳號啟用狀態
     * 
     * @return true: 啟用, false: 停用
     */
    public Boolean getEnabled() {
        logger.debug("取得 enabled: {}", enabled);
        return enabled;
    }
    
    /**
     * 設定帳號啟用狀態
     * 
     * @param enabled 啟用狀態
     */
    public void setEnabled(Boolean enabled) {
        logger.info("設定 User enabled 狀態: {} (ID: {})", enabled, id);
        this.enabled = enabled;
    }
    
    /**
     * 取得使用者角色
     * 
     * @return 使用者角色
     */
    public UserRole getRole() {
        logger.debug("取得 role: {}", role);
        return role;
    }
    
    /**
     * 設定使用者角色
     * 
     * @param role 使用者角色
     */
    public void setRole(UserRole role) {
        logger.info("設定 User role: {} (ID: {})", role, id);
        this.role = role;
    }
    
    /**
     * 檢查是否為管理員
     * 
     * @return true: 是管理員, false: 非管理員
     */
    public boolean isAdmin() {
        boolean isAdmin = role == UserRole.ADMIN;
        logger.debug("檢查是否為管理員: {} (username: {})", isAdmin, username);
        return isAdmin;
    }
    
    /**
     * 停用帳號
     */
    public void disable() {
        logger.info("停用帳號 (ID: {}, username: {})", id, username);
        this.enabled = false;
    }
    
    /**
     * 啟用帳號
     */
    public void enable() {
        logger.info("啟用帳號 (ID: {}, username: {})", id, username);
        this.enabled = true;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", role=" + role +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
