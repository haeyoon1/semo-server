package arom_semo.server.login.service;

import arom_semo.server.login.dto.JoinDto;
import arom_semo.server.login.entity.Member;
import arom_semo.server.login.entity.MemberProfile;
import arom_semo.server.login.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    private final MemberRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(MemberRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void joinProcess(JoinDto joinDto){
        String username = joinDto.getUsername();
        String password = joinDto.getPassword();
        String nickname = joinDto.getNickname();
        int age = joinDto.getAge();
        String gender = joinDto.getGender();

        Boolean isExist = userRepository.existsByUsername(username);
        if (isExist){
            return;
        }

        //memberprofile
        MemberProfile memberProfile = new MemberProfile();

        memberProfile.setNickname(nickname);
        memberProfile.setAge(age);
        memberProfile.setProfileImageURL(null); // Default or optional
        memberProfile.setGender(MemberProfile.Gender.valueOf(gender.toUpperCase()));


        //member
        Member member = new Member();

        member.setUsername(username);
        member.setPassword(bCryptPasswordEncoder.encode(password));
        member.setRole("ROLE_ADMIN");
        member.setRefreshToken(null); // Default or optional
        member.setMemberProfile(memberProfile); //왜지

        userRepository.save(member);
    }

}

