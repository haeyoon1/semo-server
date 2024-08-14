package arom_semo.server.login.service;

import arom_semo.server.login.dto.CustomUserDetails;
import arom_semo.server.login.entity.Member;
import arom_semo.server.login.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository userRepository;

    public CustomUserDetailsService(MemberRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member userData = userRepository.findByUsername(username);

//        if (userData != null){
//            return new CustomUserDetails(userData);
//        }
//
//        return null;
        if (userData == null) {
            // 사용자 정보를 찾지 못할 경우 UsernameNotFoundException을 던집니다.
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new CustomUserDetails(userData);
    }
}

