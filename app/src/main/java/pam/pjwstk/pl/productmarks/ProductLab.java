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
    private static ProductLab sProductLab;

    private List<Product> mProducts;

    public static ProductLab get(Context context){
        if (sProductLab == null){
            sProductLab = new ProductLab();
        }
        return sProductLab;
    }

    private ProductLab(Context context){
        mProducts = new ArrayList<>();
        for(int i = 0; i < 100 ; i++){
            Product product = new Product();
            product.setName("Product 1" + i );
            product.setDesc("Product " + i + " is awesome!");
            product.setMark(5);
            mProducts.add(product);
        }
    }

    private List<Product> getProducts(){
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
