package pam.pjwstk.pl.productmarks;

import android.support.v4.app.Fragment;

/**
 * Created by Boberkowy on 05.11.2017.
 */

public class EditProductActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new EditProductFragment();
    }
}
