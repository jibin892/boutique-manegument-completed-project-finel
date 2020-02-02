package com.msg91.sendotp.sample;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatusFragment extends Fragment {
    //Button b;
//TextView it
// ;
    SharedPreferences shq, shh,logout;
    Button pbtn,im;
    private Bitmap bitmap;
    private Uri filePath;
    EditText pname,pprice,pdetails;
    final int RequestPermissionCode = 1;
ImageView  imgview;
    List<String> imagesEncodedList;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View root = inflater.inflate(R.layout.fragment_status, container, false);
        shh= Objects.requireNonNull(getActivity()).getSharedPreferences("data",MODE_PRIVATE);
//Toast.makeText(getContext(),shh.getString("RID",null),Toast.LENGTH_LONG).show();
        pname = root.findViewById(R.id.pname1);
        pprice = root.findViewById(R.id.price1);
        pdetails = root.findViewById(R.id.pdetails1);
        pbtn = root.findViewById(R.id.pbtn1);
        pbtn = root.findViewById(R.id.pbtn1);

        imgview = root.findViewById(R.id.imgg);
        im = root.findViewById(R.id.im);



        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), 1);

//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

            }


//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(intent,IMAGE_PICK_CODE);

        });


        pbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        if (pname.getText().toString().isEmpty()){
            pname.setError("null");

        }
        else if (pprice.getText().toString().isEmpty()){
            pprice.setError("null");

        }
        else if (pdetails.getText().toString().isEmpty()){
            pdetails.setError("null");

        }

else{

            class UploadImage extends AsyncTask<Bitmap, Void, String> {

                ProgressDialog loading;
                RequestHandler rh = new RequestHandler();

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(getContext(), "Uploading...", null, true, false);
                }


                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    loading.dismiss();
                    Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();

                    if(s.equals("success"))
                    {

                        new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Registration Success")
                                .setContentText("Back To Login!")
                                .setConfirmText("Yes,Login")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog
                                                .setTitleText("Logining...!")

                                                .setConfirmText("OK")

                                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                        Intent in=new Intent(getContext(),Signinnurse.class);
                                                        startActivity(in);
                                                    }
                                                })
                                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                    }
                                })
                                .show();




//
                    }









                }

                @SuppressLint("WrongThread")
                @Override
                protected String doInBackground(Bitmap... params) {
                    bitmap = params[0];
                    String uploadImage = getStringImage(bitmap);

                    HashMap<String, String> data = new HashMap<>();

                    data.put("a", pname.getText().toString());
                    data.put("b", pprice.getText().toString());
                    data.put("c", pdetails.getText().toString());
                    data.put("img",uploadImage);
                    String result = rh.sendPostRequest("https://androidprojectstechsays.000webhostapp.com/Boutique_online_maneguments/product_upload.php", data);

                    return result;
                }
            }
            UploadImage ui = new UploadImage();
            ui.execute(bitmap);




        }


    }



});



        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                imgview.setImageBitmap(bitmap);
                getStringImage(bitmap);
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }




//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//
//
//        inflater.inflate(R.menu.menu_main4,menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id=item.getItemId();
//
//
//
//        if (id==R.id.logout100){
//            shq= Objects.requireNonNull(getActivity()).getSharedPreferences("Official",MODE_PRIVATE);
//            SharedPreferences.Editor e=shq.edit();
//            e.clear();
//            e.apply();
//            startActivity(new Intent(getContext(),Signinnurse.class).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
//
////            Intent i=new Intent(getActivity(),Therapy.class);
////            startActivity(i);
//////            Toast.makeText(getActivity(),"Techers",Toast.LENGTH_LONG).show();
//
//
//        }
//
//
//
//
//
//        return super.onOptionsItemSelected(item);
//    }

}