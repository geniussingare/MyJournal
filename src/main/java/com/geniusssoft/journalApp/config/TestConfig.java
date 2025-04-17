package com.geniusssoft.journalApp.config;

import com.geniusssoft.journalApp.repositorys.JournalRepository;
import com.geniusssoft.journalApp.repositorys.TestRepositories.TestJournalRepository;
import com.geniusssoft.journalApp.repositorys.TestRepositories.TestUserRepository;
import com.geniusssoft.journalApp.repositorys.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * Test configuration that provides in-memory repositories
 * Activate with -Dspring.profiles.active=test
 */
@Configuration
@Profile("test")
public class TestConfig {

    @Bean
    @Primary
    public UserRepository userRepository() {
        return new TestUserRepository();
    }

    @Bean
    @Primary
    public JournalRepository journalRepository() {
        return new TestJournalRepository();
    }
} 