package pam.pjwstk.pl.productmarks.Model;


import java.util.UUID;

/**
 * Created by Boberkowy on 05.11.2017.
 */

public class Product {

    private UUID mId;
    private String mName;
    private String mMark;
    private String mDesc;
    private String mShop;

    public Product(){
        mId = UUID.randomUUID();
    }

    public Product(UUID id){mId = id;}

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getMark() {
        return mMark;
    }

    public void setMark(String mark) {
        mMark = mark;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String desc) {
        mDesc = desc;
    }

    public String getShop() {
        return mShop;
    }

    public void setShop(String shop) {
        mShop = shop;
    }
}
