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
    private static final String TOKEN ="token";
    private static final String REFRESHTOKEN = "refreshToken";
    private static final String USERTYPE = "userType";

//    private int idProject;
//    private String nameProject;
//    private String nameProvince;
//    private String nameDistrict;
//    private String timeBegin;
//    private int hour;
//    private int minute;

//    private static final String TABLE_NAME_TICKET ="ticket";
//
//    private static final String idProjectFromLocation ="id_project_from_location";
//    private static final String nameProjectFromLocation ="name_project_from_location";
//    private static final String nameProvinceFromLocation ="name_province_from_location";
//    private static final String nameDistrictFromLocation ="name_district_from_location";
//    private static final String timeBeginFromLocation ="time_begin_from_location";
//    private static final String hourFromLocation ="hour_from_location";
//    private static final String minuteFromLocation ="minute_from_location";
//
//    private static final String idProjectToLocation ="id_project_to_location";
//    private static final String nameProjectToLocation ="name_project_to_location";
//    private static final String nameProvinceToLocation ="name_province_to_location";
//    private static final String nameDistrictToLocation ="name_district_to_location";
//    private static final String timeBeginToLocation ="time_begin_to_location";
//    private static final String hourToLocation ="hour_to_location";
//    private static final String minuteToLocation ="minute_to_location";
//
//    private static final String idCoach = "idCoach";

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

        String sqlQueryTicket = "CREATE TABLE "+TABLE_NAME +" (" +
                TOKEN+" String primary key, "+
                REFRESHTOKEN + " TEXT, "+
                USERTYPE + " TEXT )";
//        String sqlQueryTicket
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
