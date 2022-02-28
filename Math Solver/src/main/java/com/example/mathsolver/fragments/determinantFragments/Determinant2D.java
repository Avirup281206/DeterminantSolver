package com.example.mathsolver.fragments.determinantFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mathsolver.R;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Determinant2D extends Fragment {

    private EditText field_a1, field_a2, field_b1, field_b2, field_answer;
    private TextView text_solution, label_exception;
    private Button submit_button;
    private Button reset_button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_determinant_2d, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeVariable(view);

        submit_button.setOnClickListener(this::solve);
        reset_button.setOnClickListener(this::reset);
    }

    private void solve(@NotNull View view) {
        try {
            solve_simple();
        } catch (Exception e) {
            try {
                solve_expression();
            } catch (IllegalArgumentException illegalArgumentException) {
                text_solution.setText("");
                field_answer.setText("");
                label_exception.setText(R.string.invalid_expression_text);
                label_exception.setVisibility(View.VISIBLE);
            } catch (Exception exception) {
                text_solution.setText("");
                field_answer.setText("");
                label_exception.setText(R.string.invalid_input_text);
                label_exception.setVisibility(View.VISIBLE);
            }
        }
    }

    private void solve_simple() throws NumberFormatException {

        if (Objects.equals(field_a1.getText().toString(), "") || Objects.equals(field_a2.getText().toString(), "") || Objects.equals(field_b1.getText().toString(), "") || Objects.equals(field_b2.getText().toString(), ""))
            return;
        double field_a1_value = Double.parseDouble(field_a1.getText().toString()),
                field_a2_value = Double.parseDouble(field_a2.getText().toString()),
                field_b1_value = Double.parseDouble(field_b1.getText().toString()),
                field_b2_value = Double.parseDouble(field_b2.getText().toString());

        double result = field_a1_value * field_b2_value - field_a2_value * field_b1_value;
        result = Math.round(result * 100) / 100.0;

        String result_string = String.valueOf(Double.valueOf(result));

        String field_a1_b2_value = String.valueOf(Double.parseDouble(String.valueOf(field_a1_value)) * Double.parseDouble(String.valueOf(field_b2_value))),
                field_a2_b1_value = String.valueOf(Double.parseDouble(String.valueOf(field_a2_value)) * Double.parseDouble(String.valueOf(field_b1_value)));

        String solution = """
                ($field_a1_value×$field_b2_value) - ($field_a2_value×$field_b1_value)
                                
                = $field_a1*b2_value - $field_a2*b1_value
                                
                = $result"""

                .replace("$field_a1_value", String.valueOf(field_a1_value))
                .replace("$field_a2_value", String.valueOf(field_a2_value))
                .replace("$field_b1_value", String.valueOf(field_b1_value))
                .replace("$field_b2_value", String.valueOf(field_b2_value))

                .replace("$field_a1*b2_value", field_a1_b2_value)
                .replace("$field_a2*b1_value", field_a2_b1_value)

                .replace("$result", result_string);

        field_answer.setText(result_string);
        text_solution.setText(solution);
        label_exception.setVisibility(View.INVISIBLE);

    }

    private void initializeVariable(@NotNull View view) {

        field_a1 = view.findViewById(R.id.text_a1_2D);
        field_a2 = view.findViewById(R.id.text_a2_2D);
        field_b1 = view.findViewById(R.id.text_b1_2D);
        field_b2 = view.findViewById(R.id.text_b2_2D);
        field_answer = view.findViewById(R.id.text_answer_2D);
        text_solution = view.findViewById(R.id.text_solution_2D);
        submit_button = view.findViewById(R.id.submit_button_2D);
        reset_button = view.findViewById(R.id.reset_button_2D);
        label_exception = view.findViewById(R.id.text_exception_2D);

    }

    private void reset(View view) {
        field_a1.setText("");
        field_a2.setText("");
        field_b1.setText("");
        field_b2.setText("");
        field_answer.setText("");
        text_solution.setText("");
        label_exception.setVisibility(View.INVISIBLE);
    }

    private void solve_expression() {

        String toBeSolved = "($field_a1_value×$field_b2_value) - ($field_a2_value×$field_b1_value)"
                .replace("×", "*")

                .replace("$field_a1_value", field_a1.getText().toString())
                .replace("$field_a2_value", field_a2.getText().toString())
                .replace("$field_b1_value", field_b1.getText().toString())
                .replace("$field_b2_value", field_b2.getText().toString());

        Expression expression = new ExpressionBuilder(toBeSolved).build();
        double result = expression.evaluate();
        field_answer.setText(String.valueOf(result));

        String field_a1_abs_value = String.valueOf(new ExpressionBuilder(field_a1.getText().toString()).build().evaluate()),
                field_a2_abs_value = String.valueOf(new ExpressionBuilder(field_a2.getText().toString()).build().evaluate()),
                field_b1_abs_value = String.valueOf(new ExpressionBuilder(field_b1.getText().toString()).build().evaluate()),
                field_b2_abs_value = String.valueOf(new ExpressionBuilder(field_b2.getText().toString()).build().evaluate());

        String field_a1_b2_value = String.valueOf(Double.parseDouble(field_a1_abs_value) * Double.parseDouble(field_b2_abs_value)),
                field_a2_b1_value = String.valueOf(Double.parseDouble(field_a2_abs_value) * Double.parseDouble(field_b1_abs_value));


        String solution = """
                {($field_a1_value)×($field_b2_value)} - {($field_a2_value)×($field_b1_value)}
                                
                ($field_a1_abs_value×$field_b2_abs_value) - ($field_a2_abs_value×$field_b1_abs_value)
                                
                = $field_a1*b2_value - $field_a2*b1_value
                                
                = $result"""

                .replace("$field_a1_value", field_a1.getText().toString())
                .replace("$field_a2_value", field_a2.getText().toString())
                .replace("$field_b1_value", field_b1.getText().toString())
                .replace("$field_b2_value", field_b2.getText().toString())

                .replace("$field_a1_abs_value", field_a1_abs_value)
                .replace("$field_a2_abs_value", field_a2_abs_value)
                .replace("$field_b1_abs_value", field_b1_abs_value)
                .replace("$field_b2_abs_value", field_b2_abs_value)

                .replace("$field_a1*b2_value", field_a1_b2_value)
                .replace("$field_a2*b1_value", field_a2_b1_value)

                .replace("$result", String.valueOf(result));

        text_solution.setText(solution);
        label_exception.setVisibility(View.INVISIBLE);

    }
}
