package com.example.nizarsproject.classes;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import static com.example.nizarsproject.DataTable.TablesString.ProductTable.COLUMN_PRODUCT_BUYPRICE;
import static com.example.nizarsproject.DataTable.TablesString.ProductTable.COLUMN_PRODUCT_DESCRIPTION;
import static com.example.nizarsproject.DataTable.TablesString.ProductTable.COLUMN_PRODUCT_IMAGE;
import static com.example.nizarsproject.DataTable.TablesString.ProductTable.COLUMN_PRODUCT_NAME;
import static com.example.nizarsproject.DataTable.TablesString.ProductTable.COLUMN_PRODUCT_SALEPRICE;
import static com.example.nizarsproject.DataTable.TablesString.ProductTable.COLUMN_PRODUCT_STOCK;
import static com.example.nizarsproject.DataTable.TablesString.ProductTable.TABLE_PRODUCT;

public class product implements SqlInterface{

    //region Attribute
    protected String pid;
    protected String prodname;
    protected String proddisc;
    protected String prodimg;
    protected int stock;
    protected double saleprice;
    protected double buyprice;
    //endregion

    //region Constructors
    public product(String pid, String prodname, String proddisc, String prodimg, int stock, double saleprice, double buyprice){
        this.pid=pid;
        this.saleprice=saleprice;
        this.buyprice=buyprice;
        this.prodname=prodname;
        this.proddisc=proddisc;
        this.prodimg=prodimg;
        this.stock=stock;
    }
    //endregion

    //region Add,Delete,Update,Select Sql

    @Override
    public long Add(SQLiteDatabase db) {
        return 0;
    }

    @Override
    public int Delete(SQLiteDatabase db, int id) {
        return 0;
    }

    @Override
    public int Update(SQLiteDatabase db, int id) {
        return 0;
    }

    @Override
    public Cursor Select(SQLiteDatabase db) {
        return null;
    }
    //region Setter and Getter
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getProddisc() {
        return proddisc;
    }

    public void setProddisc(String proddisc) {
        this.proddisc = proddisc;
    }

    public String getProdimg() {
        return prodimg;
    }

    public void setProdimg(String prodimg) {
        this.prodimg = prodimg;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(double saleprice) {
        this.saleprice = saleprice;
    }

    public double getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(double buyprice) {
        this.buyprice = buyprice;
    }


    //endregion

    public static class product implements SqlInterface{

        //region Attribute
        protected int pid;
        protected String prodname;
        protected String proddisc;
        protected int stock;
        protected double saleprice;
        protected double buyprice;
        protected byte[] imageByte;
        //endregion

        //region Constructors
        public product(String prodname,String proddisc,int stock,double saleprice,double buyprice,byte[] image){
            this.saleprice=saleprice;
            this.buyprice=buyprice;
            this.prodname=prodname;
            this.proddisc=proddisc;
            this.stock=stock;
            this.imageByte = image;
        }
        //endregion

        //region Add,Delete,Update,Select Sql
        @Override
        public long Add(SQLiteDatabase db) {
            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(COLUMN_PRODUCT_NAME, prodname);
            values.put(COLUMN_PRODUCT_DESCRIPTION, proddisc);
            values.put(COLUMN_PRODUCT_BUYPRICE, buyprice);
            values.put(COLUMN_PRODUCT_SALEPRICE, saleprice);
            values.put(COLUMN_PRODUCT_STOCK, stock);
            values.put(COLUMN_PRODUCT_IMAGE, imageByte);


    // Insert the new row, returning the primary key value of the new row
            return db.insert(TABLE_PRODUCT, null, values);

        }

        @Override
        public int Delete(SQLiteDatabase db, int id) {
            String selection = BaseColumns._ID + " LIKE ?";
    // Specify arguments in placeholder order.
            String[] selectionArgs = {id+""};
    // Issue SQL statement.
            return db.delete(TABLE_PRODUCT, selection, selectionArgs);

        }

        @Override
        public int Update(SQLiteDatabase db, int id) {
            // New value for one column
            ContentValues values = new ContentValues();
            values.put(COLUMN_PRODUCT_NAME, prodname);
            values.put(COLUMN_PRODUCT_DESCRIPTION, proddisc);
            values.put(COLUMN_PRODUCT_BUYPRICE, buyprice);
            values.put(COLUMN_PRODUCT_SALEPRICE, saleprice);
            values.put(COLUMN_PRODUCT_STOCK, stock);
            values.put(COLUMN_PRODUCT_IMAGE, imageByte.toString());

    // Which row to update, based on the title
            String selection = BaseColumns._ID + " LIKE ?";
            String[] selectionArgs = { id+"" };

            return  db.update(
                    TABLE_PRODUCT,
                    values,
                    selection,
                    selectionArgs);

        }

        @Override
        public Cursor Select(SQLiteDatabase db) {
            String[] projection = {
                    BaseColumns._ID,
                    COLUMN_PRODUCT_NAME,
                    COLUMN_PRODUCT_DESCRIPTION,
                    COLUMN_PRODUCT_IMAGE,
                    COLUMN_PRODUCT_STOCK,
                    COLUMN_PRODUCT_SALEPRICE,
                    COLUMN_PRODUCT_BUYPRICE
            };
    // How you want the results sorted in the resulting Cursor
            String sortOrder =
                    BaseColumns._ID + " DESC";
            Cursor c = db.query(TABLE_PRODUCT,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    sortOrder);
            return c;
        }

        //endregion

        //region Setter and Getter
        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getProdname() {
            return prodname;
        }

        public void setProdname(String prodname) {
            this.prodname = prodname;
        }

        public String getProddisc() {
            return proddisc;
        }

        public void setProddisc(String proddisc) {
            this.proddisc = proddisc;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public double getSaleprice() {
            return saleprice;
        }

        public void setSaleprice(double saleprice) {
            this.saleprice = saleprice;
        }

        public double getBuyprice() {
            return buyprice;
        }

        public void setBuyprice(double buyprice) {
            this.buyprice = buyprice;
        }
        //endregion

    }
}
