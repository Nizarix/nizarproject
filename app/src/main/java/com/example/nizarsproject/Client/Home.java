
package com.example.nizarsproject.Client;
import static com.example.nizarsproject.DataTable.TablesString.ProductTable.*;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nizarsproject.classes.Product;
import com.example.nizarsproject.classes.ProductsAdapter;
import com.example.nizarsproject.DataTable.DBHelper;
import com.example.nizarsproject.R;


import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    List<Product> productList;
    RecyclerView recyclerView;
    ProductsAdapter mAdapter;
    DBHelper dbHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        productList = new ArrayList<>();
        dbHelper = new DBHelper(inflater.getContext());
        dbHelper = dbHelper.OpenReadAble();
        Product p = new Product(),p2=new Product();
        Cursor c = p.Select(dbHelper.getDb());
        c.moveToFirst();
        while(!c.isAfterLast()){
            p2 = new Product(c.getInt(c.getColumnIndexOrThrow(_ID)), p.setPid(c.getInt(c.getColumnIndexOrThrow(BaseColumns._ID)));
            p2.setProdname(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_NAME)));
            p2.setProddisc(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_DESCRIPTION)));
            p2.setBuyprice(c.getDouble(c.getColumnIndexOrThrow(COLUMN_PRODUCT_BUYPRICE)));
            p2.setSaleprice(c.getDouble(c.getColumnIndexOrThrow(COLUMN_PRODUCT_SALEPRICE)));
            p2.setStock(c.getInt(c.getColumnIndexOrThrow(COLUMN_PRODUCT_STOCK)));
            p2.setImageByte(c.getBlob(c.getColumnIndexOrThrow(COLUMN_PRODUCT_IMAGE)));
            productList.add(p2);
            c.moveToNext();
        }
        // adapter
        mAdapter = new ProductsAdapter(inflater.getContext(), productList);
        recyclerView.setAdapter(mAdapter);
        // Inflate the layout for this fragment
        return  view;
    }
}