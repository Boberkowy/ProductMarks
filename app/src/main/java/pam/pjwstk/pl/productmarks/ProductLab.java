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

    public ProductLab(Context context){
        mProducts = new ArrayList<>();
        for(int i = 0; i < 100 ; i++){
            Product product = new Product();
            product.setName("Produkt " + i );
            product.setDesc("Produkt " + i + " is awesome!");
            if(i%2 == 0) {
                product.setMark("Polecam");
            }else {
                product.setMark("Nie polecam");
            }
            mProducts.add(product);
        }
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
