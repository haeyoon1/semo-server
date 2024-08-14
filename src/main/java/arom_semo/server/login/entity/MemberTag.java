package arom_semo.server.login.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "MemberTag")
public class MemberTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_tag_id")
    private Long memberTagId;

    @Column(nullable = false)
    private String tag;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member; // 'Member'로 연결

}

