package com.example.teste3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Random;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {

    String nome="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void alterarTexto(View view)
    {
        TextView texto = findViewById(R.id.textView2);
        texto.setText("Teste!");
        novoNumero();
        PegarNome();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                mudarTela();
            }
        },2000);

    }
    public void PegarNome(){
        TextView campoNome = findViewById(R.id.login);
        TextView textoPrincipal = findViewById(R.id.textView);
        nome = campoNome.getText().toString();
        textoPrincipal.setText(nome);
    }


    public void novoNumero(){
        TextView numero = findViewById(R.id.numero);
        int x = new Random().nextInt(100);
        numero.setText(String.valueOf(x));
    }

    public void mudarTela()
    {
        Intent intent = new Intent(getApplicationContext(), Tela2.class);
        intent.putExtra("nome", nome);
        Teobaldo teo = new Teobaldo();
        intent.putExtra("obj",teo);
        startActivity(intent);
    }
}