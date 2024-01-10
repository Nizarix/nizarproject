package com.example.nizarsproject.classes;

import static com.example.nizarsproject.DataTable.TablesString.CartTable.*;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class Cart implements SqlInterface{

    //region Attribute
    private int cartid;
    private String uid;
    private int prodid;



    private int amount;
    //endregion

    //region Constructors
    public Cart(int cid,String uid,int pid,int amount){
        cartid = cid;
        this.uid = uid;
        prodid = pid;
        this.amount=amount;
    }

    public Cart() {

    }
    //endregion

    //region Add,Delete,Update,Select Sql
    @Override
    public long Add(SQLiteDatabase db) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_ID, prodid);
        values.put(COLUMN_USER_ID, uid);
        values.put(COLUMN_AMOUNT, amount);
// Insert the new row, returning the primary key value of the new row
        return db.insert(TABLE_CART, null, values);
    }

    @Override
    public int Delete(SQLiteDatabase db, int id) {
        String selection = BaseColumns._ID + " LIKE ?";
// Specify arguments in placeholder order.
        String[] selectionArgs = {id+""};
// Issue SQL statement.
        return db.delete(TABLE_CART, selection, selectionArgs);
    }

    @Override
    public int Update(SQLiteDatabase db, int id) {
        // New value for one column
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_ID, prodid);
        values.put(COLUMN_USER_ID, uid);
        values.put(COLUMN_AMOUNT, amount);
        // Which row to update, based on the title
        String selection = BaseColumns._ID + " LIKE ?";
        String[] selectionArgs = { id+"" };

        return  db.update(
                TABLE_CART,
                values,
                selection,
                selectionArgs);
    }

    @Override
    public Cursor Select(SQLiteDatabase db) {
        String[] projection = {
                BaseColumns._ID,
                COLUMN_PRODUCT_ID,
                COLUMN_USER_ID,
                COLUMN_AMOUNT
        };
// How you want the results sorted in the resulting Cursor
        String sortOrder =
                BaseColumns._ID + " DESC";
        Cursor c = db.query(TABLE_CART,
                projection,
                null,
                null,
                null,
                null,
                sortOrder);
        return c;
    }
    public Cursor SelectByUserId(SQLiteDatabase db, String userid) {
        String[] projection = {
                BaseColumns._ID,
                COLUMN_PRODUCT_ID,
                COLUMN_USER_ID,
                COLUMN_AMOUNT
        };
        String selection = BaseColumns._ID + " = ?";
        String[] selectionArgs = { userid};

// How you want the results sorted in the resulting Cursor
        Cursor c = db.query(TABLE_CART,
                projection,
                null,
                null,
                null,
                null,
                null);
        return c;
    }

    //endregion

    //region Getter and Setter
    public int getCartid() {
        return cartid;
    }

    public void setCartid(int cartid) {
        this.cartid = cartid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getProdid() {
        return prodid;
    }

    public void setProdid(int prodid) {
        this.prodid = prodid;
    }
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    //endregion
}
