package com.example.langley_lab4;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btnCalc).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                TextView t = (TextView)view.getRootView().findViewById(R.id.output);
                EditText bill = (EditText)view.getRootView().findViewById(R.id.inputBill);
                EditText tip = (EditText)view.getRootView().findViewById(R.id.inputTip);
                EditText people = (EditText)view.getRootView().findViewById(R.id.inputNumOfPeople);
                if(numericOrNull(bill.getText().toString()) == false||numericOrNull(tip.getText().toString()) == false || numericOrNull(people.getText().toString()) == false){
                    Toast.makeText(getActivity(), "Please make sure all fields are entered and numeric", Toast.LENGTH_LONG).show();
                }
                else if (Double.parseDouble(bill.getText().toString()) == 0 || Double.parseDouble(tip.getText().toString()) == 0 || Double.parseDouble(people.getText().toString()) == 0){
                    Toast.makeText(getActivity(), "Please make sure fields do not equal zero", Toast.LENGTH_LONG).show();
                }
                else{

                    DecimalFormat decimal = new DecimalFormat("#.00");
                    double billAmount = Double.parseDouble(bill.getText().toString());
                    double tipAmount = Double.parseDouble(tip.getText().toString());
                    double peopleAmount = Double.valueOf(Integer.parseInt(people.getText().toString()));

                    //bill split up by amount of people
                    double perPerson = billAmount / peopleAmount;

                    // bill split by amount tip percent and then split by amount of people
                    double tipPerPerson = billAmount * (tipAmount/100);
                    tipPerPerson = tipPerPerson / peopleAmount;

                    //finishing total
                    double totalAmount = tipPerPerson + perPerson;
                    t.setText(String.valueOf(decimal.format(totalAmount)));
                }
            }
        });
    }
    public static boolean numericOrNull(String string){
        if (string.isEmpty()) {
            return false;
        }
        try {
            double parsed = Double.parseDouble(string);
        } catch (NumberFormatException num){
                return false;
            }
        return true;
        }

    }

