package pam.pjwstk.pl.productmarks.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import pam.pjwstk.pl.productmarks.Model.Address;
import pam.pjwstk.pl.productmarks.Model.Shop;

import static android.R.attr.name;
import static pam.pjwstk.pl.productmarks.Database.ProductDbSchema.*;

/**
 * Created by Boberkowy on 24.11.2017.
 */

public class AddressCursorWrapper extends CursorWrapper {

        public AddressCursorWrapper(Cursor cursor) {
            super(cursor);
        }


        public Address getAddress(){
            String uuidString = getString(getColumnIndex(AddressTable.Cols.UUID));
            String street = getString(getColumnIndex(AddressTable.Cols.STREET));
            String number = getString(getColumnIndex(AddressTable.Cols.NUMBER));
            String city = getString(getColumnIndex(AddressTable.Cols.CITY));

            Address address = new Address(UUID.fromString(uuidString));
            address.setStreet(street);
            address.setNumber(number);
            address.setCity(city);

            return address;
        }
    }
}
