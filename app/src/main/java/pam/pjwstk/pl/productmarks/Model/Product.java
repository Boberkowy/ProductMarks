package pam.pjwstk.pl.productmarks.Model;


import java.util.UUID;

/**
 * Created by Boberkowy on 05.11.2017.
 */

public class Product {

    private UUID mId;
    private String mName;
    private double mMark;
    private String mDesc;

    public Product(){
        mId = UUID.randomUUID();
    }


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

    public double getMark() {
        return mMark;
    }

    public void setMark(double mark) {
        mMark = mark;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String desc) {
        mDesc = desc;
    }
}
