package com.example.keanweng_feelsbook;

import java.util.Date;

public class Emotion {

    protected String moodtype;
    protected Date date;
    protected String comment;

    public Emotion(){
        this.date = new Date();
    }

    public Emotion(String comment){
        this.comment = comment;
        this.date = new Date();
    }

    public void setComment(String comment) throws TooLongCommentException {
        if (comment.length()>100){
            throw new TooLongCommentException("This comment is too long! Please enter a comment with less than 100 character!");
        }
        this.comment = comment;
    }

    public void setDate() {
        this.date = date;
    }

    public String getMessage() {
        return this.comment;
    }

    public Date getDate(){
        return this.date;
    }

    public String toString() {
        return this.date.toString()+" | "+this.comment;
    }

}
