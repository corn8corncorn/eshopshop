package com.example.demo.model;

/**
 * 使用者角色枚舉類別
 * 定義系統中使用者的角色類型
 */
public enum UserRole {
    /**
     * 一般使用者角色
     * 用於普通會員，具有基本的購買功能
     */
    USER("user", "一般使用者", "General User"),
    
    /**
     * 管理員角色
     * 用於系統管理員，擁有完整的系統管理權限
     */
    ADMIN("admin", "管理員", "Administrator");
    
    /**
     * 角色代碼
     */
    private final String code;
    
    /**
     * 角色中文名稱
     */
    private final String nameZh;
    
    /**
     * 角色英文名稱
     */
    private final String nameEn;
    
    /**
     * 建構子
     * 
     * @param code 角色代碼
     * @param nameZh 角色中文名稱
     * @param nameEn 角色英文名稱
     */
    UserRole(String code, String nameZh, String nameEn) {
        this.code = code;
        this.nameZh = nameZh;
        this.nameEn = nameEn;
    }
    
    /**
     * 取得角色代碼
     * 
     * @return 角色代碼
     */
    public String getCode() {
        return code;
    }
    
    /**
     * 取得角色中文名稱
     * 
     * @return 角色中文名稱
     */
    public String getNameZh() {
        return nameZh;
    }
    
    /**
     * 取得角色英文名稱
     * 
     * @return 角色英文名稱
     */
    public String getNameEn() {
        return nameEn;
    }
}

