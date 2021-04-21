package com.example.verexe.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.verexe.model.AuthResponse;
import com.example.verexe.model.UserType;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="manageSomething";

    private static final String TABLE_NAME ="auth";
//    private static final String MA ="ma";
    private static final String TOKEN ="token";
    private static final String REFRESHTOKEN = "refreshToken";
    private static final String USERTYPE = "userType";
    private final Context context;
    public DBManager(Context context) {
        super(context, DATABASE_NAME,null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE "+TABLE_NAME +" (" +
                TOKEN+" String primary key, "+
                REFRESHTOKEN + " TEXT, "+
                USERTYPE + " TEXT )";
        db.execSQL(sqlQuery);
    }

    public void addAuth(AuthResponse authResponse){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TOKEN, authResponse.getToken());
        values.put(REFRESHTOKEN, authResponse.getRefreshToken());
        values.put(USERTYPE, authResponse.getUserType().toString());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public AuthResponse getAuth() {
        List<AuthResponse> authResponseList = new ArrayList<AuthResponse>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                AuthResponse authResponse = new AuthResponse();

                authResponse.setToken(cursor.getString(0));
                authResponse.setRefreshToken(cursor.getString(1));
                authResponse.setUserType(UserType.valueOf(cursor.getString(2)));

                return authResponse;
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return null;
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
