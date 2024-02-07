package com.example.nizarsproject.classes;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nizarsproject.DataTable.DBHelper;
import com.example.nizarsproject.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.nizarsproject.DataTable.TablesString.ProductTable.*;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    List<Cart> cartList;
    int pid;
    String uid;
    View view,v;
    Context context;
    int i = 1;
    int max ;
    double sum ;
    ViewHolder h;
    List<Double> listprice;

    public CartAdapter(View v,Context context, List<Cart> cartList) {
        this.context = context;
        this.cartList = cartList;
        this.v = v;
        sum=0;
        listprice = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int i) {

        view = LayoutInflater.from(context).inflate(R.layout.each_cart, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        h=holder;
        // here we will find the position and start setting the output on our views
        int pid = cartList.get(position).getProdid();
        int amount = cartList.get(position).getAmount();
        Product p = new Product();
        DBHelper dbHelper = new DBHelper(view.getContext());
        dbHelper.OpenReadAble();
        Cursor c = p.SelectById(dbHelper.getDb(),pid+"");
        c.moveToFirst();
        if(c!=null){
            max = c.getInt(c.getColumnIndexOrThrow(COLUMN_PRODUCT_STOCK));
            String nameOfProduct = c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_NAME));
            double price =c.getDouble(c.getColumnIndexOrThrow(COLUMN_PRODUCT_SALEPRICE));
            listprice.add(price);
            byte[] images = c.getBlob(c.getColumnIndexOrThrow(COLUMN_PRODUCT_IMAGE));
            Bitmap bm = BitmapFactory.decodeByteArray(images, 0 ,images.length);
            sum+=price*amount;
            holder.totalprice.setText(sum + "$");
            holder.tvTypeOfProduct.setText(nameOfProduct);
            holder.tvJewelryPrice.setText(price+"");
            holder.imageOfProduct.setImageBitmap(bm);
            holder.tvAmount.setText(amount+"");
        }
        dbHelper.Close();
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    // in order to make our views responsive we can implement onclicklistener on our recyclerview
    public class ViewHolder extends RecyclerView.ViewHolder{

        // here we will find the views on which we will inflate our data

        TextView tvTypeOfProduct, tvJewelryPrice,tvAmount,totalprice;
        ImageView imageOfProduct,deleteitem;
        ImageButton plusquantity,minusquantity;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTypeOfProduct = itemView.findViewById(R.id.eachCartItemName);
            tvJewelryPrice = itemView.findViewById(R.id.eachCartItemPriceTv);
            imageOfProduct = itemView.findViewById(R.id.eachCartItemIV);
            tvAmount = itemView.findViewById(R.id.eachCartItemQuantityTV);
            totalprice = v.findViewById(R.id.cartFragmentTotalPriceTv);
            deleteitem = itemView.findViewById(R.id.eachCartItemDeleteBtn);
            deleteitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBHelper dbHelper = new DBHelper(context);
                    dbHelper.OpenWriteAble();
                    sum = 0;
                    cartList.get(getPosition()).Delete(dbHelper.getDb(),cartList.get(getPosition()).getCartid());
                    cartList.remove(getPosition());
                    notifyDataSetChanged();
                    dbHelper.Close();

                }
            });
            minusquantity = itemView.findViewById(R.id.eachCartItemMinusQuantityBtn);
            minusquantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(i>1){
                        tvAmount.setText(String.valueOf(--i));
                        sum-=Double.parseDouble(tvJewelryPrice.getText().toString());
                        totalprice.setText(sum + "₪");
                    }
                }
            });
            plusquantity = itemView.findViewById(R.id.eachCartItemAddQuantityBtn);
            plusquantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(i<max){
                        tvAmount.setText(String.valueOf(++i));
                        sum+=Double.parseDouble(tvJewelryPrice.getText().toString());
                        totalprice.setText(sum + "₪");
                    }
                }
            });

        }

    }


}


