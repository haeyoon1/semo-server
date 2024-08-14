package arom_semo.server.login.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "Member")
public class Member extends MutableBaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id값이 겹치지 않고 생성
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String refreshToken;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_profile_id", referencedColumnName = "memberProfileId")
    private MemberProfile memberProfile;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MemberTag> tags;
}
