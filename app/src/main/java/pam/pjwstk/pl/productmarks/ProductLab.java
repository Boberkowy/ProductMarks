package pam.pjwstk.pl.productmarks;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import pam.pjwstk.pl.productmarks.Model.Product;

/**
 * Created by Boberkowy on 10.11.2017.
 */

public class ProductLab {
    public static ProductLab sProductLab;

    private List<Product> mProducts;

    public static ProductLab get(Context context){
        if (sProductLab == null){
            sProductLab = new ProductLab(context);
        }
        return sProductLab;
    }
    public void addProduct(Product product) {
        mProducts.add(product);
    }

    public void deleteProduct(Product product){
        mProducts.remove(product);
    }
    public ProductLab(Context context){
        mProducts = new ArrayList<>();

    }

    public List<Product> getProducts(){
        return mProducts;
    }

    public Product getProduct(UUID id){
        for(Product product : mProducts){
            if(product.getId().equals(id)){
                return product;
            }
        }
        return null;
    }
}
