
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

import com.example.nizarsproject.classes.Product;
import com.example.nizarsproject.classes.ProductAdapter;
import com.example.nizarsproject.DataTable.DBHelper;
import com.example.nizarsproject.R;


import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    List<Product> productList,productList_3;
    RecyclerView recyclerView,recyclerView_top3;
    ProductAdapter mAdapter,mAdapter_3;
    DBHelper dbHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView_top3 = view.findViewById(R.id.rec_view_meals_popular);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        recyclerView_top3.setLayoutManager(new LinearLayoutManager(null));
        productList = new ArrayList<>();
        productList_3 = new ArrayList<>();
        dbHelper = new DBHelper(inflater.getContext());
        dbHelper = dbHelper.OpenReadAble();
        Product p = new Product(),p2=new Product();
        Cursor c = p.Select(dbHelper.getDb());
        c.moveToFirst();
        int count = 0;
        while(!c.isAfterLast()){
            count++;
            p2.setPid(c.getInt(c.getColumnIndexOrThrow(BaseColumns._ID)));
            p2.setProdname(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_NAME)));
            p2.setProddisc(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_DESCRIPTION)));
            p2.setBuyprice(c.getDouble(c.getColumnIndexOrThrow(COLUMN_PRODUCT_BUYPRICE)));
            p2.setSaleprice(c.getDouble(c.getColumnIndexOrThrow(COLUMN_PRODUCT_SALEPRICE)));
            p2.setStock(c.getInt(c.getColumnIndexOrThrow(COLUMN_PRODUCT_STOCK)));
            p2.setImageByte(c.getBlob(c.getColumnIndexOrThrow(COLUMN_PRODUCT_IMAGE)));
            productList.add(p2);
            if(count < 4){
                productList_3.add(p2);
            }
            c.moveToNext();
            p2=new Product();
        }
        dbHelper.Close();
        // adapter
        mAdapter = new ProductAdapter(inflater.getContext(), productList);
        mAdapter_3 = new ProductAdapter(inflater.getContext(), productList_3);
        recyclerView.setAdapter(mAdapter);
        recyclerView_top3.setAdapter(mAdapter_3);
        // Inflate the layout for this fragment
        return  view;
    }
}