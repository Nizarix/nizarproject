package com.example.nizarsproject.classes;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import com.example.nizarsproject.R;

import com.example.nizarsproject.Client.ProductInfo;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    List<Product> productList;
    int pid;
    String uid;
    View view;
    Context context;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int i) {

        view = LayoutInflater.from(context).inflate(R.layout.products_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

        // here we will find the position and start setting the output on our views



        String prodname = productList.get(position).getProdname();
        byte[] images = productList.get(position).getImageByte();
        Bitmap bm = BitmapFactory.decodeByteArray(images, 0, images.length);
        holder.imageOfProduct.setImageBitmap(bm);

        pid = productList.get(position).getPid();
        uid = FirebaseAuth.getInstance().getUid();
        holder.tvname.setText(prodname);



    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    // in order to make our views responsive we can implement onclicklistener on our recyclerview
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // here we will find the views on which we will inflate our data

        TextView tvname;
        ImageView imageOfProduct;

        public ViewHolder(View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.tvProductName);
            imageOfProduct = itemView.findViewById(R.id.img_product);
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), ProductInfo.class);
            intent.putExtra("id", productList.get(getLayoutPosition()).getPid() + "");
            v.getContext().startActivity(intent);
        }

    }
}

