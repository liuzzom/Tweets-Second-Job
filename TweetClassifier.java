import com.aliasi.classify.LMClassifier;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class TweetClassifier {
    private LMClassifier classifier;

    public TweetClassifier(){
        try {
            FileInputStream fis = new FileInputStream("/home/mauroliuzzo/IdeaProjects/Tweets-Second-Job/classifier/subjectivity.model");
            ObjectInputStream in = new ObjectInputStream(fis);
            this.classifier = (LMClassifier) in.readObject();
        }
        catch (Exception exc){
            exc.printStackTrace();
        }

    }

    public LMClassifier getClassifier(){
        return this.classifier;
    }

    public String evaluate(String value){
        return this.classifier.classify(value).bestCategory();
    }
}
