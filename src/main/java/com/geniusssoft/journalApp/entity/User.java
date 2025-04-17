package com.geniusssoft.journalApp.entity;

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
public class User {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String username;
    
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
    
    // Getters and Setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public List<JournalEntity> getJournalEntries() {
        return journalEntries;
    }

    public void setJournalEntries(List<JournalEntity> journalEntries) {
        this.journalEntries = journalEntries;
    }
}
