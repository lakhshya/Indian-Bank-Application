package com.example.indianbankapp.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class EMICalculator extends Fragment implements OnClickListener {

    private static final String ARG_ID_NUMBER = "section_number";

    public static DecimalFormat twodformat = new DecimalFormat("#0.00");
    EditText et1;
    EditText et2;
    EditText et3;
    TextView tv1;

    EMICalculator(int id) {
            Bundle args = new Bundle();
            args.putInt(ARG_ID_NUMBER, id);
            setArguments(args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview1 = inflater.inflate(R.layout.fragment_emi_calculator, container, false);

        et1 = (EditText) rootview1.findViewById(R.id.editText1);
        et2 = (EditText) rootview1.findViewById(R.id.editText2);
        et3 = (EditText) rootview1.findViewById(R.id.editText3);
        tv1 = (TextView) rootview1.findViewById(R.id.textView5);

        Button b1 = (Button) rootview1.findViewById(R.id.button1);


        b1.setOnClickListener(this);
        return rootview1;
    }

    @Override
    public void onClick(View v) {

        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


        String str1 = et1.getText().toString();
        String str2 = et2.getText().toString();
        String str3 = et3.getText().toString();


        if ((str1).matches("")) {
            Toast.makeText(getActivity(), "Fill in the principal", Toast.LENGTH_SHORT).show();
            return;
        }
        else if ((str2).matches("")) {
            Toast.makeText(getActivity(), "Fill in number of months", Toast.LENGTH_LONG).show();
            return;
        }
        else if ((str3).matches("")) {
            Toast.makeText(getActivity(), "Fill in rate of interest", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            try {
                Double p = Double.parseDouble(str1);
                Double r1 = Double.parseDouble(str3);
                int n = Integer.parseInt(str2);
                Double r = r1 / (100 * 12);
                double p1 = Math.pow(1 + r, n);
                Double p2 = p1 - 1;
                Double p3 = p * r * p1;
                Double EMID = p3 / p2;
                Double EMI = Double.parseDouble(twodformat.format(EMID));
                String text = Double.toString(EMI);
                tv1.setText(text);
            }
            catch (Exception e) {
                Toast.makeText(getActivity(), "Fill the values correctly", Toast.LENGTH_LONG).show();
                return;
            }
        }
    }

}

