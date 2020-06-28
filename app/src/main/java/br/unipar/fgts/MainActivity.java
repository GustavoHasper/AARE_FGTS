package br.unipar.fgts;

import androidx.appcompat.app.AppCompatActivity;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.text.NumberFormat;

import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class MainActivity extends AppCompatActivity {

    private EditText tvCPF, tvDataNasc;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvCPF = findViewById(R.id.tvCPF);
        tvDataNasc = findViewById(R.id.tvDataNasc);


        tvCPF.addTextChangedListener(new MaskTextWatcher(tvCPF, new SimpleMaskFormatter("NNN.NNN.NNN-NN")));
        tvDataNasc.addTextChangedListener(new MaskTextWatcher(tvDataNasc, new SimpleMaskFormatter("NN/NN/NNNN")));

    }

    public  void validar(View view){

        if(tvCPF.getText().toString().equals("")){
            menssage("Campo do Cpf esta nulo!");
        }
        if(tvDataNasc.getText().toString().equals("")){
            menssage("Campo da data Nascimento esta nulo!");
        }
        else {

            Pessoa pessoa = new Pessoa();
            pessoa.setCpf(tvCPF.getText().toString());
            pessoa.setDataNascimento(pessoa.converterData(tvDataNasc.getText().toString()));

            if(pessoa.calculaIdade(pessoa.getDataNascimento()) < 18 ){
                menssage("FGTS Negado!");
            }else{
                segundaTela(view, pessoa);
            }
        }
    }

    public void segundaTela(View view, Pessoa pessoa){
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(4);

        Intent intent = new Intent(this,Saque.class);
        Bundle b = new Bundle();

        b.putString("dataPrimeiroPagamento", pessoa.calculaPagamento(pessoa.getDataNascimento(), 1));
        b.putString("dataSegundoPagamento", pessoa.calculaPagamento(pessoa.getDataNascimento(),2));
        b.putString("dataTerceiroPagamento", pessoa.calculaPagamento(pessoa.getDataNascimento(),3));

        intent.putExtras(b);
        startActivity(intent);

    }

    public void menssage(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


}