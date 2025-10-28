package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 客戶實體類別
 * 存放客戶的詳細資訊，與 User 實體相關聯
 */
@Entity
@Table(name = "customers")
public class Customer {
    
    private static final Logger logger = LoggerFactory.getLogger(Customer.class);
    
    /**
     * 客戶ID（主鍵）
     * 自動遞增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * 使用者帳號（關聯到 User）
     * 一對一關係
     */
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
    
    /**
     * 客戶姓名
     */
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    /**
     * 連絡電話
     */
    @Column(name = "phone", length = 20)
    private String phone;
    
    /**
     * 地址
     */
    @Column(name = "address", length = 500)
    private String address;
    
    /**
     * 城市
     */
    @Column(name = "city", length = 100)
    private String city;
    
    /**
     * 郵遞區號
     */
    @Column(name = "postal_code", length = 20)
    private String postalCode;
    
    /**
     * 國家
     */
    @Column(name = "country", length = 100)
    private String country;
    
    /**
     * 生日
     */
    @Column(name = "birthday")
    private LocalDateTime birthday;
    
    /**
     * 性別
     * M: 男性, F: 女性, O: 其他
     */
    @Column(name = "gender", length = 1)
    private String gender;
    
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
    public Customer() {
        logger.info("建立新的 Customer 物件");
    }
    
    /**
     * 建構子（帶參數）
     * 
     * @param user 使用者帳號
     * @param name 客戶姓名
     */
    public Customer(User user, String name) {
        logger.info("建立 Customer 物件 - name: {}", name);
        this.user = user;
        this.name = name;
    }
    
    /**
     * 建構子（完整參數）
     * 
     * @param user 使用者帳號
     * @param name 客戶姓名
     * @param phone 連絡電話
     * @param address 地址
     */
    public Customer(User user, String name, String phone, String address) {
        logger.info("建立 Customer 物件 - name: {}, phone: {}", name, phone);
        this.user = user;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
    
    /**
     * 取得客戶ID
     * 
     * @return 客戶ID
     */
    public Long getId() {
        logger.debug("取得 Customer ID: {}", id);
        return id;
    }
    
    /**
     * 設定客戶ID
     * 
     * @param id 客戶ID
     */
    public void setId(Long id) {
        logger.debug("設定 Customer ID: {}", id);
        this.id = id;
    }
    
    /**
     * 取得使用者帳號
     * 
     * @return 使用者帳號
     */
    public User getUser() {
        logger.debug("取得 User 關聯 (Customer ID: {})", id);
        return user;
    }
    
    /**
     * 設定使用者帳號
     * 
     * @param user 使用者帳號
     */
    public void setUser(User user) {
        logger.info("設定 User 關聯 - User ID: {} (Customer ID: {})", 
                    user != null ? user.getId() : null, id);
        this.user = user;
    }
    
    /**
     * 取得客戶姓名
     * 
     * @return 客戶姓名
     */
    public String getName() {
        logger.debug("取得 name: {}", name);
        return name;
    }
    
    /**
     * 設定客戶姓名
     * 
     * @param name 客戶姓名
     */
    public void setName(String name) {
        logger.debug("設定 name: {}", name);
        this.name = name;
    }
    
    /**
     * 取得連絡電話
     * 
     * @return 連絡電話
     */
    public String getPhone() {
        logger.debug("取得 phone: {}", phone);
        return phone;
    }
    
    /**
     * 設定連絡電話
     * 
     * @param phone 連絡電話
     */
    public void setPhone(String phone) {
        logger.debug("設定 phone: {}", phone);
        this.phone = phone;
    }
    
    /**
     * 取得地址
     * 
     * @return 地址
     */
    public String getAddress() {
        logger.debug("取得 address: {}", address);
        return address;
    }
    
    /**
     * 設定地址
     * 
     * @param address 地址
     */
    public void setAddress(String address) {
        logger.debug("設定 address: {}", address);
        this.address = address;
    }
    
    /**
     * 取得城市
     * 
     * @return 城市
     */
    public String getCity() {
        logger.debug("取得 city: {}", city);
        return city;
    }
    
    /**
     * 設定城市
     * 
     * @param city 城市
     */
    public void setCity(String city) {
        logger.debug("設定 city: {}", city);
        this.city = city;
    }
    
    /**
     * 取得郵遞區號
     * 
     * @return 郵遞區號
     */
    public String getPostalCode() {
        logger.debug("取得 postalCode: {}", postalCode);
        return postalCode;
    }
    
    /**
     * 設定郵遞區號
     * 
     * @param postalCode 郵遞區號
     */
    public void setPostalCode(String postalCode) {
        logger.debug("設定 postalCode: {}", postalCode);
        this.postalCode = postalCode;
    }
    
    /**
     * 取得國家
     * 
     * @return 國家
     */
    public String getCountry() {
        logger.debug("取得 country: {}", country);
        return country;
    }
    
    /**
     * 設定國家
     * 
     * @param country 國家
     */
    public void setCountry(String country) {
        logger.debug("設定 country: {}", country);
        this.country = country;
    }
    
    /**
     * 取得生日
     * 
     * @return 生日
     */
    public LocalDateTime getBirthday() {
        logger.debug("取得 birthday: {}", birthday);
        return birthday;
    }
    
    /**
     * 設定生日
     * 
     * @param birthday 生日
     */
    public void setBirthday(LocalDateTime birthday) {
        logger.debug("設定 birthday: {}", birthday);
        this.birthday = birthday;
    }
    
    /**
     * 取得性別
     * 
     * @return 性別
     */
    public String getGender() {
        logger.debug("取得 gender: {}", gender);
        return gender;
    }
    
    /**
     * 設定性別
     * 
     * @param gender 性別（M: 男性, F: 女性, O: 其他）
     */
    public void setGender(String gender) {
        logger.debug("設定 gender: {}", gender);
        this.gender = gender;
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
     * 取得完整地址
     * 
     * @return 完整地址字串
     */
    public String getFullAddress() {
        StringBuilder fullAddress = new StringBuilder();
        
        if (address != null && !address.isEmpty()) {
            fullAddress.append(address);
        }
        
        if (city != null && !city.isEmpty()) {
            if (fullAddress.length() > 0) {
                fullAddress.append(", ");
            }
            fullAddress.append(city);
        }
        
        if (postalCode != null && !postalCode.isEmpty()) {
            if (fullAddress.length() > 0) {
                fullAddress.append(" ");
            }
            fullAddress.append(postalCode);
        }
        
        if (country != null && !country.isEmpty()) {
            if (fullAddress.length() > 0) {
                fullAddress.append(", ");
            }
            fullAddress.append(country);
        }
        
        String result = fullAddress.toString();
        logger.debug("取得完整地址: {}", result);
        return result;
    }
    
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", user=" + (user != null ? user.getId() : null) +
                '}';
    }
}
