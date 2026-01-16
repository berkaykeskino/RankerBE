package com.berkay.ranker.user.data.entity;

import com.berkay.ranker.friendship.data.entity.Friendship;
import com.berkay.ranker.likeEvent.data.entity.LikeEvent;
import com.berkay.ranker.post.data.entity.Post;
import com.berkay.ranker.rankEvent.data.entity.RankEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "token_id")
    private UserToken userToken;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "sender")
    private List<Friendship> sentFriendships = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    private List<Friendship> receivedFriendships = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<LikeEvent> likeEvents = new ArrayList<>();

    @OneToMany(mappedBy = "actor")
    private List<RankEvent> actorInRanks = new ArrayList<>();

    @OneToMany(mappedBy = "subject")
    private List<RankEvent> subjectInRanks = new ArrayList<>();
}
