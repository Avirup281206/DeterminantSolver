package com.example.mathsolver.fragments.determinantFragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mathsolver.R;

import net.objecthunter.exp4j.ExpressionBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Determinant4D extends Fragment {

    private EditText field_a1, field_a2, field_a3, field_a4, field_b1, field_b2, field_b3, field_b4, field_c1, field_c2, field_c3, field_c4, field_d1, field_d2, field_d3, field_d4, field_answer;
    private Button submit_button;
    private Button reset_button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_determinant_4d, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeVariable(view);

        submit_button.setOnClickListener(this::solve);
        reset_button.setOnClickListener(this::reset);
    }

    private void solve(@NotNull View view) {
        ((InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        try {
            solve_simple();
        } catch (Exception e) {
            try {
                solve_expression();
            } catch (IllegalArgumentException illegalArgumentException) {
                field_answer.setText("");
                Toast.makeText(requireActivity().getApplicationContext(), R.string.invalid_expression_text, Toast.LENGTH_SHORT).show();
            } catch (Exception exception) {
                field_answer.setText("");
                Toast.makeText(requireActivity().getApplicationContext(), R.string.invalid_input_text, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void reset(@NotNull View view) {

        field_a1.setText("");
        field_a2.setText("");
        field_a3.setText("");
        field_a4.setText("");
        field_b1.setText("");
        field_b2.setText("");
        field_b3.setText("");
        field_b4.setText("");
        field_c1.setText("");
        field_c2.setText("");
        field_c3.setText("");
        field_c4.setText("");
        field_d1.setText("");
        field_d2.setText("");
        field_d3.setText("");
        field_d4.setText("");
        field_answer.setText("");
    }

    private void initializeVariable(View view) {

        field_a1 = view.findViewById(R.id.text_a1_4d);
        field_a2 = view.findViewById(R.id.text_a2_4d);
        field_a3 = view.findViewById(R.id.text_a3_4d);
        field_a4 = view.findViewById(R.id.text_a4_4d);
        field_b1 = view.findViewById(R.id.text_b1_4d);
        field_b2 = view.findViewById(R.id.text_b2_4d);
        field_b3 = view.findViewById(R.id.text_b3_4d);
        field_b4 = view.findViewById(R.id.text_b4_4d);
        field_c1 = view.findViewById(R.id.text_c1_4d);
        field_c2 = view.findViewById(R.id.text_c2_4d);
        field_c3 = view.findViewById(R.id.text_c3_4d);
        field_c4 = view.findViewById(R.id.text_c4_4d);
        field_d1 = view.findViewById(R.id.text_d1_4d);
        field_d2 = view.findViewById(R.id.text_d2_4d);
        field_d3 = view.findViewById(R.id.text_d3_4d);
        field_d4 = view.findViewById(R.id.text_d4_4d);
        field_answer = view.findViewById(R.id.text_answer_4D);
        submit_button = view.findViewById(R.id.submit_button_4D);
        reset_button = view.findViewById(R.id.reset_button_4D);
    }

    private void solve_simple() {

        if (Objects.equals(field_a1.getText().toString(), "") ||
                Objects.equals(field_a2.getText().toString(), "") ||
                Objects.equals(field_a3.getText().toString(), "") ||
                Objects.equals(field_a4.getText().toString(), "") ||
                Objects.equals(field_b1.getText().toString(), "") ||
                Objects.equals(field_b2.getText().toString(), "") ||
                Objects.equals(field_b3.getText().toString(), "") ||
                Objects.equals(field_b4.getText().toString(), "") ||
                Objects.equals(field_c1.getText().toString(), "") ||
                Objects.equals(field_c2.getText().toString(), "") ||
                Objects.equals(field_c3.getText().toString(), "") ||
                Objects.equals(field_c4.getText().toString(), "") ||
                Objects.equals(field_d1.getText().toString(), "") ||
                Objects.equals(field_d2.getText().toString(), "") ||
                Objects.equals(field_d3.getText().toString(), "") ||
                Objects.equals(field_d4.getText().toString(), ""))
            return;

        double field_a1_value = Double.parseDouble(field_a1.getText().toString()),
                field_a2_value = Double.parseDouble(field_a2.getText().toString()),
                field_a3_value = Double.parseDouble(field_a3.getText().toString()),
                field_a4_value = Double.parseDouble(field_a4.getText().toString()),
                field_b1_value = Double.parseDouble(field_b1.getText().toString()),
                field_b2_value = Double.parseDouble(field_b2.getText().toString()),
                field_b3_value = Double.parseDouble(field_b3.getText().toString()),
                field_b4_value = Double.parseDouble(field_b4.getText().toString()),
                field_c1_value = Double.parseDouble(field_c1.getText().toString()),
                field_c2_value = Double.parseDouble(field_c2.getText().toString()),
                field_c3_value = Double.parseDouble(field_c3.getText().toString()),
                field_c4_value = Double.parseDouble(field_c4.getText().toString()),
                field_d1_value = Double.parseDouble(field_d1.getText().toString()),
                field_d2_value = Double.parseDouble(field_d2.getText().toString()),
                field_d3_value = Double.parseDouble(field_d3.getText().toString()),
                field_d4_value = Double.parseDouble(field_d4.getText().toString());

        double result = field_a1_value * solve_3d_determinant(field_b2_value, field_b3_value, field_b4_value, field_c2_value, field_c3_value, field_c4_value, field_d2_value, field_d3_value, field_d4_value) -
                field_a2_value * solve_3d_determinant(field_b1_value, field_b3_value, field_b4_value, field_c1_value, field_c3_value, field_c4_value, field_d1_value, field_d3_value, field_d4_value) +
                field_a3_value * solve_3d_determinant(field_b1_value, field_b2_value, field_b4_value, field_c1_value, field_c2_value, field_c4_value, field_d1_value, field_d2_value, field_d4_value) -
                field_a4_value * solve_3d_determinant(field_b1_value, field_b2_value, field_b3_value, field_c1_value, field_c2_value, field_c3_value, field_d1_value, field_d2_value, field_d3_value);

        field_answer.setText(String.valueOf(result));
    }

    private double solve_3d_determinant(double a1, double a2, double a3, double b1, double b2, double b3, double c1, double c2, double c3) {

        double result = a1 * (b2 * c3 - b3 * c2)
                - a2 * (b1 * c3 - b3 * c1)
                + a3 * (b1 * c2 - b2 * c1);
        return Math.round(result * 100) / 100.0;

    }

    private void solve_expression() {
        double field_a1_value = new ExpressionBuilder(field_a1.getText().toString()).build().evaluate(),
                field_a2_value = new ExpressionBuilder(field_a2.getText().toString()).build().evaluate(),
                field_a3_value = new ExpressionBuilder(field_a3.getText().toString()).build().evaluate(),
                field_a4_value = new ExpressionBuilder(field_a4.getText().toString()).build().evaluate(),
                field_b1_value = new ExpressionBuilder(field_b1.getText().toString()).build().evaluate(),
                field_b2_value = new ExpressionBuilder(field_b2.getText().toString()).build().evaluate(),
                field_b3_value = new ExpressionBuilder(field_b3.getText().toString()).build().evaluate(),
                field_b4_value = new ExpressionBuilder(field_b4.getText().toString()).build().evaluate(),
                field_c1_value = new ExpressionBuilder(field_c1.getText().toString()).build().evaluate(),
                field_c2_value = new ExpressionBuilder(field_c2.getText().toString()).build().evaluate(),
                field_c3_value = new ExpressionBuilder(field_c3.getText().toString()).build().evaluate(),
                field_c4_value = new ExpressionBuilder(field_c4.getText().toString()).build().evaluate(),
                field_d1_value = new ExpressionBuilder(field_d1.getText().toString()).build().evaluate(),
                field_d2_value = new ExpressionBuilder(field_d2.getText().toString()).build().evaluate(),
                field_d3_value = new ExpressionBuilder(field_d3.getText().toString()).build().evaluate(),
                field_d4_value = new ExpressionBuilder(field_d4.getText().toString()).build().evaluate();

        double result = field_a1_value * solve_3d_determinant(field_b2_value, field_b3_value, field_b4_value, field_c2_value, field_c3_value, field_c4_value, field_d2_value, field_d3_value, field_d4_value) -
                field_a2_value * solve_3d_determinant(field_b1_value, field_b3_value, field_b4_value, field_c1_value, field_c3_value, field_c4_value, field_d1_value, field_d3_value, field_d4_value) +
                field_a3_value * solve_3d_determinant(field_b1_value, field_b2_value, field_b4_value, field_c1_value, field_c2_value, field_c4_value, field_d1_value, field_d2_value, field_d4_value) -
                field_a4_value * solve_3d_determinant(field_b1_value, field_b2_value, field_b3_value, field_c1_value, field_c2_value, field_c3_value, field_d1_value, field_d2_value, field_d3_value);

        field_answer.setText(String.valueOf(result));
    }
}
