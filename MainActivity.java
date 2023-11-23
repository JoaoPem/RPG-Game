package com.example.a20trabalhorpg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button botaoSorteio;
    TextView resultadoForca, resultadoDestreza, resultadoConstituicao, resultadoInteligencia, resultadoSabedoria, resultadoCarisma, resultadoVida;
    private Spinner classSpinner;
    private ArrayAdapter<String> spinnerAdapter;
    private HashMap<String, Class<?>> classMap = new HashMap<>();
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SORTEAR
        botaoSorteio = findViewById(R.id.btnSortear);
        resultadoForca = findViewById(R.id.txtForca);
        resultadoDestreza = findViewById(R.id.txtDestreza);
        resultadoConstituicao = findViewById(R.id.txtConstituicao);
        resultadoInteligencia = findViewById(R.id.txtInteligencia);
        resultadoSabedoria = findViewById(R.id.txtSabedoria);
        resultadoCarisma = findViewById(R.id.txtCarisma);
        resultadoVida = findViewById(R.id.txtVida);
        imageView = findViewById(R.id.imageView);


        classSpinner = findViewById(R.id.spinner);
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        addClassToMap("Humano", Humano.class);
        addClassToMap("Anão", Anao.class);
        addClassToMap("Elfo", Elfo.class);
        addClassToMap("Meio-Gigante", MeioGiga.class);

        classSpinner.setAdapter(spinnerAdapter);

        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedClassName = (String) classSpinner.getSelectedItem();
                Class<?> selectedClass = classMap.get(selectedClassName);
                if ("Humano".equals(selectedClassName)){
                    imageView.setImageResource(R.drawable.humano_image);
                }else if ("Anão".equals(selectedClassName)) {
                    imageView.setImageResource(R.drawable.anao_image);
                } else if ("Elfo".equals(selectedClassName)) {
                    imageView.setImageResource(R.drawable.elfo_image);
                } else if ("Meio-Gigante".equals(selectedClassName)) {
                    imageView.setImageResource(R.drawable.meiogigante_image);
                }

                if (selectedClass != null) {
                    try {
                        Object instance = selectedClass.newInstance();
                        selectedClass.getMethod("execute").invoke(instance);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        botaoSorteio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dado dado1 = new Dado();
                int numeroAleatorioF = dado1.sortear();
                Dado dado2 = new Dado();
                int numeroAleatorioDes = dado2.sortear();
                Dado dado3 = new Dado();
                int numeroAleatorioCon = dado3.sortear();
                Dado dado4 = new Dado();
                int numeroAleatorioInt = dado4.sortear();
                Dado dado5 = new Dado();
                int numeroAleatorioSab = dado5.sortear();
                Dado dado6 = new Dado();
                int numeroAleatorioCar = dado6.sortear();

                String selectedClassName = (String) classSpinner.getSelectedItem();
                Class<?> selectedClass = classMap.get(selectedClassName);
                if (selectedClass != null) {
                    try {
                        Object instance = selectedClass.newInstance();
                        int resultForca = (int) selectedClass.getMethod("setForca", int.class).invoke(instance, numeroAleatorioF);
                        resultadoForca.setText("Força: " + resultForca);
                        int resultDestreza = (int) selectedClass.getMethod("setDestreza", int.class).invoke(instance, numeroAleatorioDes);
                        resultadoDestreza.setText("Destreza: " + resultDestreza);
                        int resultConst = (int) selectedClass.getMethod("setConstituicao", int.class).invoke(instance, numeroAleatorioCon);
                        resultadoConstituicao.setText("Contituição: " + resultConst);
                        int resultIntel = (int) selectedClass.getMethod("setInteligencia", int.class).invoke(instance,numeroAleatorioInt);
                        resultadoInteligencia.setText("Inteligência: " + resultIntel);
                        int resultSab = (int) selectedClass.getMethod("setSabedoria", int.class).invoke(instance, numeroAleatorioSab);
                        resultadoSabedoria.setText("Sabedoria " + resultSab);
                        int resultCar = (int) selectedClass.getMethod("setCarisma", int.class).invoke(instance, numeroAleatorioCar);
                        resultadoCarisma.setText("Carisma: " + resultCar);
                        int resultVi = (int) selectedClass.getMethod("setVida", int.class).invoke(instance, numeroAleatorioCon);
                        resultadoVida.setText("Vida " + Math.ceil(resultConst*3.5));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void addClassToMap(String classeName, Class<?> classeType) {
        spinnerAdapter.add(classeName);
        classMap.put(classeName, classeType);
    }
}


