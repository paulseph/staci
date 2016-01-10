package com.paulseph.staci.pageobjectmodels;

/**
 * Class representing a simple note with title and plain text body
 */
public class EvernoteNote {
    private String title = "";
    private String body = "";

    public EvernoteNote(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean equals(EvernoteNote evernoteNote) {
        return this.title.equals(evernoteNote.title)
                &&
                this.body.equals(evernoteNote.body);
    }

    public boolean equals (Object object) {
        boolean equals = false;

        if (object != null && object instanceof EvernoteNote) {
            equals = this.equals((EvernoteNote) object);
        }

        return equals;
    }

    public int hashCode() {
        return this.toString().hashCode();
    }

    public String toString() {
        return "Title: <" + this.title + "> Body: <" + this.body + ">";
    }
}
