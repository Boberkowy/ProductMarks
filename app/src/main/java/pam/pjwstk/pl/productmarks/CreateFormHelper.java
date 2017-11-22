package pam.pjwstk.pl.productmarks;

import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import pam.pjwstk.pl.productmarks.Model.Product;

/**
 * Created by Boberkowy on 22.11.2017.
 */

public abstract class CreateFormHelper   extends Fragment {



    public static void createTitleField(View v, final Product mProduct) {
        EditText mTitleField;

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

    public static void createDescField(View v, final Product mProduct) {
        EditText mDesc;

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

    public void createMarkSpinner(View v,final Product mProduct) {
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
}
