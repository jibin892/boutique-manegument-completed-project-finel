package com.msg91.sendotp.sample;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.content.Context.MODE_PRIVATE;

public class Chequeadapter extends RecyclerView.Adapter<Chequeadapter.ProductViewHolder> {
    Intent i;
SharedPreferences sh;

    private Context mCtx;
    private List<Cheque> productList;

    public Chequeadapter(Context mCtx, List<Cheque> productList) {
        sh = Objects.requireNonNull(mCtx).getSharedPreferences("data", MODE_PRIVATE);
        this.mCtx = mCtx;
        this.productList = productList;
       // sh=mCtx.getSharedPreferences("Official1",MODE_PRIVATE);
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recycler_c, null);
        return new ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final Cheque cheque = productList.get(position);

        holder.name.setText(cheque.getImage());
        holder.detalis.setText(cheque.getStatus());

        Picasso.get().load(cheque.getUser1()).into(holder.image);
        holder.ph.setText(cheque.getUser2());
        sh = Objects.requireNonNull(mCtx).getSharedPreferences("data", MODE_PRIVATE);

        holder.cart.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

         // Toast.makeText(mCtx,sh.getString("phone",null) , Toast.LENGTH_LONG).show();

          StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Boutique_online_maneguments/cart_item_check.php",
                  new Response.Listener<String>() {
                      @Override
                      public void onResponse(String response) {
                       //   Toast.makeText(mCtx, response, Toast.LENGTH_LONG).show();
                          // Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                          if(response.contains("ok"))
                          {

                              new SweetAlertDialog(mCtx, SweetAlertDialog.WARNING_TYPE)
                                      .setTitleText(" Item Already Added")
                                      .setContentText("Back to Home!")
                                      .setConfirmText("Yes")
                                      .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                          @Override
                                          public void onClick(SweetAlertDialog sDialog) {
                                              sDialog
                                                      .setTitleText("Logining...!")

                                                      .setConfirmText("OK")

                                                      .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                          @Override
                                                          public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                              Intent in=new Intent(mCtx,MainActivity2.class);
                                                              mCtx.startActivity(in);
                                                          }
                                                      })
                                                      .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                          }
                                      })
                                      .show();




//
                          }
                          else{



                              StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Boutique_online_maneguments/cart.php",
                                      new Response.Listener<String>() {
                                          @Override
                                          public void onResponse(String response) {

                                              // Toast.makeText(mCtx, response, Toast.LENGTH_LONG).show();
                                              if(response.equals("Successful"))
                                              {

                                                  new SweetAlertDialog(mCtx, SweetAlertDialog.WARNING_TYPE)
                                                          .setTitleText(" Item Adiing Success")
                                                          .setContentText("Back to Home!")
                                                          .setConfirmText("Yes")
                                                          .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                              @Override
                                                              public void onClick(SweetAlertDialog sDialog) {
                                                                  sDialog
                                                                          .setTitleText("Logining...!")

                                                                          .setConfirmText("OK")

                                                                          .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                                              @Override
                                                                              public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                                                  Intent in=new Intent(mCtx,MainActivity2.class);
                                                                                  mCtx.startActivity(in);
                                                                              }
                                                                          })
                                                                          .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                                              }
                                                          })
                                                          .show();




//
                                              }

                                          }





                                      },
                                      new Response.ErrorListener() {
                                          @Override
                                          public void onErrorResponse(VolleyError error) {

                                          }


                                      }) {
                                  @Override
                                  protected Map<String, String> getParams() throws AuthFailureError {
                                      Map<String, String> params = new HashMap<>();
                                      //Adding parameters to request

                                      params.put("b",cheque.getImage());
                                      params.put("c",cheque.getStatus());
                                      params.put("a", cheque.getUser2());
                                      params.put("d", sh.getString("phone",null));
                                      params.put("img",cheque.getUser1());
                                      //returning parameter
                                      return params;
                                  }

                              };


                              //Adding the string request to the queue
                              RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
                              requestQueue.add(stringRequest);


                          }

                      }





                  },
                  new Response.ErrorListener() {
                      @Override
                      public void onErrorResponse(VolleyError error) {

                      }


                  }) {
              @Override
              protected Map<String, String> getParams() throws AuthFailureError {
                  Map<String, String> params = new HashMap<>();

                  params.put("a", cheque.getImage());
                  params.put("c", cheque.getStatus());
                  params.put("b", sh.getString("phone",null));

                  return params;
              }

          };


          //Adding the string request to the queue
          RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
          requestQueue.add(stringRequest);




      }
  });


        holder.app.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {


                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareBody = cheque.getUser1();
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    mCtx.startActivity(sharingIntent);

                }

        });

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(mCtx, Request.class);
                i.putExtra("image", cheque.getUser1());
                i.putExtra("imag", cheque.getUser2());
                i.putExtra("name1", cheque.getImage());
                i.putExtra("name2", cheque.getStatus());

                mCtx.startActivity(i);


            }
        });
        //  SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Creating editor to store values to shared preferences

        // mCtx.startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }



    class ProductViewHolder extends RecyclerView.ViewHolder {


        TextView name, price, detalis, a, ph,app,cart;
        ImageView image;
        Button buy, del, map;


        public ProductViewHolder(View itemView) {
            super(itemView);

            sh = Objects.requireNonNull(mCtx).getSharedPreferences("data", MODE_PRIVATE);
            name = itemView.findViewById(R.id.cname);
            ph = itemView.findViewById(R.id.vph11);
            detalis = itemView.findViewById(R.id.cdetails);
            image = itemView.findViewById(R.id.cimage);

            app = itemView.findViewById(R.id.app);
            cart = itemView.findViewById(R.id.cart);
            del = itemView.findViewById(R.id.del);



        }

//    @Override
//    public int getItemCount() {
//        return productList.size();
//    }
//
//    class ProductViewHolder extends RecyclerView.ViewHolder {
//
//

//        public ProductViewHolder(View itemView) {
//            super(itemView);
//
//

////            review=itemView.findViewById(R.id.re);
////            viewreview=itemView.findViewById(R.id.ve);
////          //  pid=itemView.findViewById(R.id.productidd);
//
//        }
//
//    }

    }

    public void filterList(ArrayList<Cheque> filteredList) {
        productList = filteredList;
        notifyDataSetChanged();
    }

}
