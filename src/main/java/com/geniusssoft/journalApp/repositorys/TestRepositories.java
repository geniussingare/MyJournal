package com.geniusssoft.journalApp.repositorys;

import com.geniusssoft.journalApp.entity.JournalEntity;
import com.geniusssoft.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * Test implementations of repositories for use in test profile
 */
public class TestRepositories {

    /**
     * Test implementation of UserRepository for in-memory testing
     */
    public static class TestUserRepository implements UserRepository {
        private final Map<String, User> usersByUsername = new HashMap<>();
        private final Map<ObjectId, User> usersById = new HashMap<>();

        @Override
        public User findByUsername(String username) {
            return usersByUsername.get(username);
        }

        @Override
        public <S extends User> S save(S entity) {
            if (entity.getId() == null) {
                entity.setId(new ObjectId());
            }
            usersByUsername.put(entity.getUsername(), entity);
            usersById.put(entity.getId(), entity);
            return entity;
        }

        @Override
        public <S extends User> List<S> saveAll(Iterable<S> entities) {
            List<S> result = new ArrayList<>();
            entities.forEach(entity -> {
                save(entity);
                result.add(entity);
            });
            return result;
        }

        @Override
        public Optional<User> findById(ObjectId id) {
            return Optional.ofNullable(usersById.get(id));
        }

        @Override
        public boolean existsById(ObjectId id) {
            return usersById.containsKey(id);
        }

        @Override
        public List<User> findAll() {
            return new ArrayList<>(usersById.values());
        }

        @Override
        public List<User> findAll(Sort sort) {
            // Sort not implemented in test repository
            return findAll();
        }

        @Override
        public Page<User> findAll(Pageable pageable) {
            // Pagination not implemented in test repository
            return Page.empty();
        }

        @Override
        public List<User> findAllById(Iterable<ObjectId> ids) {
            List<User> result = new ArrayList<>();
            ids.forEach(id -> {
                User user = usersById.get(id);
                if (user != null) {
                    result.add(user);
                }
            });
            return result;
        }

        @Override
        public long count() {
            return usersById.size();
        }

        @Override
        public void deleteById(ObjectId id) {
            User user = usersById.remove(id);
            if (user != null) {
                usersByUsername.remove(user.getUsername());
            }
        }

        @Override
        public void delete(User entity) {
            if (entity.getId() != null) {
                deleteById(entity.getId());
            }
        }

        @Override
        public void deleteAllById(Iterable<? extends ObjectId> ids) {
            ids.forEach(this::deleteById);
        }

        @Override
        public void deleteAll(Iterable<? extends User> entities) {
            entities.forEach(this::delete);
        }

        @Override
        public void deleteAll() {
            usersByUsername.clear();
            usersById.clear();
        }

        @Override
        public <S extends User> Optional<S> findOne(Example<S> example) {
            // Not implemented in test repository
            return Optional.empty();
        }

        @Override
        public <S extends User> List<S> findAll(Example<S> example) {
            // Not implemented in test repository
            return new ArrayList<>();
        }

        @Override
        public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
            // Not implemented in test repository
            return new ArrayList<>();
        }

        @Override
        public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
            // Not implemented in test repository
            return Page.empty();
        }

        @Override
        public <S extends User> long count(Example<S> example) {
            // Not implemented in test repository
            return 0;
        }

        @Override
        public <S extends User> boolean exists(Example<S> example) {
            // Not implemented in test repository
            return false;
        }

        @Override
        public <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
            // Not implemented in test repository
            return null;
        }

        @Override
        public <S extends User> S insert(S entity) {
            return save(entity);
        }

        @Override
        public <S extends User> List<S> insert(Iterable<S> entities) {
            return saveAll(entities);
        }
    }

    /**
     * Test implementation of JournalRepository for in-memory testing
     */
    public static class TestJournalRepository implements JournalRepository {
        private final Map<ObjectId, JournalEntity> journalsById = new HashMap<>();

        @Override
        public <S extends JournalEntity> S save(S entity) {
            if (entity.getId() == null) {
                entity.setId(new ObjectId());
            }
            journalsById.put(entity.getId(), entity);
            return entity;
        }

        @Override
        public <S extends JournalEntity> List<S> saveAll(Iterable<S> entities) {
            List<S> result = new ArrayList<>();
            entities.forEach(entity -> {
                save(entity);
                result.add(entity);
            });
            return result;
        }

        @Override
        public Optional<JournalEntity> findById(ObjectId id) {
            return Optional.ofNullable(journalsById.get(id));
        }

        @Override
        public boolean existsById(ObjectId id) {
            return journalsById.containsKey(id);
        }

        @Override
        public List<JournalEntity> findAll() {
            return new ArrayList<>(journalsById.values());
        }

        @Override
        public List<JournalEntity> findAll(Sort sort) {
            // Sort not implemented in test repository
            return findAll();
        }

        @Override
        public Page<JournalEntity> findAll(Pageable pageable) {
            // Pagination not implemented in test repository
            return Page.empty();
        }

        @Override
        public List<JournalEntity> findAllById(Iterable<ObjectId> ids) {
            List<JournalEntity> result = new ArrayList<>();
            ids.forEach(id -> {
                JournalEntity journal = journalsById.get(id);
                if (journal != null) {
                    result.add(journal);
                }
            });
            return result;
        }

        @Override
        public long count() {
            return journalsById.size();
        }

        @Override
        public void deleteById(ObjectId id) {
            journalsById.remove(id);
        }

        @Override
        public void delete(JournalEntity entity) {
            if (entity.getId() != null) {
                journalsById.remove(entity.getId());
            }
        }

        @Override
        public void deleteAllById(Iterable<? extends ObjectId> ids) {
            ids.forEach(this::deleteById);
        }

        @Override
        public void deleteAll(Iterable<? extends JournalEntity> entities) {
            entities.forEach(this::delete);
        }

        @Override
        public void deleteAll() {
            journalsById.clear();
        }

        @Override
        public <S extends JournalEntity> Optional<S> findOne(Example<S> example) {
            // Not implemented in test repository
            return Optional.empty();
        }

        @Override
        public <S extends JournalEntity> List<S> findAll(Example<S> example) {
            // Not implemented in test repository
            return new ArrayList<>();
        }

        @Override
        public <S extends JournalEntity> List<S> findAll(Example<S> example, Sort sort) {
            // Not implemented in test repository
            return new ArrayList<>();
        }

        @Override
        public <S extends JournalEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
            // Not implemented in test repository
            return Page.empty();
        }

        @Override
        public <S extends JournalEntity> long count(Example<S> example) {
            // Not implemented in test repository
            return 0;
        }

        @Override
        public <S extends JournalEntity> boolean exists(Example<S> example) {
            // Not implemented in test repository
            return false;
        }

        @Override
        public <S extends JournalEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
            // Not implemented in test repository
            return null;
        }

        @Override
        public <S extends JournalEntity> S insert(S entity) {
            return save(entity);
        }

        @Override
        public <S extends JournalEntity> List<S> insert(Iterable<S> entities) {
            return saveAll(entities);
        }
    }
} 