package com.example.quiz1_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.quiz1_test.Model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UserListViewActivity extends AppCompatActivity {

    ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_view);

        users = new ArrayList<User>();

        String url = "https://reqres.in/api/users";
        Log.d("URL", url);

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(getStringRequest(url));


    }

    private StringRequest getStringRequest(String url) {
        return new StringRequest(Request.Method.GET, url, (response) -> {
            try{
                JSONObject object = new JSONObject(response);
                JSONArray array = object.getJSONArray("data");

                for(int i=0; i<array.length(); i++){
                    users.add(new User(
                            array.getJSONObject(i).getInt("id"),
                            array.getJSONObject(i).getString("email"),
                            array.getJSONObject(i).getString("first_name"),
                            array.getJSONObject(i).getString("last_name"),
                            array.getJSONObject(i).getString("avatar")));
                }
                prepareListView();
                Log.d("CREATION", users.toString());
            } catch (JSONException e){
                e.printStackTrace();
            }
        }, (error) -> {

        });
    }

    private void prepareListView() {
        RecyclerView rv =findViewById(R.id.list_reycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        UserModelAdapter userModelAdapter = new UserModelAdapter(users);

        rv.setLayoutManager(layoutManager);
        rv.setAdapter(userModelAdapter);
    }


}

class UserModelAdapter extends RecyclerView.Adapter<UserModelViewHolder> {

    ArrayList<User> users;

    public UserModelAdapter(ArrayList<User> users) {
        this.users = users;
    }


    @NonNull
    @Override
    public UserModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);

        return new UserModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserModelViewHolder holder, int position) {
        User user = users.get(position);

        holder.id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UserDetailActivity.class);
                intent.putExtra("userDetail", user);
                view.getContext().startActivity(intent);
            }
        });

        Glide.with(holder.parent.getContext())
                        .load(user.getAvatar())
                                .into(holder.avatar);
        holder.fname.setText(user.getFirstname());
        holder.lname.setText(user.getLastname());
        holder.email.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}

class UserModelViewHolder extends RecyclerView.ViewHolder {
    View parent;
    ImageView avatar;
    LinearLayout id;
    TextView fname;
    TextView lname;
    TextView email;

    public UserModelViewHolder(@NonNull View itemView){
        super(itemView);
        parent = itemView;
        avatar = itemView.findViewById(R.id.profilePic);
        id = itemView.findViewById(R.id.list_item_card_view);
        fname = itemView.findViewById(R.id.fname_text);
        lname = itemView.findViewById(R.id.lname_text);
        email = itemView.findViewById(R.id.email_text);

    }
}