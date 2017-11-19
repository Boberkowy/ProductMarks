package pam.pjwstk.pl.productmarks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.UUID;

import pam.pjwstk.pl.productmarks.Model.Product;


public class ProductFragment extends Fragment{

    private static final String ARG_PRODUCT_ID = "product_id";

    private Product mProduct;
    private TextView mName;
    private TextView mMark;
    private TextView mDesc;

    public static ProductFragment newInstance (UUID productId){
        Bundle args = new Bundle();

        args.putSerializable(ARG_PRODUCT_ID,productId);

        ProductFragment fragment = new ProductFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID productId = (UUID) getArguments()
                        .getSerializable(ARG_PRODUCT_ID);
        mProduct = ProductLab.get(getActivity()).getProduct(productId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_product,container,false);

        getNameText(v);
        getMarkText(v);
        getDescText(v);

        return v;
    }

    private void getNameText(View v){
        mName = (TextView) v.findViewById(R.id.product_title);
        mName.setText(mProduct.getName());
    }

    private void getMarkText(View v){
        mMark = (TextView) v.findViewById(R.id.product_mark);
        mMark.setText(mProduct.getMark());
    }

    private void getDescText (View v){
        mDesc = (TextView) v.findViewById(R.id.product_desc);
        mDesc.setText(mProduct.getDesc());
    }
}
