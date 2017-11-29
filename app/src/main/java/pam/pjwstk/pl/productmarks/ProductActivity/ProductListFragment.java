package pam.pjwstk.pl.productmarks.ProductActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pam.pjwstk.pl.productmarks.Model.Product;
import pam.pjwstk.pl.productmarks.R;


public class ProductListFragment extends Fragment {

    private RecyclerView mProductRecyclerView;
    private ProductAdapter mAdapter;

    private TextView mTitleTextView;
    private TextView mMarkTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_product_list, container, false);

        mProductRecyclerView = (RecyclerView) v.findViewById(R.id.product_recycler_view);
        mProductRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_product_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_product:
                Product product = new Product();

                Intent intent = AddProductActivity.newIntent(getActivity(), product.getId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateUI() {
        ProductLab productLab = ProductLab.get(getActivity());
        List<Product> products = productLab.getProducts();

        if(mAdapter == null){
            mAdapter = new ProductAdapter(products);
            mProductRecyclerView.setAdapter(mAdapter);
        }else {
            mAdapter.setProducts(products);
            mAdapter.notifyDataSetChanged();
        }
    }
        private class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            private Product mProduct;

            public void bind(Product product){
                mProduct = product;
                mTitleTextView.setText(mProduct.getName());
                mMarkTextView.setText(mProduct.getMark());
            }
            public ProductHolder(LayoutInflater inflater, ViewGroup parent) {
                super(inflater.inflate(R.layout.list_item_product, parent, false));

                mTitleTextView = (TextView) itemView.findViewById(R.id.product_title);
                mMarkTextView = (TextView) itemView.findViewById(R.id.product_mark);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                Intent intent = ProductActivity.newIntent(getActivity(),mProduct.getId());
                startActivity(intent);
            }
        }

    private class ProductAdapter extends RecyclerView.Adapter<ProductHolder> {

        private List<Product> mProducts;

        public ProductAdapter(List<Product> products) {
            mProducts = products;
        }

        @Override
        public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new ProductHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(ProductHolder holder, int position) {
            Product product = mProducts.get(position);
            holder.bind(product);
        }

        @Override
        public int getItemCount() {
            return mProducts.size();
        }


        public void setProducts(List<Product> products){
            mProducts = products;
        }
    }
}
