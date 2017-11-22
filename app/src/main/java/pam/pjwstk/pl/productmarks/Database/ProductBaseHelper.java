package pam.pjwstk.pl.productmarks.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static pam.pjwstk.pl.productmarks.Database.ProductDbSchema.ProductTable.Cols;
import static pam.pjwstk.pl.productmarks.Database.ProductDbSchema.ProductTable.NAME;

/**
 * Created by Boberkowy on 04.11.2017.
 */

public class BaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "productMarks.db";


    public BaseHelper(Context context){
        super(context,DATABASE_NAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table "+ NAME +
        "(" +
                "id integer primary key autoincrement," +
                    Cols.PRODUCT_NAME + "string, " +
                    Cols.DESCRIPTION + " string, " +
                    Cols.MARK + "real" +
                    ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
