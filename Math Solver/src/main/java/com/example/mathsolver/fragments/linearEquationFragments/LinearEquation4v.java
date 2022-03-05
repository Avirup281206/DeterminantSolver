package com.example.mathsolver.fragments.linearEquationFragments;

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

import com.example.mathsolver.R;

import net.objecthunter.exp4j.ExpressionBuilder;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LinearEquation4v extends Fragment {

    private EditText field_w1, field_w2, field_w3, field_w4, field_x1, field_x2, field_x3, field_x4, field_y1, field_y2, field_y3, field_y4, field_z1, field_z2, field_z3, field_z4, field_c1, field_c2, field_c3, field_c4;
    private TextView text_answer;
    private Button submit_button, reset_button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_linear_equation_4v, container, false);
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
            } catch (SingularMatrixException singularMatrixException) {
                text_answer.setText("");
                Toast.makeText(requireActivity().getApplicationContext(), "No Unique Solution", Toast.LENGTH_SHORT).show();
            } catch (Exception e1) {
                text_answer.setText("");
                Toast.makeText(requireActivity().getApplicationContext(), "Enter Valid Input", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void reset(@NotNull View view) {

        field_w1.setText("");
        field_w2.setText("");
        field_w3.setText("");
        field_w4.setText("");
        field_x1.setText("");
        field_x2.setText("");
        field_x3.setText("");
        field_x4.setText("");
        field_y1.setText("");
        field_y2.setText("");
        field_y3.setText("");
        field_y4.setText("");
        field_z1.setText("");
        field_z2.setText("");
        field_z3.setText("");
        field_z4.setText("");
        field_c1.setText("");
        field_c2.setText("");
        field_c3.setText("");
        field_c4.setText("");
        text_answer.setText("");

    }

    private void initialize(@NotNull View view) {
        field_w1 = view.findViewById(R.id.text_w1_le_4v);
        field_w2 = view.findViewById(R.id.text_w2_le_4v);
        field_w3 = view.findViewById(R.id.text_w3_le_4v);
        field_w4 = view.findViewById(R.id.text_w4_le_4v);
        field_x1 = view.findViewById(R.id.text_x1_le_4v);
        field_x2 = view.findViewById(R.id.text_x2_le_4v);
        field_x3 = view.findViewById(R.id.text_x3_le_4v);
        field_x4 = view.findViewById(R.id.text_x4_le_4v);
        field_y1 = view.findViewById(R.id.text_y1_le_4v);
        field_y2 = view.findViewById(R.id.text_y2_le_4v);
        field_y3 = view.findViewById(R.id.text_y3_le_4v);
        field_y4 = view.findViewById(R.id.text_y4_le_4v);
        field_z1 = view.findViewById(R.id.text_z1_le_4v);
        field_z2 = view.findViewById(R.id.text_z2_le_4v);
        field_z3 = view.findViewById(R.id.text_z3_le_4v);
        field_z4 = view.findViewById(R.id.text_z4_le_4v);
        field_c1 = view.findViewById(R.id.text_c1_le_4v);
        field_c2 = view.findViewById(R.id.text_c2_le_4v);
        field_c3 = view.findViewById(R.id.text_c3_le_4v);
        field_c4 = view.findViewById(R.id.text_c4_le_4v);
        text_answer = view.findViewById(R.id.text_answer_le_4v);
        submit_button = view.findViewById(R.id.submit_button_le_4v);
        reset_button = view.findViewById(R.id.reset_button_le_4v);
    }

    private void solve_simple() {
        if (Objects.equals(field_w1.getText().toString(), "") ||
                Objects.equals(field_w2.getText().toString(), "") ||
                Objects.equals(field_w3.getText().toString(), "") ||
                Objects.equals(field_w4.getText().toString(), "") ||
                Objects.equals(field_x1.getText().toString(), "") ||
                Objects.equals(field_x2.getText().toString(), "") ||
                Objects.equals(field_x3.getText().toString(), "") ||
                Objects.equals(field_x4.getText().toString(), "") ||
                Objects.equals(field_y1.getText().toString(), "") ||
                Objects.equals(field_y2.getText().toString(), "") ||
                Objects.equals(field_y3.getText().toString(), "") ||
                Objects.equals(field_y4.getText().toString(), "") ||
                Objects.equals(field_z1.getText().toString(), "") ||
                Objects.equals(field_z2.getText().toString(), "") ||
                Objects.equals(field_z3.getText().toString(), "") ||
                Objects.equals(field_z4.getText().toString(), "") ||
                Objects.equals(field_c1.getText().toString(), "") ||
                Objects.equals(field_c2.getText().toString(), "") ||
                Objects.equals(field_c3.getText().toString(), "") ||
                Objects.equals(field_c4.getText().toString(), ""))
            return;
        double field_w1_value = Double.parseDouble(field_w1.getText().toString()),
                field_w2_value = Double.parseDouble(field_w2.getText().toString()),
                field_w3_value = Double.parseDouble(field_w3.getText().toString()),
                field_w4_value = Double.parseDouble(field_w4.getText().toString()),
                field_x1_value = Double.parseDouble(field_x1.getText().toString()),
                field_x2_value = Double.parseDouble(field_x2.getText().toString()),
                field_x3_value = Double.parseDouble(field_x3.getText().toString()),
                field_x4_value = Double.parseDouble(field_x4.getText().toString()),
                field_y1_value = Double.parseDouble(field_y1.getText().toString()),
                field_y2_value = Double.parseDouble(field_y2.getText().toString()),
                field_y3_value = Double.parseDouble(field_y3.getText().toString()),
                field_y4_value = Double.parseDouble(field_y4.getText().toString()),
                field_z1_value = Double.parseDouble(field_z1.getText().toString()),
                field_z2_value = Double.parseDouble(field_z2.getText().toString()),
                field_z3_value = Double.parseDouble(field_z3.getText().toString()),
                field_z4_value = Double.parseDouble(field_z4.getText().toString()),
                field_c1_value = Double.parseDouble(field_c1.getText().toString()),
                field_c2_value = Double.parseDouble(field_c2.getText().toString()),
                field_c3_value = Double.parseDouble(field_c3.getText().toString()),
                field_c4_value = Double.parseDouble(field_c4.getText().toString());

        RealMatrix coefficients = new Array2DRowRealMatrix(new double[][]{
                {field_w1_value, field_x1_value, field_y1_value, field_z1_value},
                {field_w2_value, field_x2_value, field_y2_value, field_z2_value},
                {field_w3_value, field_x3_value, field_y3_value, field_z3_value},
                {field_w4_value, field_x4_value, field_y4_value, field_z4_value}}
        );
        DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();
        RealVector solution = solver.solve(new ArrayRealVector(new double[]{field_c1_value, field_c2_value, field_c3_value, field_c4_value}, false));
        var W = Math.round(solution.getEntry(0) * 100) / 100.0;
        var X = Math.round(solution.getEntry(1) * 100) / 100.0;
        var Y = Math.round(solution.getEntry(2) * 100) / 100.0;
        var Z = Math.round(solution.getEntry(3) * 100) / 100.0;
        text_answer.setText("""
                W: $W
                X: $X
                Y: $Y
                Z: $Z"""
                .replace("$W", String.valueOf(W))
                .replace("$X", String.valueOf(X))
                .replace("$Y", String.valueOf(Y))
                .replace("$Z", String.valueOf(Z))
        );
    }

    private void solve_expression() {
        double field_w1_value = new ExpressionBuilder(field_w1.getText().toString()).build().evaluate(),
                field_w2_value = new ExpressionBuilder(field_w2.getText().toString()).build().evaluate(),
                field_w3_value = new ExpressionBuilder(field_w3.getText().toString()).build().evaluate(),
                field_w4_value = new ExpressionBuilder(field_w4.getText().toString()).build().evaluate(),
                field_x1_value = new ExpressionBuilder(field_x1.getText().toString()).build().evaluate(),
                field_x2_value = new ExpressionBuilder(field_x2.getText().toString()).build().evaluate(),
                field_x3_value = new ExpressionBuilder(field_x3.getText().toString()).build().evaluate(),
                field_x4_value = new ExpressionBuilder(field_x4.getText().toString()).build().evaluate(),
                field_y1_value = new ExpressionBuilder(field_y1.getText().toString()).build().evaluate(),
                field_y2_value = new ExpressionBuilder(field_y2.getText().toString()).build().evaluate(),
                field_y3_value = new ExpressionBuilder(field_y3.getText().toString()).build().evaluate(),
                field_y4_value = new ExpressionBuilder(field_y4.getText().toString()).build().evaluate(),
                field_z1_value = new ExpressionBuilder(field_z1.getText().toString()).build().evaluate(),
                field_z2_value = new ExpressionBuilder(field_z2.getText().toString()).build().evaluate(),
                field_z3_value = new ExpressionBuilder(field_z3.getText().toString()).build().evaluate(),
                field_z4_value = new ExpressionBuilder(field_z4.getText().toString()).build().evaluate(),
                field_c1_value = new ExpressionBuilder(field_c1.getText().toString()).build().evaluate(),
                field_c2_value = new ExpressionBuilder(field_c2.getText().toString()).build().evaluate(),
                field_c3_value = new ExpressionBuilder(field_c3.getText().toString()).build().evaluate(),
                field_c4_value = new ExpressionBuilder(field_c4.getText().toString()).build().evaluate();

        RealMatrix coefficients = new Array2DRowRealMatrix(new double[][]{
                {field_w1_value, field_x1_value, field_y1_value, field_z1_value},
                {field_w2_value, field_x2_value, field_y2_value, field_z2_value},
                {field_w3_value, field_x3_value, field_y3_value, field_z3_value},
                {field_w4_value, field_x4_value, field_y4_value, field_z4_value}}
        );
        DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();
        RealVector solution = solver.solve(new ArrayRealVector(new double[]{field_c1_value, field_c2_value, field_c3_value, field_c4_value}, false));
        var W = Math.round(solution.getEntry(0) * 100) / 100.0;
        var X = Math.round(solution.getEntry(1) * 100) / 100.0;
        var Y = Math.round(solution.getEntry(2) * 100) / 100.0;
        var Z = Math.round(solution.getEntry(3) * 100) / 100.0;
        text_answer.setText("""
                W: $W
                X: $X
                Y: $Y
                Z: $Z"""
                .replace("$W", String.valueOf(W))
                .replace("$X", String.valueOf(X))
                .replace("$Y", String.valueOf(Y))
                .replace("$Z", String.valueOf(Z))
        );
    }
}
