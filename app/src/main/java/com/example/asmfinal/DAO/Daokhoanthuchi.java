package com.example.asmfinal.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asmfinal.Model.KhoanThuChi;
import com.example.asmfinal.SQL.Dpheper;

import java.util.ArrayList;

public class Daokhoanthuchi {
    Dpheper tc;
    public SQLiteDatabase db;
    public Daokhoanthuchi(Context context){
        tc = new Dpheper(context);
    }

    public ArrayList<KhoanThuChi> readAll(){
        ArrayList<KhoanThuChi> datakhoan = new ArrayList<>();
        SQLiteDatabase db = tc.getReadableDatabase();
        Cursor cs = db.query("KHOAN_TC",null,null,null,null,null,null);
        cs.moveToFirst();
        while ((!cs.isAfterLast())){
            String matc =cs.getString(0);
            String tentc =cs.getString(1);
            String ngay =cs.getString(2);
            double tien =cs.getDouble(3);
            String ghichu =cs.getString(4);
            String maloai =cs.getString(5);
            datakhoan.add(new KhoanThuChi(matc,tentc,ngay,tien,ghichu,maloai));
            cs.moveToNext();

        }cs.close();
        return datakhoan;
    }
    public boolean insert(KhoanThuChi khoan){
        SQLiteDatabase db = tc.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TENKHOANTC",khoan.getTentc());
        contentValues.put("NGAY",khoan.getNgay());
        contentValues.put("TIEN",khoan.getTien());
        contentValues.put("GhiChu",khoan.getGhichu());
        contentValues.put("MALOAI",khoan.getMaloai());
        long insert = db.insert("KHOAN_TC",null,contentValues);
        return insert>0;
    }
    public boolean update(String makhoan,String tenkhoantc ,String ngay, Double tien, String ghichu, String maloai){
        db= tc.getReadableDatabase();
        ContentValues values =  new ContentValues();
        values.put("TENKHOANTC",tenkhoantc);
        values.put("NGAY",ngay);
        values.put("TIEN",tien);
        values.put("GhiChu",ghichu);
        values.put("MALOAI",maloai);
        int update = db.update("KHOAN_TC",values,"MATC=?",new String[]{makhoan});
        return update>0;
    }
    public boolean delete(String makhoan){
        db= tc.getReadableDatabase();
        int xoa = db.delete("KHOAN_TC","MATC=?",new String[]{makhoan});
        return xoa>0;
    }
}
