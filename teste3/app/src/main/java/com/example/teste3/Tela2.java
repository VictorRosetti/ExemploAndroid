package com.example.teste3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class Tela2 extends AppCompatActivity {
    TextView nome,cpf,endereco,dadoT1;
    RequestQueue filaRequest;

    String url="https://9nflln-3000.csb.app/tudo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);
        filaRequest = Volley.newRequestQueue(this);
        dadoT1 = findViewById(R.id.textView3);
        Intent intent = getIntent();
        String dadoTela1 = intent.getStringExtra("nome");
        dadoT1.setText(dadoTela1);
        Teobaldo baldo = (Teobaldo)intent.getSerializableExtra("obj");
        System.out.println("Aqui1: "+baldo.nomebaldo);
    }
    public void GetData(View view){
        System.out.println("Entrei Aqui!");
        nome = findViewById(R.id.nome);
        cpf = findViewById(R.id.cpf);

        JsonArrayRequest arrReq = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length() > 0) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject jsonObj = response.getJSONObject(i);
                                    String nomeJ = jsonObj.get("nome").toString();
                                    String cpfJ = jsonObj.get("cpf").toString();
                                    String enderecoJ = jsonObj.get("endereco").toString();
                                    //System.out.println("Nome: " +nomeJ +" CPF: "+cpfJ+" Endereco: "+enderecoJ);
                                    Log.d("Dados ","Nome: " +nomeJ +" CPF: "+cpfJ+" Endereco: "+enderecoJ);
                                    nome.setText(nomeJ);
                                    cpf.setText(cpfJ);
                                } catch (JSONException e) {
                                    Log.e("Volley", "Erro no JSON");
                                    //System.out.println("Erro no JSON");
                                }

                            }
                        } else {
                            Log.e("Data","Sem Dados");
                            //System.out.println("Sem Dados");
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", error.toString());
                        //System.out.println(error.toString());
                    }
                });
        filaRequest.add(arrReq);
    }
}