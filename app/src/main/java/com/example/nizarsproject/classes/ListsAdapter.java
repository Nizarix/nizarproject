package com.example.nizarsproject.classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.lang.UProperty;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nizarsproject.R;

public class ListsAdapter extends BaseAdapter {
    Context context;
    Product[] prudctlist;
    LayoutInflater layoutInflater;
    public ListsAdapter(Context context,Product[] prudctlist){
        this.context = context;
        this.prudctlist = prudctlist;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return prudctlist.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.activity_list_view,null);
        TextView textView = view.findViewById(R.id.productTV);
        ImageView image = view.findViewById(R.id.imImage);
        textView.setText(prudctlist[i].toString());
        byte[] im = prudctlist[i].getImageByte();
        Bitmap bm = BitmapFactory.decodeByteArray(im, 0, im.length);
        image.setImageBitmap(bm);

        return view;
    }
}
