package pam.pjwstk.pl.productmarks;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by Boberkowy on 19.11.2017.
 */

public class ProductActivity extends SingleFragmentActivity {

    private static final String EXTRA_PRODUCT_ID = "pam.pjwstk.pl.productmarks.product_id";

    public static Intent newIntent(Context packageContext, UUID productId) {

        Intent intent = new Intent(packageContext, ProductActivity.class);
        intent.putExtra(EXTRA_PRODUCT_ID, productId);

        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID productId = (UUID) getIntent().getSerializableExtra(EXTRA_PRODUCT_ID);

        return ProductFragment.newInstance(productId);
    }
}