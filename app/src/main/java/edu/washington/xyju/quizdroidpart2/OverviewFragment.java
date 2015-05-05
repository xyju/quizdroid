package edu.washington.xyju.quizdroidpart2;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;



public class OverviewFragment extends android.support.v4.app.Fragment {
    private static int myValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_overview, container, false);

        Bundle bundle = getArguments();
        myValue = bundle.getInt("passValue1");
        switch(myValue) {
            case 0:
                TextView math = (TextView) view.findViewById(R.id.to2);
                math.setVisibility(View.VISIBLE);
                break;
            case 1:
                TextView physics = (TextView) view.findViewById(R.id.to3);
                physics.setVisibility(View.VISIBLE);
                break;
            case 2:
                TextView marvel = (TextView) view.findViewById(R.id.to4);
                marvel.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }

        Button begin = (Button)view.findViewById(R.id.begin);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent multiple = new Intent(getActivity(),MultiUseActivity.class);
                multiple.putExtra("jumpTo", 1);
                startActivity(multiple);
            }
        });
        return view;
    }

}
