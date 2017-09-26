package pt.ipg.gcyclerclient2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button bu_adicionar_jogo;
    private Button bu_ver_jogos_a_venda;
    private Button bu_ver_colecao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bu_adicionar_jogo = (Button) findViewById(R.id.button_adicionar);
        bu_adicionar_jogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchGameActivity.class);
                startActivity(intent);
            }
        });

        bu_ver_jogos_a_venda = (Button) findViewById(R.id.button_ver_a_venda);
        bu_ver_jogos_a_venda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CopiesForSaleActivity.class);
                startActivity(intent);
            }
        });

        bu_ver_colecao = (Button) findViewById(R.id.button_ver_colecao);
        bu_ver_colecao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CopiesForSaleActivity.class);
                startActivity(intent);
            }
        });

        VarSessao.setId_utilizador(1);
    }
}