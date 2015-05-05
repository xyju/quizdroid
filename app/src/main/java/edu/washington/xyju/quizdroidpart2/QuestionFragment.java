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
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class QuestionFragment extends android.support.v4.app.Fragment {
    private static int i,j;
    private static int mcAnswer;
    private int selectedOption;
    private static String yourAnswer;
    TextView topic;
    RadioButton r1,r2,r3,r4,rChoice;
    RadioGroup radio;
    SendValue send;
    Button submit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_question, container, false);
        Bundle bundle=getArguments();
        i =bundle.getInt("passValue2");

        topic = (TextView) view.findViewById(R.id.question);
        r1 = (RadioButton) view.findViewById(R.id.radioc1);
        r2 = (RadioButton) view.findViewById(R.id.radioc2);
        r3 = (RadioButton) view.findViewById(R.id.radioc3);
        r4 = (RadioButton) view.findViewById(R.id.radioc4);

        switch(i){
            case 0:
                if(j==0) {
                    topic.setText("(1) 14 + 17 = ?");
                    r1.setText("29");
                    r2.setText("30");
                    r3.setText("31");
                    r4.setText("32");
                }else{
                    topic.setText("(2) 12 x 12 = ?");
                    r1.setText("121");
                    r2.setText("144");
                    r3.setText("169");
                    r4.setText("196");
                }
                break;
            case 1:
                if(j==0) {
                    topic.setText("(1) ____ is a unit of speed:");
                    r1.setText("m/s");
                    r2.setText("s");
                    r3.setText("kg");
                    r4.setText("hr");
                }else{
                    topic.setText("(2) How far would you travel moving at 12 m/s for 3.00 minutes?");
                    r1.setText("36.0m");
                    r2.setText("2160m");
                    r3.setText("40.0m");
                    r4.setText("36.0miles");
                }
                break;
            case 2:
                if(j==0) {
                    topic.setText("(1) The Fantastic Four have the headquarters in what building?");
                    r1.setText("Stark Tower");
                    r2.setText("Fantastic Headquarters");
                    r3.setText("Baxter Building");
                    r4.setText("Xavier Institute");
                }else{
                    topic.setText("(2) Peter Parker works as a photographer for:");
                    r1.setText("The Daily Planet");
                    r2.setText("The Daily Bugle");
                    r3.setText("The New York Times");
                    r4.setText("The Rolling Stone");
                }
                break;
        }


        radio = (RadioGroup) view.findViewById(R.id.radiobtn);
        submit = (Button) view.findViewById(R.id.submit);

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                submit.setVisibility(View.VISIBLE);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOption = radio.getCheckedRadioButtonId();

                if (selectedOption > 0) {
                    rChoice = (RadioButton) view.findViewById(selectedOption);
                    yourAnswer = rChoice.getText().toString();

                    switch (i) {
                        case 0:
                            if (j == 0 && yourAnswer == "31") {
                                mcAnswer++;
                            } else if (j == 1 && yourAnswer == "144") {
                                mcAnswer++;
                            }
                            break;
                        case 1:
                            if (j == 0 && yourAnswer == "m/s") {
                                mcAnswer++;
                            } else if (j == 1 && yourAnswer == "2160m") {
                                mcAnswer++;
                            }
                            break;
                        case 2:
                            if (j == 0 && yourAnswer == "Baxter Building") {
                                mcAnswer++;
                            } else if (j == 1 && yourAnswer == "The Daily Bugle") {
                                mcAnswer++;
                            }
                            break;
                    }
                    j++;
                    send.passData(mcAnswer, j, yourAnswer);

                    if(j == 2){
                        j = 0 ;
                        mcAnswer = 0;
                    }

                    Intent multiple = new Intent(getActivity(),MultiUseActivity.class);
                    multiple.putExtra("jumpTo",2 );
                    startActivity(multiple);

                }
            }
        });
        return view;

    }


    public interface SendValue{
        public void passData(int correct, int total,String answer);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            send = (SendValue) activity;
        }catch(ClassCastException e){
            throw new ClassCastException("You must to implement SendValue method!");
        }
    }

}
