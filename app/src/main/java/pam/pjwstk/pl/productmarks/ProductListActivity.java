package pam.pjwstk.pl.productmarks;

import android.support.v4.app.Fragment;

/**
 * Created by Boberkowy on 10.11.2017.
 */

public class ProductListActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {
        return CrimeListFragment();
    }
}
