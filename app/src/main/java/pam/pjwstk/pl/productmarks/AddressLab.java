package pam.pjwstk.pl.productmarks;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import pam.pjwstk.pl.productmarks.Database.AddressCursorWrapper;
import pam.pjwstk.pl.productmarks.Database.ProductBaseHelper;
import pam.pjwstk.pl.productmarks.Model.Address;

import static pam.pjwstk.pl.productmarks.Database.ProductDbSchema.AddressTable;

public class AddressLab {

    private static AddressLab sAddressLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private AddressLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new ProductBaseHelper(mContext).getWritableDatabase();
    }

    public static AddressLab get(Context context){
        if (sAddressLab == null){
            sAddressLab= new AddressLab(context);
        }
        return sAddressLab;
    }

    public void addAddress(Address address) {
        ContentValues values = getAddressValues(address);
        mDatabase.insert(AddressTable.NAME,null,values);
    }

    public void updateProduct(Address address){
        String uuidString = address.getId().toString();
        ContentValues values = getAddressValues(address);

        mDatabase.update(AddressTable.NAME,values, AddressTable.Cols.UUID + "= ?", new String[] {uuidString});
    }
    public void deleteProduct(Address address){
        String uuidString = address.getId().toString();
        ContentValues values = getAddressValues(address);

        mDatabase.delete(AddressTable.NAME, AddressTable.Cols.UUID + "= ?", new String[]{uuidString})
    }

    private AddressCursorWrapper queryAddresses(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                AddressTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new AddressCursorWrapper(cursor);
    }

    public List<Address> getAddresses(){
        List<Address> addresses = new ArrayList<>();

        AddressCursorWrapper cursor = queryAddresses(null,null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                addresses.add(cursor.getAddress());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return addresses;
    }

    public Address getAddress(UUID id){

        AddressCursorWrapper cursor = queryAddresses(AddressTable.Cols.UUID + "= ?", new String[] {id.toString()} );

        try {
            if(cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getAddress();
        }finally {
            cursor.close();
        }

    }

    private static ContentValues getAddressValues(Address address){
        ContentValues values = new ContentValues();

        values.put(AddressTable.Cols.UUID, address.getId().toString());
        values.put(AddressTable.Cols.STREET, address.getStreet());
        values.put(AddressTable.Cols.NUMBER, address.getNumber());
        values.put(AddressTable.Cols.CITY, address.getCity());


        return values;
    }
}
