package com.e.esayVan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParentChildrenAdapter extends RecyclerView.Adapter<ParentChildrenAdapter.ChildrenViewHolder> {

    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<ParentChild> childlist;
    String no,parentNIC,mode,childNo;
    String firstName,lastName, grade, school,pick,drop,vehicleNo,ownerID;
    String userName;


    String URL_DELETE="https://10.0.2.2/easyvan/removeChildVan.php";
    String URL_CHECK="https://10.0.2.2/easyvan/checkChildStatus.php";

    //getting the context and product list with constructor
    public ParentChildrenAdapter(Context mCtx, List<ParentChild> childlist, String strNic,String vehicleNo,String ownerID, String mode) {
        this.mCtx = mCtx;
        this.childlist = childlist;
        this.parentNIC=strNic;
        this.mode=mode;
        this.vehicleNo=vehicleNo;
        this.ownerID=ownerID;
        // this.monChildListener= onChildListener;
    }

    @Override
    public ParentChildrenAdapter.ChildrenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.parent_child_details_layout, null);
        return new ParentChildrenAdapter.ChildrenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentChildrenAdapter.ChildrenViewHolder holder, final int position) {
        final ParentChild children = childlist.get(position);


        //binding the data with the viewholder views
        holder.textViewFirstName.setText(String.valueOf(children.getFirstName())+" "+String.valueOf(children.getLastName()));
        holder.textViewGrade.setText(String.valueOf("Grade: "+children.getGrade()));
        holder.textViewSchool.setText(String.valueOf("School: "+children.getSchool()));
        holder.textViewPickupLocation.setText(String.valueOf("Pick up location: "+children.getPickupLocation()));
        holder.textViewDropoffLocation.setText(String.valueOf("Drop off location: "+children.getDropoffLocation()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //when viewing more child details
                if(mode.equals("view")) {
                    Intent intent = new Intent(mCtx, ParentDetails.class);
                    no = String.valueOf(children.getChildNo());
                    intent.putExtra("childNumber", no);//passing child no to the next view
                    intent.putExtra("parentNIC", parentNIC);//passing parent nic no to the next view
                    mCtx.startActivity(intent);
                }
                //when requesting for a school van
                else if(mode.equals("request")){

                    childNo=String.valueOf(children.getChildNo());
                    firstName=String.valueOf(children.getFirstName());
                    lastName=String.valueOf(children.getLastName());
                    grade=String.valueOf(children.getGrade());
                    school=String.valueOf(children.getSchool());
                    pick=String.valueOf(children.getPickupLocation());
                    drop=String.valueOf(children.getDropoffLocation());
                    //check if child already got a van
                    check();
                }else if(mode.equals("select")){

                }

            }
        });

    }

    public void removeChildVan(){

        HttpsTrustManager.allowAllSSL();
        StringRequest request = new StringRequest(Request.Method.POST, URL_DELETE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(mCtx, "Your child is removed from the school van",Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mCtx, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("no",childNo);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
        requestQueue.add(request);
    }

    public  void check(){

        HttpsTrustManager.allowAllSSL();
        StringRequest request = new StringRequest(Request.Method.POST,URL_CHECK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equals("has a van")){

                            //ask to delete child's existing school van
                            AlertDialog.Builder builder= new AlertDialog.Builder(mCtx);
                            builder.setMessage("Your child is already assigned to a school van.Do you want to remove child from the school van?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //remove child from the school van
                                    removeChildVan();

                                }
                            }).setNegativeButton("cancel",null);

                            AlertDialog alert =builder.create();
                            alert.show();
                        }else{

                            SessionManagement sessionManagement = new SessionManagement(mCtx);
                            userName = sessionManagement.getUserName();

                            Intent intent = new Intent(mCtx,ParentRequest.class);

                            intent.putExtra("ParentUsername",userName);
                            intent.putExtra("childNo",childNo);
                            intent.putExtra("firstName",firstName);
                            intent.putExtra("lastName",lastName);
                            intent.putExtra("grade",grade);
                            intent.putExtra("school",school);
                            intent.putExtra("pick",pick);
                            intent.putExtra("drop",drop);
                            intent.putExtra("vehicleNo",vehicleNo);

                            mCtx.startActivity(intent);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //show child details
                Toast.makeText(mCtx,"error",Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("no",childNo);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
        requestQueue.add(request);
    }


    @Override
    public int getItemCount() {
        return childlist.size();
    }

    class ChildrenViewHolder extends RecyclerView.ViewHolder  {

        TextView  textViewGrade, textViewSchool,textViewFirstName,textViewPickupLocation,textViewDropoffLocation;
        //OnChildListener onChildListener;

        public ChildrenViewHolder(View itemView) {
            super(itemView);

            textViewGrade=itemView.findViewById(R.id.dspGrade);
            textViewSchool=itemView.findViewById(R.id.dspSchool);
            textViewFirstName = itemView.findViewById(R.id.dspName);
            textViewPickupLocation = itemView.findViewById(R.id.dspPickup);
            textViewDropoffLocation = itemView.findViewById(R.id.dspDropOff);

        }

    }
}
