package pam.pjwstk.pl.productmarks.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import pam.pjwstk.pl.productmarks.Model.Product;

import static pam.pjwstk.pl.productmarks.Database.ProductDbSchema.ProductTable;

/**
 * Created by Boberkowy on 22.11.2017.
 */

public class ProductCursorWrapper extends CursorWrapper {

    public ProductCursorWrapper (Cursor cursor){
        super(cursor);
    }

    public Product getProduct(){
        String uuidString = getString(getColumnIndex(ProductTable.Cols.UUID));
        String name = getString(getColumnIndex(ProductTable.Cols.PRODUCT_NAME));
        String desc = getString(getColumnIndex(ProductTable.Cols.DESCRIPTION));
        String mark = getString(getColumnIndex(ProductTable.Cols.MARK));

        Product product = new Product(UUID.fromString(uuidString));
        product.setName(name);
        product.setDesc(desc);
        product.setMark(mark);

        return product;
    }


}
