package com.example.b2bcamp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductResultVo {
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("seller_id")
    @Expose
    private String sellerId;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("p_img1")
    @Expose
    private String pImg1;
    @SerializedName("p_img2")
    @Expose
    private String pImg2;
    @SerializedName("p_img3")
    @Expose
    private String pImg3;
    @SerializedName("product_price")
    @Expose
    private String productPrice;
    @SerializedName("product_description")
    @Expose
    private String productDescription;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("profile_pic")
    @Expose
    private String profilePic;
    @SerializedName("contact_num")
    @Expose
    private String contactNum;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state_name")
    @Expose
    private String stateName;
    @SerializedName("qty")
    @Expose
    private String qty;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPImg1() {
        return pImg1;
    }

    public void setPImg1(String pImg1) {
        this.pImg1 = pImg1;
    }

    public String getPImg2() {
        return pImg2;
    }

    public void setPImg2(String pImg2) {
        this.pImg2 = pImg2;
    }

    public String getPImg3() {
        return pImg3;
    }

    public void setPImg3(String pImg3) {
        this.pImg3 = pImg3;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }


    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
