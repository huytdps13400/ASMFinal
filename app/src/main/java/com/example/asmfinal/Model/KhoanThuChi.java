package com.example.asmfinal.Model;

public class KhoanThuChi {
    private String matc;
    private String tentc;
    private String ngay;
    private double tien;
    private String ghichu;
    private String maloai;

    public KhoanThuChi() {
    }

    public KhoanThuChi(String matc, String tentc, String ngay, double tien, String ghichu, String maloai) {
        this.matc = matc;
        this.tentc = tentc;
        this.ngay = ngay;
        this.tien = tien;
        this.ghichu = ghichu;
        this.maloai = maloai;
    }

    public String getMatc() {
        return matc;
    }

    public void setMatc(String matc) {
        this.matc = matc;
    }

    public String getTentc() {
        return tentc;
    }

    public void setTentc(String tentc) {
        this.tentc = tentc;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public double getTien() {
        return tien;
    }

    public void setTien(double tien) {
        this.tien = tien;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getMaloai() {
        return maloai;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
    }
}
