package br.unipar.fgts;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Saque extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saque);

        setValores();
    }

    public void setValores(){


        Bundle b = getIntent().getExtras();
        String primeiroBeneficio = b.getString("dataPrimeiroPagamento");
        String segundoBeneficio = b.getString("dataSegundoPagamento");
        String terceiroBeneficio = b.getString("dataTerceiroPagamento");

        TextView textPrimeiroPagamento = (TextView) findViewById(R.id.primeiraparcela);
        textPrimeiroPagamento.setText("Primeira \n"+primeiroBeneficio);

        TextView textSegundoPagamento = (TextView) findViewById(R.id.segundaparcela);
        textSegundoPagamento.setText("Segunda \n"+segundoBeneficio);

        TextView textTerceiroPagamento= (TextView) findViewById(R.id.terceiraparcela);
        textTerceiroPagamento.setText("Terceira \n"+terceiroBeneficio);

    }
}
