package com.example.keanweng_feelsbook;

import java.util.Date;

class Emotion {

    String moodtype;
    private Date date;
    private String comment;

    Emotion(){
        this.date = new Date();
    }

    public void setComment(String comment) throws TooLongCommentException {
        if (comment.length()>100){
            throw new TooLongCommentException("This comment is too long! Please enter a comment with less than 100 character!");
        }
        this.comment = comment;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMoodtype(String moodtype){
        this.moodtype = moodtype;
    }

    public String getComment() {
        return this.comment;
    }

    public Date getDate(){
        return this.date;
    }

    public String getMoodtype() {
        return this.moodtype;
    }

}

