package pam.pjwstk.pl.productmarks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.UUID;

import pam.pjwstk.pl.productmarks.Model.Product;

public class EditProductFragment extends Fragment {

    private static final String ARG_PRODUCT_ID = "product_id";

    private Product mProduct;
    private EditText mTitleField;
    private EditText mDesc;
    private Button mEditButton;

    public static EditProductFragment newInstance (UUID productId){
        Bundle args = new Bundle();

        args.putSerializable(ARG_PRODUCT_ID,productId);

        EditProductFragment fragment = new EditProductFragment();
        fragment.setArguments(args);

        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID productId = (UUID) getArguments().getSerializable(ARG_PRODUCT_ID);
        mProduct = ProductLab.get(getActivity()).getProduct(productId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit_product, container, false);

        createTitleField(v);
        createDescField(v);
        createMarkSpinner(v);
        createEditButton(v);
        return v;
    }


    private void createTitleField(View v) {

        mTitleField = (EditText) v.findViewById(R.id.product_title);
        mTitleField.setText(mProduct.getName());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mProduct.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // nothing
            }
        });
    }

    private void createDescField(View v) {

        mDesc = (EditText) v.findViewById(R.id.product_desc);
        mDesc.setText(mProduct.getDesc());
        mDesc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mProduct.setDesc(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //nothing
            }
        });
    }

    private void createMarkSpinner(View v) {
        final Spinner spinner = (Spinner) v.findViewById(R.id.marks_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext() ,R.array.marks_array, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        if(mProduct.getMark().equals("Polecam")){
            spinner.setSelection(0);
        }else {
            spinner.setSelection(1);
        }

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
    private void createEditButton(View v){
        mEditButton = (Button) v.findViewById(R.id.edit_button);

        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductLab.get(getActivity()).updateProduct(mProduct);
                getActivity().finish();
            }
        });
    }
}