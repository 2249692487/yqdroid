package com.ywqln.yqdroid.entity.resp.model;

import com.google.gson.annotations.SerializedName;

/**
 * 描述:user实体
 * <p>
 *
 * @author yanwenqiang
 * @date 2017/12/9
 */
public class StaffModel {

    @SerializedName("CompanyCode")
    private String companyCode;

    /**
     * 工号
     */
    @SerializedName("StaffNo")
    private String staffNo;

    /**
     * 域账户
     */
    @SerializedName("DomainAccount")
    private String domainAccount;

    /**
     * 手机号
     */
    @SerializedName("Mobile")
    private String mobile;

    /**
     * 导入的手机号
     */
    @SerializedName("ImportMobile")
    private String importMobile;

    /**
     * 邮件
     */
    @SerializedName("Email")
    private String email;

    /**
     * 状态，在职or离职
     */
    @SerializedName("Status")
    private String status;

    /**
     * 真实姓名
     */
    @SerializedName("CnName")
    private String cnName;

    /**
     * 职称
     */
    @SerializedName("Title")
    private String title;

    /**
     * 部门
     */
    @SerializedName("DeptName")
    private String deptName;

    /**
     * 全部门
     */
    @SerializedName("FullDept")
    private String fullDept;

    /**
     * 是否是导入的
     */
    @SerializedName("IsImport")
    private boolean isImport;

    /**
     * 入职时间
     */
    @SerializedName("EntryTime")
    private String entryTime;

    /**
     * 创建时间
     */
    @SerializedName("CreateTime")
    private String createTime;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getDomainAccount() {
        return domainAccount;
    }

    public void setDomainAccount(String domainAccount) {
        this.domainAccount = domainAccount;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getImportMobile() {
        return importMobile;
    }

    public void setImportMobile(String importMobile) {
        this.importMobile = importMobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getFullDept() {
        return fullDept;
    }

    public void setFullDept(String fullDept) {
        this.fullDept = fullDept;
    }

    public boolean isImport() {
        return isImport;
    }

    public void setImport(boolean anImport) {
        this.isImport = anImport;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
