package com.example.indianbankapp.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class FDCalculator extends Fragment implements OnClickListener {

    private static final String ARG_ID_NUMBER = "section_number";

    public static DecimalFormat twodformat = new DecimalFormat("#0.00");
    EditText et1;
    EditText et2;
    EditText et3;
    EditText et4;
    TextView tv1;
    TextView tv2;

    FDCalculator(int id) {
        Bundle args = new Bundle();
        args.putInt(ARG_ID_NUMBER, id);
        setArguments(args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview2 = inflater.inflate(R.layout.fragment_fd_calculator, container, false);

        et1 = (EditText) rootview2.findViewById(R.id.editText1);
        et2 = (EditText) rootview2.findViewById(R.id.editText2);
        et3 = (EditText) rootview2.findViewById(R.id.editText3);
        et4 = (EditText) rootview2.findViewById(R.id.editText4);
        tv1 = (TextView) rootview2.findViewById(R.id.textView6);
        tv2 = (TextView) rootview2.findViewById(R.id.textView9);

        Button b1 = (Button) rootview2.findViewById(R.id.button1);
        b1.setOnClickListener(this);

        return rootview2;
    }

    @Override
    public void onClick(View v) {

        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        String st1 = et1.getText().toString();
        String st2 = et2.getText().toString();
        String st3 = et3.getText().toString();
        String st4 = et4.getText().toString();

        if (st1.matches("")) {
            Toast.makeText(getActivity(), "Fill in the principal", Toast.LENGTH_LONG).show();
            return;
        } else if (st2.matches("")) {
            Toast.makeText(getActivity(), "Fill  in the number years", Toast.LENGTH_LONG).show();
            return;
        }

        if (st3.matches("")) {
            Toast.makeText(getActivity(), "Fill in the rate of interest", Toast.LENGTH_LONG).show();
            return;
        }

        if (st4.matches("")) {
            Toast.makeText(getActivity(), "Fill in the months of compound", Toast.LENGTH_LONG).show();
            return;
        } else {
            try {
                Double p = (double) Integer.parseInt(st1);
                Double t = (double) Integer.parseInt(st2);
                Double r1 = (double) Integer.parseInt(st3);
                Double n1 = (double) Integer.parseInt(st4);

                Double r = r1 / 100;

                Double n = 12 / n1;

                Double s1 = 1 + (r / n);

                Double s2 = Math.pow(s1, n * t);

                Double fixeddepd = p * s2;

                Double inted = fixeddepd - p;

                Double fixeddep = Double.parseDouble(twodformat.format(fixeddepd));

                Double inte = Double.parseDouble(twodformat.format(inted));

                String st = Double.toString(fixeddep);

                String st8 = Double.toString(inte);

                tv1.setText(st);

                tv2.setText(st8);
            } catch (Exception e) {
                Toast.makeText(getActivity(), "Please Fill in the coorect values", Toast.LENGTH_LONG).show();
                return;
            }
        }
    }

}
