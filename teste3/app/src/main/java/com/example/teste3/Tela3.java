package com.example.teste3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class Tela3 extends AppCompatActivity {
    TextView nome,cpf;
    RequestQueue filaRequest;
    String url="https://9nflln-3000.csb.app/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela3);
        filaRequest = Volley.newRequestQueue(this);
    }
    public void GetData(View view){
        nome = findViewById(R.id.nome);
        cpf = findViewById(R.id.cpf2);

        JsonArrayRequest requisicao = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length()>0){
                            for(int i =0; i< response.length(); i++){
                                try{
                                    JSONObject jsonObj = response.getJSONObject(i);
                                    String nomeJ = jsonObj.get("nome").toString();
                                    String cpfJ = jsonObj.get("cpf").toString();
                                    Log.d("Dados", "Nome: "+nomeJ+" CPF: "+cpfJ);
                                    nome.setText(nomeJ);
                                    cpf.setText(cpfJ);
                                }catch (JSONException e){
                                    Log.e("Volley", "Erro no JSON");
                                }

                            }
                        }else {
                            Log.e("Data", "Sem Dados!");
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.e("Volley", error.toString());
                    }
                });
        filaRequest.add(requisicao);
    }














}