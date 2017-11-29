package pam.pjwstk.pl.productmarks.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static pam.pjwstk.pl.productmarks.Database.ProductDbSchema.*;
import static pam.pjwstk.pl.productmarks.Database.ProductDbSchema.ProductTable.Cols;

/**
 * Created by Boberkowy on 04.11.2017.
 */
//TODO: JAK JEDNO PADNIE TO WSZYSTKO PADINE - TAKI PROBLEM JUZ JEST
    //ALE ROZWIĄZUJEMY TEN PROBLEM BO MIKROSERWISY :)
public class ProductBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "productMarks.db";


    public ProductBaseHelper(Context context){
        super(context,DATABASE_NAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + ProductTable.NAME +
        "(" +
                "_id integer primary key autoincrement," +
                    Cols.UUID + ", " +
                    Cols.PRODUCT_NAME + ", " +
                    Cols.DESCRIPTION + ", " +
                    Cols.MARK  + ", " +
                    Cols.SHOP +
                    ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
