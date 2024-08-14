package arom_semo.server.login.jwt;

import arom_semo.server.login.dto.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collection;
import java.util.Iterator;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public LoginFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public LoginFilter(AuthenticationManager authenticationManager, AuthenticationManager authenticationManager1, JwtUtil jwtUtil) {
        super(authenticationManager);
        this.authenticationManager = authenticationManager1;
        this.jwtUtil = jwtUtil;
    }

    //Authentication: 로그인과 같이 사용자 또는 프로세스의 신원을 확인하는 프로세스
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        //클라이언트 요청에서 username, password 추출
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        //스프링 시큐리티에서 username과 password를 검증하기 위해서는 token에 담아야 함
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);

        //token에 담은 검증을 위한 AuthenticationManager로 전달(dto바구니=UsernamePasswordAuthenticationToken에 담아서)
        return authenticationManager.authenticate(authToken);
    }

    //검증
    //로그인 성공시 실행하는 메소드 (여기서 JWT를 발급하면 됨)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        CustomUserDetails customUserDetails =(CustomUserDetails) authentication.getPrincipal();

        String username = customUserDetails.getUsername();

        //username과 role값 뽑아내기
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        //jwtUtil에 token을 만들어달라고 전달
        String role = auth.getAuthority();

        String token = jwtUtil.createJwt(username, role, 60*60*10L);

        //jwt를 response에 담에서 응답
        response.addHeader("Authorization", "Bearer " + token);
    }

    //로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        response.setStatus(401);
    }
}

