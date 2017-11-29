package pam.pjwstk.pl.productmarks.Database;

/**
 * Created by Boberkowy on 04.11.2017.
 */

public class ProductDbSchema {
    public static final class ProductTable{
        public static final String NAME = "Products";

        public static final class Cols {
            public static final String UUID = "id";
            public static final String PRODUCT_NAME ="product_name";
            public static final String MARK = "mark";
            public static final String DESCRIPTION = "description";
            public static final String SHOP = "shop";
        }
    }
}
