package imsh.project.domain.filter;

import imsh.project.domain.provider.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try{
            String token = parseBearerToken(request);
            if(token==null){
                filterChain.doFilter(request,response);
                return;
            }
            String email = jwtProvider.validate(token);
            if(email == null){
                filterChain.doFilter(request,response);
                return;
            }
            AbstractAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(email,null, AuthorityUtils.NO_AUTHORITIES);
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authenticationToken);
            SecurityContextHolder.setContext(securityContext);
        }catch (Exception e){
            e.printStackTrace();
        }
        filterChain.doFilter(request,response);

        //parseBearerToken(request): HTTP 요청 헤더에서 Bearer 토큰을 추출하는 메서드입니다.
        //
        //if(token==null): 토큰이 없는 경우, 다음 필터로 요청을 전달하고 메서드를 종료합니다.
        //
        //String email = jwtProvider.validate(token): 추출한 토큰을 검증하고, 유효한 경우 해당 토큰에 포함된 사용자의 이메일을 가져옵니다.
        //
        //if(email == null): 토큰이 유효하지 않은 경우, 다음 필터로 요청을 전달하고 메서드를 종료합니다.
        //
        //AbstractAuthenticationToken authenticationToken = ...: 검증이 성공하면, 사용자의 인증 토큰을 생성합니다. 여기서는 UsernamePasswordAuthenticationToken을 사용하고, 권한은 없음(AuthorityUtils.NO_AUTHORITIES)으로 설정합니다.
        //
        //authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)): 추가적인 인증 세부 정보를 설정합니다.
        //
        //SecurityContext securityContext = SecurityContextHolder.createEmptyContext(): 빈 SecurityContext를 생성합니다.
        //
        //securityContext.setAuthentication(authenticationToken): 보안 컨텍스트에 앞에서 생성한 인증 토큰을 설정합니다.
        //
        //SecurityContextHolder.setContext(securityContext): 현재 스레드의 SecurityContextHolder에 보안 컨텍스트를 설정합니다.
        //
        //filterChain.doFilter(request, response): 나머지 필터 체인을 계속 진행합니다.
    }

    private String parseBearerToken(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        boolean hasAuthorization = StringUtils.hasText(authorization);
        if(!hasAuthorization) return null;

        boolean isBearer = authorization.startsWith("Bearer "); // "Bearer " 로 시작하느냐?
        if(!isBearer) return null;
        String token = authorization.substring(7);
        return token;
    }
}
