package com.berkay.ranker.rankEvent.data.entity;

import com.berkay.ranker.user.data.entity.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rank_event")
public class RankEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "actor", nullable = false)
    private User actor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject", nullable = false)
    private User subject;

    @Column(name = "rank_value")
    private Long rankValue;

    @Column(name = "total_candidates")
    private Long totalCandidates;

    @ManyToOne
    @JoinColumn(name = "ranking_type")
    private RankingType rankingType;
}
