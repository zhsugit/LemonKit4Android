package net.lemonsoft.lemonkit.samples.view_controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AllRentListDto implements Serializable {

    //房源ID 
    private String id;

    //  图片地址 
    private String imageUrl;

    //房源主标题 
    private String titleA;

    //房源副标题 
    private String titleB;

    //月租金 
    private String rent;

    //   房源特色 
    private String feature;

    //  是否收藏（0：否；1：是） 
    private int collectionFlag;

    //   是否验真 0：正在验真 ；1：验真成功；2：验真失败 
    private String feature1;

    private String feature2;

    private String feature3;

    private String feature4;

    /** 请求返回状态码 */
    private int resultCode;

    private String count; // 搜索到的房源总数

    /** 是否分期 00：否 01 ：是 */
    private String rentInstallment;

    /** 是否验真 0：正在验真 ；1：验真成功；2：验真失败 */
    String certFlag;

    public String getCount() {

        return count;
    }

    public void setCount(String count) {

        this.count = count;
    }

    List<AllRentListDto> houses = new ArrayList<AllRentListDto>();

    public String getFeature1() {

        return feature1;
    }

    public void setFeature1(String feature1) {

        this.feature1 = feature1;
    }

    public String getFeature2() {

        return feature2;
    }

    public void setFeature2(String feature2) {

        this.feature2 = feature2;
    }

    public String getFeature3() {

        return feature3;
    }

    public void setFeature3(String feature3) {

        this.feature3 = feature3;
    }

    public String getFeature4() {

        return feature4;
    }

    public void setFeature4(String feature4) {

        this.feature4 = feature4;
    }

    public int getCollectionFlag() {

        return collectionFlag;
    }

    public void setCollectionFlag(int collectionFlag) {

        this.collectionFlag = collectionFlag;
    }

    public int getResultCode() {

        return resultCode;
    }

    public void setResultCode(int resultCode) {

        this.resultCode = resultCode;
    }

    public List<AllRentListDto> getHouses() {

        return houses;
    }

    public void setHouses(List<AllRentListDto> houses) {

        this.houses = houses;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getImageUrl() {

        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {

        this.imageUrl = imageUrl;
    }

    public String getTitleA() {

        return titleA;
    }

    public void setTitleA(String titleA) {

        this.titleA = titleA;
    }

    public String getTitleB() {

        return titleB;
    }

    public void setTitleB(String titleB) {

        this.titleB = titleB;
    }

    public String getRent() {

        return rent;
    }

    public void setRent(String rent) {

        this.rent = rent;
    }

    public String getFeature() {

        return feature;
    }

    public void setFeature(String feature) {

        this.feature = feature;
    }

    public String getRentInstallment() {

        return rentInstallment;
    }

    public void setRentInstallment(String rentInstallment) {

        this.rentInstallment = rentInstallment;
    }

    public String getCertFlag() {

        return certFlag;
    }

    public void setCertFlag(String certFlag) {

        this.certFlag = certFlag;
    }

}
