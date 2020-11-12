package com.e.esayVan;


import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class Admin_CustomListView extends ArrayAdapter<String>{

    private String[] pname;
    private Activity context;
    Bitmap bitmap;

    public Admin_CustomListView(Activity context, String[] pname) {
        super(context, R.layout.admin_layout,pname);
        this.context=context;
        this.pname=pname;
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent){
        View r=convertView;
        ViewHolder viewHolder=null;
        if(r==null){
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.admin_layout,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder)r.getTag();

        }

        viewHolder.tvw1.setText(pname[position]);

        return r;
    }

    class ViewHolder{

        TextView tvw1;

        ViewHolder(View v){
            tvw1=(TextView)v.findViewById(R.id.pname);
        }

    }





}
