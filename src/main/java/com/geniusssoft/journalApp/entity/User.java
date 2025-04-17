package com.geniusssoft.journalApp.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "users")
@Data
public class User {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    @NonNull
    private String username;
    
    @NonNull
    private String password;
    
    private Set<String> roles = new HashSet<>();
    
    @DBRef
    private List<JournalEntity> journalEntries = new ArrayList<>();
    
    public User() {
        // Default constructor required by MongoDB
    }
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.roles.add("ROLE_USER"); // Default role
    }
}
