package com.study.blog.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime createAt;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> post = new ArrayList<>();
    //saving a user can save their new posts.
    //Deleting a user deletes all their posts.
    //Removing a post from the user's posts collection deletes that post from the databases

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(name, user.name) && Objects.equals(createAt, user.createAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name, createAt);
    }

    @PrePersist
    protected void onCreate(){
        createAt = LocalDateTime.now();
        //The @PrePersist annotation helps us manage entity lifecycle events automatically.
        //
        //When a new user is created, JPA will automatically call our onCreate() method to set the creation timestamp.
        //
        //This ensures we always have an accurate record of when each user account was created.
    }
}
