package edu.washington.xyju.quizdroidpart3;

/**
 * Created by xyju on 15/5/12.
 */
public interface TopicRepository<String> {
    public void store(String elements);
    public void readJson(String fileName,Topic[] topic);
}
