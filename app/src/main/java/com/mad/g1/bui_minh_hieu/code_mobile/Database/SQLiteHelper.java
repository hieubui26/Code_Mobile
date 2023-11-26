package com.mad.g1.bui_minh_hieu.code_mobile.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import com.mad.g1.bui_minh_hieu.code_mobile.Model.Matches;
import com.mad.g1.bui_minh_hieu.code_mobile.Model.Member;
import com.mad.g1.bui_minh_hieu.code_mobile.Model.MemberStatistic;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Demo_code.db";
    private static final Integer DB_VERSION = 1;
    private static final String TABLE_NAME = "matches";
    private static final String TABLE_MEMBER = "member";
    private static final String COLUMN_ID = "_id";

    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    public SQLiteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE matches(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "description TEXT, " +
                "date TEXT, " +
                "level TEXT, " +
                "status BOOLEAN" +
                ")";
        sqLiteDatabase.execSQL(sql);
        String createTable = "CREATE TABLE " + TABLE_MEMBER +
                "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT NOT NULL, " +
                COLUMN_PASSWORD + " TEXT NOT NULL " +
                ")";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addMember(Member member) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, member.getUsername());
        values.put(COLUMN_PASSWORD, member.getPassword());
        db.insert(TABLE_MEMBER, null, values);
        db.close();
    }

    public boolean checkLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(TABLE_MEMBER, null, selection, selectionArgs, null, null, null);

        boolean loginSuccessful = cursor.moveToFirst();
        cursor.close();
        db.close();
        return loginSuccessful;
    }

    public List<Matches> getAll() {
        List<Matches> jobList = new ArrayList<>();
        String orderClause = "date DESC";
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null, null, null, null, orderClause);
        while (cursor.moveToNext()) {
            Matches job = new Matches();
            job.setId(cursor.getInt(0));
            job.setName(cursor.getString(1));
            job.setDescription(cursor.getString(2));
            job.setDate(cursor.getString(3));
            job.setLevel(cursor.getString(4));
            job.setStatus(cursor.getInt(5) != 0);
            jobList.add(job);
        }
        return jobList;
    }

    public long add(Matches job) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", job.getName());
        contentValues.put("description", job.getDescription());
        contentValues.put("date", job.getDate());
        contentValues.put("level", job.getLevel());
        contentValues.put("status", job.getStatus());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public long update(Matches job) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", job.getName());
        contentValues.put("description", job.getDescription());
        contentValues.put("date", job.getDate());
        contentValues.put("level", job.getLevel());
        contentValues.put("status", job.getStatus());
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(job.getId())};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.update(TABLE_NAME, contentValues, whereClause, whereArgs);
    }

    public long delete(Integer id) {
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, whereClause, whereArgs);
    }

    public List<Matches> searchByName(String key) {
        List<Matches> jobList = new ArrayList<>();
        String orderClause = "date ASC";
        String whereClause = "name like ?";
        String[] whereArgs = {"%" + key + "%"};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, whereClause, whereArgs, null, null, orderClause);
        while (cursor.moveToNext()) {
            Matches job = new Matches();
            job.setId(cursor.getInt(0));
            job.setName(cursor.getString(1));
            job.setDescription(cursor.getString(2));
            job.setDate(cursor.getString(3));
            job.setLevel(cursor.getString(4));
            job.setStatus(cursor.getInt(5) != 0);
            jobList.add(job);
        }
        return jobList;
    }

    public List<MemberStatistic> statisticByStatus(String key) {
        List<MemberStatistic> jobStatisticList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = "SELECT j.level, COUNT(j.id) AS total FROM job j WHERE (LOWER(?) = LOWER('Tat ca') OR LOWER(level) = LOWER(?)) GROUP BY level ORDER BY total DESC";
        String[] whereArgs = {key, key};
        Cursor cursor = sqLiteDatabase.rawQuery(sql, whereArgs);
        while (cursor.moveToNext()) {
            String status = cursor.getString(0);
            Integer total = cursor.getInt(1);
            MemberStatistic jobStatistic = new MemberStatistic(total, status);
            jobStatisticList.add(jobStatistic);
        }
        return jobStatisticList;
    }
}