package pam.pjwstk.pl.productmarks.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import pam.pjwstk.pl.productmarks.Model.Shop;

import static pam.pjwstk.pl.productmarks.Database.ProductDbSchema.ShopTable;

public class ShopCursorWrapper extends CursorWrapper {

    public ShopCursorWrapper(Cursor cursor) {
        super(cursor);
    }


    public Shop getShop(){
        String uuidString = getString(getColumnIndex(ShopTable.Cols.UUID));
        String name = getString(getColumnIndex(ShopTable.Cols.SHOP_NAME));
        String address_id = getString(getColumnIndex(ShopTable.Cols.ADDRESS_ID));

        Shop shop = new Shop(UUID.fromString(uuidString));
        shop.setName(name);
        shop.setAddressId(address_id);

        return shop;
    }
}
