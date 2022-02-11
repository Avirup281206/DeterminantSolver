package com.example.determinantsolver;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private EditText field_a1, field_a2, field_a3, field_b1, field_b2, field_b3, field_c1, field_c2, field_c3, field_answer;
    private TextView text_solution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration configuration = getResources().getConfiguration();
        configuration.fontScale=(float) 1; //0.85 small size, 1 normal size, 1,15 big etc

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        metrics.scaledDensity = configuration.fontScale * metrics.density;
        getBaseContext().getResources().updateConfiguration(configuration, metrics);
        setContentView(R.layout.activity_main);
        field_a1 = findViewById(R.id.text_a1);
        field_a2 = findViewById(R.id.text_a2);
        field_a3 = findViewById(R.id.text_a3);
        field_b1 = findViewById(R.id.text_b1);
        field_b2 = findViewById(R.id.text_b2);
        field_b3 = findViewById(R.id.text_b3);
        field_c1 = findViewById(R.id.text_c1);
        field_c2 = findViewById(R.id.text_c2);
        field_c3 = findViewById(R.id.text_c3);
        field_answer = findViewById(R.id.text_answer);
        text_solution = findViewById(R.id.text_solution);

        text_solution.setText("""
                Determinant Solver v0.1-beta
                Author: Avirup Banerjee
                Version: 0.1-beta
                
                This is a Determinant Solver made by me. Hope you like it""");
    }

    public void solve(View view){
        TextView label_numberFormatException = findViewById(R.id.text_numberFormatException);
        try {
            if (Objects.equals(field_a1.getText().toString(), "") || Objects.equals(field_a2.getText().toString(), "") || Objects.equals(field_a3.getText().toString(), "") || Objects.equals(field_b1.getText().toString(), "") || Objects.equals(field_b2.getText().toString(), "") || Objects.equals(field_b3.getText().toString(), "") || Objects.equals(field_c1.getText().toString(), "") || Objects.equals(field_c2.getText().toString(), "") || Objects.equals(field_c3.getText().toString(), ""))
                return;
            double field_a1_value = Double.parseDouble(field_a1.getText().toString()),
                    field_a2_value = Double.parseDouble(field_a2.getText().toString()),
                    field_a3_value = Double.parseDouble(field_a3.getText().toString()),
                    field_b1_value = Double.parseDouble(field_b1.getText().toString()),
                    field_b2_value = Double.parseDouble(field_b2.getText().toString()),
                    field_b3_value = Double.parseDouble(field_b3.getText().toString()),
                    field_c1_value = Double.parseDouble(field_c1.getText().toString()),
                    field_c2_value = Double.parseDouble(field_c2.getText().toString()),
                    field_c3_value = Double.parseDouble(field_c3.getText().toString());

            double result = field_a1_value * (field_b2_value * field_c3_value - field_b3_value * field_c2_value)
                    - field_a2_value * (field_b1_value * field_c3_value - field_b3_value * field_c1_value)
                    + field_a3_value * (field_b1_value * field_c2_value - field_b2_value * field_c1_value);
            result = Math.round(result*100)/100.0;

            String result_string = String.valueOf(Double.valueOf(result));

            field_answer.setText(result_string);

            String solution = """
                    $result = [($field_a1_value)×{($field_b2_value)×($field_c3_value) - ($field_b3_value)×($field_c2_value)}
                              - ($field_a2_value)×{($field_b1_value)×($field_c3_value) - ($field_b3_value)×($field_c1_value)}
                              + ($field_a3_value){($field_b1_value)×($field_c2_value) - ($field_b2_value)×($field_c1_value)}]"""
                    .replace("$result", result_string)
                    .replace("$field_a1_value", String.valueOf(field_a1_value))
                    .replace("$field_a2_value", String.valueOf(field_a2_value))
                    .replace("$field_a3_value", String.valueOf(field_a3_value))
                    .replace("$field_b1_value", String.valueOf(field_b1_value))
                    .replace("$field_b2_value", String.valueOf(field_b2_value))
                    .replace("$field_b3_value", String.valueOf(field_b3_value))
                    .replace("$field_c1_value", String.valueOf(field_c1_value))
                    .replace("$field_c2_value", String.valueOf(field_c2_value))
                    .replace("$field_c3_value", String.valueOf(field_c3_value));
            text_solution.setText(solution);
            label_numberFormatException.setVisibility(View.INVISIBLE);
        }catch(Exception e){
            text_solution.setText("");
            field_answer.setText("");
            label_numberFormatException.setVisibility(View.VISIBLE);
        }
    }

    public void reset(View view){
        field_a1.setText("");
        field_a2.setText("");
        field_a3.setText("");
        field_b1.setText("");
        field_b2.setText("");
        field_b3.setText("");
        field_c1.setText("");
        field_c2.setText("");
        field_c3.setText("");
        field_answer.setText("");
        text_solution.setText("");
        findViewById(R.id.text_numberFormatException).setVisibility(View.INVISIBLE);
    }
}