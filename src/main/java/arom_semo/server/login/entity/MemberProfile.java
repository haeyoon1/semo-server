package arom_semo.server.login.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MemberProfile")
public class MemberProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id값이 겹치지 않고 생성
    private Long memberProfileId;

    @Column(length = 8, nullable = false)
    private String nickname;

    @Column(nullable = false)
    private int age;

    @Column
    private String profileImageURL;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public enum Gender{
        M,F
    }

    @OneToOne(mappedBy = "memberProfile")
    private Member member;
}
