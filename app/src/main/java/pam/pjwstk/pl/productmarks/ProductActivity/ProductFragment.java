package pam.pjwstk.pl.productmarks.ProductActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.UUID;

import pam.pjwstk.pl.productmarks.MapActivity.MapsActivity;
import pam.pjwstk.pl.productmarks.Model.Product;
import pam.pjwstk.pl.productmarks.R;

import static android.view.View.*;


public class ProductFragment extends Fragment{

    private static final String ARG_PRODUCT_ID = "product_id";

    private Product mProduct;
    private TextView mName;
    private TextView mMark;
    private TextView mDesc;
    private TextView mShop;
    private Button mEditButton;
    private Button mDeleteButton;

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
        getShop(v);
        editButtonHandler(v);
        deleteButtonHandler(v);
        findShopButtonHandler(v);
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

    private void getShop(View v){
        mShop = (TextView) v.findViewById(R.id.shop_name);
        mShop.setText(mProduct.getShop());
    }

    private void editButtonHandler(View v){
        mEditButton = (Button) v.findViewById(R.id.edit_button);

        mEditButton.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = EditProductActivity.newIntent(getActivity(),mProduct.getId());
                startActivity(intent);
                getActivity().finish();

            }
        });
    }

    private void deleteButtonHandler(View v){
        mDeleteButton = (Button) v.findViewById(R.id.delete_button);

        mDeleteButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductLab.get(getActivity()).deleteProduct(mProduct);
                getActivity().finish();
            }
        });
    }

    private void findShopButtonHandler(View v){
        Button findShopButton  = (Button) v.findViewById(R.id.find_button);

        findShopButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MapsActivity.newIntent(getActivity(),mProduct.getShop());
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
}
