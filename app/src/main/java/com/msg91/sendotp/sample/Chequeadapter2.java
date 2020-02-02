package com.msg91.sendotp.sample;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class Chequeadapter2 extends RecyclerView.Adapter<Chequeadapter2.ProductViewHolder> {
    Intent i;


    private Context mCtx;
    private List<Cheque2> productList;

    public Chequeadapter2(Context mCtx, List<Cheque2> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recycler_c2, null);
        return new ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final   Cheque2 cheque;   cheque = productList.get(position);

        Picasso.get().load(cheque.getImage()).into(holder.image);
        holder.name.setText(cheque.getUser());
        holder.phone.setText(cheque.getStatus());
        holder.email.setText(cheque.getPrize1());
        holder.address.setText(cheque.getPrize2());
        holder.nos.setText(cheque.getPrize3());
        holder.price.setText(cheque.getPrize4());
        holder.productname.setText(cheque.getPrize5());
        holder.productdetails.setText(cheque.getPrize());
        holder.track.setText(cheque.getPrize6());
        sh= mCtx.getSharedPreferences("data",MODE_PRIVATE);

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Uri uri = Uri.parse("smsto:" + cheque.getUser());
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                i.putExtra("sms_body","hello your item shiped  Track id\t"+cheque.getPrize6()+"Address\t"+cheque.getPrize1()+"product\t"+cheque.getPrize4()+"product details\t\t\t\t\t"+cheque.getPrize5()+" More Enquiry\t\t\t\t\t"+"\t\t\t\t Contact Us\t\t\t\t"+sh.getString("phone",null));
                i.getPackage();
                mCtx.startActivity(i);


            }
        });
//
        holder.sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+cheque.getUser()));
                mCtx.startActivity(intent);





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
    SharedPreferences sh;
    class ProductViewHolder extends RecyclerView.ViewHolder {



        TextView name,phone,email,address,nos,price,productname,productdetails,track;
        ImageView image;
        Button sms,call;


        public ProductViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.simage);
            name = itemView.findViewById(R.id.sname);
            phone= itemView.findViewById(R.id.sphone);
            email= itemView.findViewById(R.id.semail);
            address = itemView.findViewById(R.id.saddress);
            nos = itemView.findViewById(R.id.snos);
            price = itemView.findViewById(R.id.sprice);
            productname= itemView.findViewById(R.id.sprodactname);
            productdetails= itemView.findViewById(R.id.sproductdetails);
            sms= itemView.findViewById(R.id.msgbt);
            call= itemView.findViewById(R.id.msgbt2);
            track= itemView.findViewById(R.id.strack);


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

}