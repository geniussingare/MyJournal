package com.geniusssoft.journalApp.entity;

import com.fasterxml.jackson.annotation.JsonTypeId;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries") // this is the name of the collection in MongoDB
public class JournalEntity {
    // used to identify the journal entry
    @Id // this is the primary key
    private ObjectId id;
    private LocalDateTime date;
    private String title;
    private String content;

    public LocalDateTime getdate() {
        return date;
    }

    public void setdate(LocalDateTime date) {
        this.date = date;
    }



    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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
