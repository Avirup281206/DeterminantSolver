package com.example.determinantsolver;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private EditText field_a1, field_a2, field_a3, field_b1, field_b2, field_b3, field_c1, field_c2, field_c3, field_answer;
    private TextView text_solution, label_exception;
    private Button submit_button;
    private Button reset_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration configuration = getResources().getConfiguration();
        configuration.fontScale = (float) 1;

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        metrics.scaledDensity = configuration.fontScale * metrics.density;
        getBaseContext().getResources().updateConfiguration(configuration, metrics);
        setContentView(R.layout.activity_main);

        initializeVariable();

        submit_button.setOnClickListener(this::solve);
        reset_button.setOnClickListener(this::reset);
    }

    private void solve(@NotNull View view){
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
            label_exception.setVisibility(View.INVISIBLE);
        }catch(Exception e) {
            try {
                String toBeSolved = "[($field_a1_value)×{($field_b2_value)×($field_c3_value) - ($field_b3_value)×($field_c2_value)}-($field_a2_value)×{($field_b1_value)×($field_c3_value) - ($field_b3_value)×($field_c1_value)}+($field_a3_value){($field_b1_value)×($field_c2_value) - ($field_b2_value)×($field_c1_value)}]"
                        .replace("{", "(")
                        .replace("[", "(")
                        .replace("}", ")")
                        .replace("]", ")")
                        .replace("×", "*")
                        .replace("$field_a1_value", field_a1.getText().toString())
                        .replace("$field_a2_value", field_a2.getText().toString())
                        .replace("$field_a3_value", field_a3.getText().toString())
                        .replace("$field_b1_value", field_b1.getText().toString())
                        .replace("$field_b2_value", field_b2.getText().toString())
                        .replace("$field_b3_value", field_b3.getText().toString())
                        .replace("$field_c1_value", field_c1.getText().toString())
                        .replace("$field_c2_value", field_c2.getText().toString())
                        .replace("$field_c3_value", field_c3.getText().toString());
                Expression expression = new ExpressionBuilder(toBeSolved).build();
                double result = expression.evaluate();
                field_answer.setText(String.valueOf(result));

                String solution = """
                        $result = [($field_a1_value)×{($field_b2_value)×($field_c3_value) - ($field_b3_value)×($field_c2_value)}
                                  - ($field_a2_value)×{($field_b1_value)×($field_c3_value) - ($field_b3_value)×($field_c1_value)}
                                  + ($field_a3_value){($field_b1_value)×($field_c2_value) - ($field_b2_value)×($field_c1_value)}]"""
                        .replace("$result", String.valueOf(result))
                        .replace("$field_a1_value", field_a1.getText().toString())
                        .replace("$field_a2_value", field_a2.getText().toString())
                        .replace("$field_a3_value", field_a3.getText().toString())
                        .replace("$field_b1_value", field_b1.getText().toString())
                        .replace("$field_b2_value", field_b2.getText().toString())
                        .replace("$field_b3_value", field_b3.getText().toString())
                        .replace("$field_c1_value", field_c1.getText().toString())
                        .replace("$field_c2_value", field_c2.getText().toString())
                        .replace("$field_c3_value", field_c3.getText().toString());

                text_solution.setText(solution);
                label_exception.setVisibility(View.INVISIBLE);

            } catch (IllegalArgumentException illegalArgumentException) {
                illegalArgumentException.printStackTrace();
                text_solution.setText("");
                field_answer.setText("");
                label_exception.setText(R.string.invalid_expression_text);
                label_exception.setVisibility(View.VISIBLE);
            } catch (Exception exception) {
                exception.printStackTrace();
                text_solution.setText("");
                field_answer.setText("");
                label_exception.setText(R.string.invalid_input_text);
                label_exception.setVisibility(View.VISIBLE);
            }
        }
    }

    private void reset(@NotNull View view){
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
        label_exception.setVisibility(View.INVISIBLE);
    }

    private void initializeVariable(){
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
        submit_button = findViewById(R.id.submit_button);
        reset_button = findViewById(R.id.reset_button);
        label_exception = findViewById(R.id.text_exception);
    }
}