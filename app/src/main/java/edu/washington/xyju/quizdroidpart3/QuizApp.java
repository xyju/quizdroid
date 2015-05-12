package edu.washington.xyju.quizdroidpart3;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * Created by xyju on 15/5/12.
 */

public class QuizApp extends Application implements TopicRepository<String> {
    public static QuizApp instance = null;

    public QuizApp(){
        if(instance == null){
            instance = this;
        }else{
            throw new RuntimeException("Cannot create more than one QuizApp");
        }
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d("QuizApp", "QuizApp is being loaded and run correctly! ");
    }


    @Override
    public void store(String elements) {

    }

    @Override
    public void readJson(String fileName,Topic[] topic) {

        String json = null;

        // Fetch data.json in assets/ folder
        try {
            InputStream inputStream = getAssets().open(fileName);
            json = readJSONFile(inputStream);

            JSONArray jsonData = new JSONArray(json);
            for(int i = 0; i < jsonData.length();i++) {
                JSONObject topics = jsonData.getJSONObject(i);
                topic[i].setTitle(topics.getString("title"));
                topic[i].setsDes(topics.getString("desc"));

                JSONArray questions = topics.getJSONArray("questions");
                topic[i].setLength(questions.length());
                for (int j = 0; j < questions.length(); j++) {

                    JSONObject quizs = questions.getJSONObject(j);
                    topic[i].getQuestion(j).setText(quizs.getString("text"));
                    topic[i].getQuestion(j).setAnswer(quizs.getString("answer"));

                    JSONArray answers = quizs.getJSONArray("answers");
                    for (int k = 0; k < answers.length(); k++){
                        switch(k) {
                            case 0:
                                topic[i].getQuestion(j).setAnswer1(answers.getString(k));
                                break;
                            case 1:
                                topic[i].getQuestion(j).setAnswer2(answers.getString(k));
                                break;
                            case 2:
                                topic[i].getQuestion(j).setAnswer3(answers.getString(k));
                                break;
                            case 3:
                                topic[i].getQuestion(j).setAnswer4(answers.getString(k));
                                break;
                            default:
                                break;
                        }
                    }

                    topic[i].setQuestion(topic[i].getQuestion(j));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String readJSONFile(InputStream inputStream) throws IOException {

        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();
        return new String(buffer, "UTF-8");

    }
}

class Quiz{
    public Quiz(){

    }

    private String text;
    private String answer;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;

    public void setText(String text){
        this.text = text;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }

    public void setAnswer1(String answer1){
        this.answer1 = answer1;
    }

    public void setAnswer2(String answer2){
        this.answer2 = answer2;
    }

    public void setAnswer3(String answer3){
        this.answer3 = answer3;
    }

    public void setAnswer4(String answer4){
        this.answer4 = answer4;
    }

    public String getText(){
        return text;
    }

    public String getAnswer(){
        return answer;
    }

    public String getAnswer1(){
        return answer1;
    }

    public String getAnswer2(){
        return answer2;
    }

    public String getAnswer3(){
        return answer3;
    }

    public String getAnswer4(){
        return answer4;
    }

}

class Topic{
    public Topic(){

    }

    private String title;
    private String sDes;
    private String lDes;
    private List<Quiz> questions = new ArrayList<Quiz>();

    public void setTitle(String title){
        this.title = title;
    }

    public void setsDes(String sDes){
        this.sDes = sDes;
    }

    public void setlDes(String lDes){
        this.lDes = lDes;
    }

    public void setQuestion(Quiz question){
        this.questions.add(question);
    }

    public String getTitle(){
        return title;
    }

    public String getsDes(){
        return sDes;
    }

    public String getsLes(){
        return lDes;
    }

    public Quiz getQuestion(int i){
        return questions.get(i);
    }

    public List<Quiz> getQuestionsList(){
        return questions;
    }

    public void setLength(int i){
        for(int counter = 0;counter<i;counter++) {
            questions.add(new Quiz());
        }
    }
}
