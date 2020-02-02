package com.msg91.sendotp.sample.ui.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.msg91.sendotp.sample.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class ToolsFragment extends Fragment {
    TextView dname,dph,did,dob,daddress,dgender,demail,dp;
    Button search;
    EditText abc;
    String a,b,c,d,e,f,g,h;
    private ToolsViewModel toolsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
        dname=root.findViewById(R.id.rdname);
        did=root.findViewById(R.id.docyou3);
        dph=root.findViewById(R.id.dw);
        search=root.findViewById(R.id.s);
        demail=root.findViewById(R.id.demail);
        abc=root.findViewById(R.id.trk);
        dob=root.findViewById(R.id.dob);
        daddress=root.findViewById(R.id.daddress);
        dgender=root.findViewById(R.id.dgender);
dp=root.findViewById(R.id.dp);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/Boutique_online_maneguments/track_oder.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
//If we are getting success from server
                       Toast.makeText(getActivity(),response,Toast.LENGTH_LONG).show();
                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject json_obj = jsonArray.getJSONObject(i);
//ba = json_obj.getString("balance");

                                                a=json_obj.getString("name");
                                                b=json_obj  .getString("phone");
                                                c=json_obj.getString("email");
                                                d=json_obj.getString("address");
                                                e=json_obj.getString("nos");
                                                f=json_obj.getString("price");
                                                g=json_obj.getString("product_name");
                                                h=json_obj.getString("product_details");
//                                e=json_obj.getString("address");
//                                f=json_obj.getString("gender");
//                                g=json_obj.getString("dob");

                                        did.setText(a);
                                        dph.setText(b);
                                        demail.setText(c);
                                        dob.setText(d);
                                        daddress.setText(e);
                                        dgender.setText(f);
                                        dname.setText(g);
                                        dp.setText(h);
                                    }
//Toast.makeText(Recharge.this, "your new balnce is "+ba, Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                //   Toast.makeText(Signin.this, "success", Toast.LENGTH_LONG).show();

                            }



                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
//You can handle error here if you want
                            }

                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
//Adding parameters to request
                        params.put("a",abc.getText().toString());

//

//returning parameter
                        return params;
                    }

                };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(stringRequest);



            }
        });
        return root;
    }
}