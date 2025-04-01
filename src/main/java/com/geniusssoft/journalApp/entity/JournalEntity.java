package com.geniusssoft.journalApp.entity;

import com.fasterxml.jackson.annotation.JsonTypeId;

import java.lang.annotation.Documented;

@Document
public class JournalEntity {
    // used to identify the journal entry
    @Id // this is the primary key
    private String id;
    private String title;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
