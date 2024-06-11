package com.vof.user.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.vof.user.security.jwt.JwtService.obtenerSubject;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

//-->   Extiendo de esta forma para asegurarme que el filtro se ejecute unicamente 1 ves por cada solicitud HTTP soporta multiplefiltros   <--

@Component
@RequiredArgsConstructor

public class JwtAuthFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //-->   Tengo que obtener el token de la cabecera de la solicitud(request)   <--
        final String token=obteinTockenFromRequest(request);

        if(token==null){
            //-->   No tengo el token en el request   <--
            filterChain.doFilter(request,response);
            return;
        }

        String username=  obtenerSubject(token);
        request.setAttribute("username", username);
        filterChain.doFilter(request,response);
    }

    private String obteinTockenFromRequest(HttpServletRequest request) {
        final String JTK_HEADER=request.getHeader(AUTHORIZATION);   //-->   TODO VERIFICAR  <--
        //-->   Verifico que exista el token y que empiece con Bearer caso afirmativo lo retorno caso contrario null    <--
        return (StringUtils.hasText(JTK_HEADER)&&JTK_HEADER.startsWith("Bearer "))?JTK_HEADER.substring(7):null;
    }
}
