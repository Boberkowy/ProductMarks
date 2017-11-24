package pam.pjwstk.pl.productmarks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import pam.pjwstk.pl.productmarks.Database.ProductBaseHelper;
import pam.pjwstk.pl.productmarks.Database.ProductCursorWrapper;
import pam.pjwstk.pl.productmarks.Database.ProductDbSchema;
import pam.pjwstk.pl.productmarks.Database.ProductDbSchema.ProductTable;
import pam.pjwstk.pl.productmarks.Model.Address;
import pam.pjwstk.pl.productmarks.Model.Product;
import pam.pjwstk.pl.productmarks.Model.Shop;

/**
 * Created by Boberkowy on 10.11.2017.
 */

public class ProductLab {

    private static ProductLab sProductLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private ProductLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new ProductBaseHelper(mContext).getWritableDatabase();
    }

    public static ProductLab get(Context context){

        if (sProductLab == null){
            sProductLab = new ProductLab(context);
        }
        return sProductLab;
    }
    public void addProduct(Product product) {
        ContentValues values = getContentValues(product);
        mDatabase.insert(ProductTable.NAME,null,values);
    }

    public void updateProduct(Product product){
        String uuidString = product.getId().toString();
        ContentValues values = getContentValues(product);

        mDatabase.update(ProductTable.NAME,values,ProductTable.Cols.UUID + "= ?", new String[] {uuidString});
    }
    public void deleteProduct(Product product){
        String uuidString = product.getId().toString();
        ContentValues values = getContentValues(product);

        mDatabase.delete(ProductTable.NAME,ProductTable.Cols.UUID + "= ?", new String[] {uuidString});
    }

    private ProductCursorWrapper queryProducts(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                ProductTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new ProductCursorWrapper(cursor);
    }

    public List<Product> getProducts(){
        List<Product> products = new ArrayList<>();

        ProductCursorWrapper cursor = queryProducts(null,null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                products.add(cursor.getProduct());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return products;
    }

    public Product getProduct(UUID id){

        ProductCursorWrapper cursor = queryProducts(ProductTable.Cols.UUID + "= ?", new String[] {id.toString()} );

        try {
            if(cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getProduct();
        }finally {
            cursor.close();
        }

    }

    private static ContentValues getContentValues(Product product){
        ContentValues values = new ContentValues();

        values.put(ProductTable.Cols.UUID,product.getId().toString());
        values.put(ProductTable.Cols.PRODUCT_NAME,product.getName());
        values.put(ProductTable.Cols.DESCRIPTION,product.getDesc());
        values.put(ProductTable.Cols.MARK,product.getMark());

        return values;
    }

    private static ContentValues getShopContentValues(Shop shop){
        ContentValues values = new ContentValues();

        values.put(ProductDbSchema.ShopTable.Cols.UUID, shop.getUUID().toString());
        values.put(ProductDbSchema.ShopTable.Cols.SHOP_NAME, shop.getName());
        values.put(ProductDbSchema.ShopTable.Cols.ADDRESS_ID, shop.getAddressId());

        return values;
    }


    }
}
