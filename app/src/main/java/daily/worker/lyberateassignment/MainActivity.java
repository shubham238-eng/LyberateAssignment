package daily.worker.lyberateassignment;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.List;

import daily.worker.lyberateassignment.BackGround.GroupingList;
import daily.worker.lyberateassignment.model.Result;
import daily.worker.lyberateassignment.model.SongsEntity;

public class MainActivity extends AppCompatActivity {

    final String BASE_URL="https://itunes.apple.com/search?term=";
    List<SongsEntity> result;
    RecyclerView recyclerView;
    EditText Searchtext;
    EditText Limit;
    TextView Error;
    Button Search;
    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();//initialization variables

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userInput=Searchtext.getText().toString().trim();
                String limit = Limit.getText().toString().trim();
                if(limit.length() == 0 ){
                    limit = "25";
                }

                if(userInput.equals(""))
                    Toast.makeText(MainActivity.this, "Enter Valid Text", Toast.LENGTH_SHORT).show();
                else
                {

                    Error.setVisibility(View.GONE);
                    Searchtext.setText("");
                    Limit.setText("");
                    showData(userInput,limit);

                }

            }
        });


    }

    void initialization()
    {
        Searchtext=findViewById(R.id.editSearch);
        Error=findViewById(R.id.textError);
        recyclerView=findViewById(R.id.rView);
        Search = findViewById(R.id.btnSearch);
        Limit = findViewById(R.id.limit);
        recyclerAdapter = RecyclerAdapter.getRecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }





    void showData(String userInput,String limit)
    {
        final Gson gson = new Gson();
        RequestQueue queue = Volley.newRequestQueue(this);

        char[] temp=userInput.toCharArray();

        for(int i=0;i<userInput.length();i++)
        {
            if(userInput.charAt(i)==' ')
                temp[i]='+';
        }

        String URL = BASE_URL + String.valueOf(temp) + "&entity=musicTrack&limit="+ limit;

        StringRequest request= new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Result model= gson.fromJson(response,Result.class);

                result= model.getResults();

                if(result.size()==0)
                {
                    Error.setVisibility(View.VISIBLE);
                }

                new GroupingList(recyclerAdapter).execute(result);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

        queue.add(request);

    }

}
