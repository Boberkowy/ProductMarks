package pam.pjwstk.pl.productmarks.ProductActivity;

import android.support.v4.app.Fragment;

import pam.pjwstk.pl.productmarks.SingleFragmentActivity;

/**
 * Created by Boberkowy on 10.11.2017.
 */

public class ProductListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ProductListFragment();
    }
}
