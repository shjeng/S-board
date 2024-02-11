package imsh.project.domain.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtProvider {
    @Value("${jwt.secretKey}")
    private String secretKey;


    public String create(String email){
        // Instant.now() : 현재 시간 , .plus(1,ChronoUnit.HOURS) : 1시간 추가
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS)); // 유효시간
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        String jwt = Jwts.builder()
                .signWith(key,SignatureAlgorithm.HS256)
                .setSubject(email).setIssuedAt(new Date()).setExpiration(expiredDate)
                .compact();
        // Jwts.builder(): JWT를 생성하기 위한 빌더 객체를 생성
        // signWith(SignatureAlgorithm.ES256, secretKey): JWT에 서명을 추가
        // ES256은 ECDSA (Elliptic Curve Digital Signature Algorithm)의 SHA-256 구현을 사용하는 서명 알고리즘
        // setSubject(email): JWT의 주제(sub)를 설정 // setIssuedAt(new Date()): 토큰이 발급된 시간
        // setExpiration : 유효시간 // compact(): 모든 설정이 완료된 JWT를 생성하고, 문자열로 변환하여 반환
        return jwt;
    }

    public String validate(String jwt){ // JWT 토큰의 유효성 검사
        Claims claims = null;
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        try{
            claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt).getBody();
            // Jwts.parser(): Jwts 클래스의 정적 메서드로, JWT 토큰을 파싱
            // setSigningKey(secretKey): 토큰을 검증하기 위해 사용되는 비밀 키를 설정
            // parseClaimsJws(jwt): 주어진 JWT 토큰을 파싱하고, 토큰이 유효한 서명을 가지고 있는지 확인
            // getBody(): 토큰의 본문을 가져옴. 이 부분에는 클레임이 들어 있음

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return claims.getSubject();
        // claims.getSubject(): 클레임에서 서브젝트를 가져와 반환. 
        // 서브젝트는 일반적으로 토큰이 속한 사용자를 식별하는 데 사용됨.
    }
}
