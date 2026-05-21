package com.works.etkinlik_planlama_uygulamasi_backend.Configs;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SessionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        // 1. Tarayıcıların CORS için attığı OPTIONS ön isteklerine her zaman izin ver
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. HERKESE AÇIK ADRESLERİN KONTROLÜ (Giriş ve Kayıt Ol adımları)
        // path içinde "register" veya "login" geçiyorsa filtreye takılmadan geçsin
        if (path.contains("/users/register") || path.contains("/users/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Swagger ve H2-Console Kontrolleri (Geliştirici Araçları)
        if (path.contains("/swagger-ui") || path.contains("/v3/api-docs") || path.contains("/h2-console")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 4. KORUMALI ALANLAR İÇİN SESSION KONTROLÜ
        HttpSession session = request.getSession(false); // Oturum yoksa yeni oturum oluşturma

        if (session != null && session.getAttribute("user") != null) {
            // Oturum var ve kullanıcı verisi mevcut, Controller'a geçişe izin ver
            filterChain.doFilter(request, response);
        } else {
            // Oturum açılmamış! İsteği engelle ve 401 Unauthorized JSON mesajı dön
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"status\": 401, \"message\": \"Bu islem icin giris yapmalisiniz.\"}");
        }
    }
}