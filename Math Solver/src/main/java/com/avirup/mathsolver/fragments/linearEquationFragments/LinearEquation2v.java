package com.avirup.mathsolver.fragments.linearEquationFragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.avirup.mathsolver.R;

import net.objecthunter.exp4j.ExpressionBuilder;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LinearEquation2v extends Fragment {

    private EditText field_x1, field_x2, field_y1, field_y2, field_c1, field_c2;
    private TextView text_answer;
    private Button submit_button, reset_button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_linear_equation_2v, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialize(view);

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
            } catch (Exception e1) {
                text_answer.setText("");
                Toast.makeText(requireActivity().getApplicationContext(), "Enter Valid Input", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void reset(@NotNull View view) {

        field_x1.setText("");
        field_x2.setText("");
        field_y1.setText("");
        field_y2.setText("");
        field_c1.setText("");
        field_c2.setText("");
        text_answer.setText("");

    }

    private void initialize(@NotNull View view) {
        field_x1 = view.findViewById(R.id.text_x1_le_2v);
        field_x2 = view.findViewById(R.id.text_x2_le_2v);
        field_y1 = view.findViewById(R.id.text_y1_le_2v);
        field_y2 = view.findViewById(R.id.text_y2_le_2v);
        field_c1 = view.findViewById(R.id.text_c1_le_2v);
        field_c2 = view.findViewById(R.id.text_c2_le_2v);
        text_answer = view.findViewById(R.id.text_answer_le_2v);
        submit_button = view.findViewById(R.id.sumbit_button_le_2v);
        reset_button = view.findViewById(R.id.reset_button_le_2v);
    }

    private void solve_simple() {
        if (Objects.equals(field_x1.getText().toString(), "") ||
                Objects.equals(field_x2.getText().toString(), "") ||
                Objects.equals(field_y1.getText().toString(), "") ||
                Objects.equals(field_y2.getText().toString(), "") ||
                Objects.equals(field_c1.getText().toString(), "") ||
                Objects.equals(field_c2.getText().toString(), ""))
            return;
        double field_x1_value = Double.parseDouble(field_x1.getText().toString()),
                field_x2_value = Double.parseDouble(field_x2.getText().toString()),
                field_y1_value = Double.parseDouble(field_y1.getText().toString()),
                field_y2_value = Double.parseDouble(field_y2.getText().toString()),
                field_c1_value = Double.parseDouble(field_c1.getText().toString()),
                field_c2_value = Double.parseDouble(field_c2.getText().toString());

        if (field_x1_value / field_x2_value == field_y1_value / field_y2_value && field_y1_value / field_y2_value == field_c1_value / field_c2_value) {
            Toast.makeText(getContext(), "The lines are same", Toast.LENGTH_SHORT).show();
            return;
        } else if (field_x1_value / field_x2_value == field_y1_value / field_y2_value && field_y1_value / field_y2_value != field_c1_value / field_c2_value) {
            Toast.makeText(getContext(), "The lines are parallel", Toast.LENGTH_SHORT).show();
            return;
        }

        RealMatrix coefficients = new Array2DRowRealMatrix(new double[][]{
                {field_x1_value, field_y1_value},
                {field_x2_value, field_y2_value}}
        );
        DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();
        RealVector solution = solver.solve(new ArrayRealVector(new double[]{field_c1_value, field_c2_value}, false));
        var X = Math.round(solution.getEntry(0) * 100) / 100.0;
        var Y = Math.round(solution.getEntry(1) * 100) / 100.0;
        text_answer.setText("""
                X: $X
                Y: $Y""".replace("$X", String.valueOf(X)).replace("$Y", String.valueOf(Y)));
    }

    private void solve_expression() {
        double field_x1_value = new ExpressionBuilder(field_x1.getText().toString()).build().evaluate(),
                field_x2_value = new ExpressionBuilder(field_x2.getText().toString()).build().evaluate(),
                field_y1_value = new ExpressionBuilder(field_y1.getText().toString()).build().evaluate(),
                field_y2_value = new ExpressionBuilder(field_y2.getText().toString()).build().evaluate(),
                field_c1_value = new ExpressionBuilder(field_c1.getText().toString()).build().evaluate(),
                field_c2_value = new ExpressionBuilder(field_c2.getText().toString()).build().evaluate();


        if (field_x1_value / field_x2_value == field_y1_value / field_y2_value && field_y1_value / field_y2_value == field_c1_value / field_c2_value) {
            Toast.makeText(getContext(), "The lines are same", Toast.LENGTH_SHORT).show();
            return;
        } else if (field_x1_value / field_x2_value == field_y1_value / field_y2_value && field_y1_value / field_y2_value != field_c1_value / field_c2_value) {
            Toast.makeText(getContext(), "The lines are parallel", Toast.LENGTH_SHORT).show();
            return;
        }

        RealMatrix coefficients = new Array2DRowRealMatrix(new double[][]{{field_x1_value, field_y1_value}, {field_x2_value, field_y2_value}});
        DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();
        RealVector solution = solver.solve(new ArrayRealVector(new double[]{field_c1_value, field_c2_value}, false));
        var X = Math.round(solution.getEntry(0) * 100) / 100.0;
        var Y = Math.round(solution.getEntry(1) * 100) / 100.0;
        text_answer.setText("""
                X: $X
                Y: $Y"""
                .replace("$X", String.valueOf(X))
                .replace("$Y", String.valueOf(Y))
        );
    }
}
