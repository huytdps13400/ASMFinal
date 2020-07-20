package com.example.asmfinal.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmfinal.DAO.Daokhoanthuchi;
import com.example.asmfinal.DAO.Daoloaithuchi;
import com.example.asmfinal.Model.KhoanThuChi;
import com.example.asmfinal.Model.LoaiThuChi;
import com.example.asmfinal.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.asmfinal.TabFragment.TabFragmentkhoanthu.rcv;


public class TabKhoanThuAdapter extends RecyclerView.Adapter<TabKhoanThuViewHolder> {
    Context context;
    ArrayList<KhoanThuChi> datakhoanthu;
    ArrayList<LoaiThuChi> dataloaithu;
    Daokhoanthuchi daokhoanthuchi;
    Daoloaithuchi daoloaithuchi;
    TabKhoanThuAdapter tabKhoanThuAdapter;
    TextView edtsuangay;
    Adapter_Spinner adapter_spinner;

    public TabKhoanThuAdapter(Context context, ArrayList<KhoanThuChi> datakhoanthu) {
        this.context = context;
        this.datakhoanthu = datakhoanthu;
    }

    @NonNull
    @Override
    public TabKhoanThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemkhoanthu,parent,false);
        return new TabKhoanThuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TabKhoanThuViewHolder holder, final int position) {
        holder.txttenkhoan.setText(datakhoanthu.get(position).getTentc());
        holder.txtngay.setText(datakhoanthu.get(position).getNgay());
        holder.txttien.setText(String.valueOf(datakhoanthu.get(position).getTien()));
        holder.txtghichu.setText(datakhoanthu.get(position).getGhichu());
        Daoloaithuchi daoloaithuchi = new Daoloaithuchi(context);
        dataloaithu = new ArrayList<>();
        dataloaithu = daoloaithuchi.readAll();
        int idmaloai = Integer.parseInt(datakhoanthu.get(position).getMaloai());
        holder.txtmaloai.setText(dataloaithu.get(idmaloai-1).getTenloai()+"-"+dataloaithu.get(idmaloai-1).getTrangthai());
        holder.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater =((Activity)context).getLayoutInflater();
                View view1 = inflater.inflate(R.layout.dialogeditkhoanthu,null);
                builder.setView(view1);
                final EditText edtsuatenkhoanthu = view1.findViewById(R.id.edtsuatenkhoanthu);
                final Spinner spinersuamaloai = view1.findViewById(R.id.spinersuamaloai);
                final EditText edtsuatien = view1.findViewById(R.id.edtsuatien);
                final EditText edtsuaghichu = view1.findViewById(R.id.edtsuaghichu);
                edtsuangay = view1.findViewById(R.id.edtsuangay);
                Button btnsuakhoanthu = view1.findViewById(R.id.btnsuakhoanthu);
                Button btnhuysua = view1.findViewById(R.id.btnhuysua);


                Daoloaithuchi daoloai =new Daoloaithuchi(context);
                dataloaithu = daoloai.readAll();
                adapter_spinner = new Adapter_Spinner(context,dataloaithu);
                spinersuamaloai.setAdapter(adapter_spinner);
                int vi_tri = Integer.parseInt(datakhoanthu.get(position).getMaloai());
                Toast.makeText(context, "Mã"+vi_tri, Toast.LENGTH_SHORT).show();
                spinersuamaloai.setSelection(vi_tri-1);
                edtsuaghichu.setText(datakhoanthu.get(position).getGhichu());
                edtsuatenkhoanthu.setText(datakhoanthu.get(position).getTentc());
                edtsuatien.setText(String.valueOf(datakhoanthu.get(position).getTien()));
                edtsuangay.setText(datakhoanthu.get(position).getNgay());
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                btnsuakhoanthu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String matc = datakhoanthu.get(position).getMatc();
                        String tenkhoan = edtsuatenkhoanthu.getText().toString().trim();
                        double tien = Double.parseDouble(edtsuatien.getText().toString().trim());
                        String ghichu = edtsuaghichu.getText().toString().trim();
                        int index = spinersuamaloai.getSelectedItemPosition();
//                        Toast.makeText(context, "Ma index"+index, Toast.LENGTH_SHORT).show();
                        datakhoanthu = new ArrayList<KhoanThuChi>();
                        String idloai = dataloaithu.get(index).getMaloai();
                        String ngay = edtsuangay.getText().toString();
                        daokhoanthuchi = new Daokhoanthuchi(context);
                        daokhoanthuchi.update(matc,tenkhoan,ngay,tien,ghichu,idloai);
                        Toast.makeText(context, "CẬP NHẬT THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                        showduelieu();
                        alertDialog.cancel();
                    }
                });
                edtsuangay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        datapicker();
                    }
                });
                btnhuysua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                    }
                });
            }
        });
    }
    private void datapicker(){
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year2, int month2, int dayOfMonth2) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                calendar.set(year2,month2,dayOfMonth2);
                String date = dateFormat.format(calendar.getTime());
                edtsuangay.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();
    }
    public void showduelieu(){
        datakhoanthu.clear();
        datakhoanthu = daokhoanthuchi.readAll();
        tabKhoanThuAdapter = new TabKhoanThuAdapter(context,datakhoanthu);
        rcv.setAdapter(tabKhoanThuAdapter);
    }
    @Override
    public int getItemCount() {
        return datakhoanthu.size();
    }
}

