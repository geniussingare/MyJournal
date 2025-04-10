package com.geniusssoft.journalApp.entity;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
@Data// this is the name of the collection in MongoDB
@NoArgsConstructor
public class JournalEntity {
    // used to identify the journal entry
    @Id // this is the primary key
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime date;
}
