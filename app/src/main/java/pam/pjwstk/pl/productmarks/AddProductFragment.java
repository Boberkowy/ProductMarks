package pam.pjwstk.pl.productmarks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.UUID;

import pam.pjwstk.pl.productmarks.Model.Product;

/**
 * Created by Boberkowy on 22.11.2017.
 */

public class AddProductFragment extends Fragment{

    private static final String ARG_PRODUCT_ID = "product_id";

    private Product mProduct;
    private Button mAddButton;

    public static AddProductFragment newInstance (UUID productId){
        Bundle args = new Bundle();

        args.putSerializable(ARG_PRODUCT_ID,productId);

        AddProductFragment fragment = new AddProductFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProduct = new Product();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_product,container,false);

        CreateFormHelper.createTitleField(v,mProduct);
        CreateFormHelper.createDescField(v,mProduct);
        createMarkSpinner(v);
        createAddButton(v);
        return v;
    }

    @Override public void onPause() {
        super.onPause();
        ProductLab.get(getActivity()).updateProduct(mProduct);
    }
    private void createMarkSpinner(View v) {
        final Spinner spinner = (Spinner) v.findViewById(R.id.marks_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext() ,R.array.marks_array, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        mProduct.setMark(spinner.getSelectedItem().toString());
                        break;
                    case 1:
                        mProduct.setMark(spinner.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mProduct.setMark(getString(R.string.marks_none));
            }
        });
    }

    private void createAddButton(View v){
        mAddButton = (Button) v.findViewById(R.id.add_button);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductLab.get(getActivity()).addProduct(mProduct);
                getActivity().finish();
            }
        });
    }
}
