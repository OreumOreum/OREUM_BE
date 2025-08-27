package com.zzarit.oreum.logging.config;

import com.zzarit.oreum.logging.domain.HttpStatusCategory;
import com.zzarit.oreum.logging.domain.MemberIdentifier;
import com.zzarit.oreum.logging.dto.LogContext;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Component
public class LoggingFilter implements Filter {

    private static final int MAX_LOGGABLE_BODY_LENGTH = 2048;
    private static final String MASKED_INFORMATION = "[MASKED_INFORMATION]";

    // 로깅 제외할 경로들 (Swagger 관련 + 기타)
    private static final Set<String> EXCLUDED_PATHS = Set.of(
            "/v3/api-docs",
            "/swagger-ui",
            "/swagger-resources",
            "/webjars",
            "/favicon.ico",
            "/actuator/health"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        // Swagger 관련 경로는 로깅하지 않고 바로 통과
        if (shouldExcludeFromLogging(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(httpRequest);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(
                (HttpServletResponse) response);
        String identifier = UUID.randomUUID().toString();
        MDC.put("trackId", identifier);
        wrappedRequest.setAttribute("startTime", System.currentTimeMillis());
        wrappedRequest.setAttribute("loggingMasked", false);

        chain.doFilter(wrappedRequest, wrappedResponse);

        long startTime = Long.parseLong(request.getAttribute("startTime").toString());
        long endTime = System.currentTimeMillis();
        String latency = endTime - startTime + "ms";
        String uri = parseUri(wrappedRequest.getRequestURI(), wrappedRequest.getQueryString());

        String requestBody = parseRequestBody(wrappedRequest);
        MemberIdentifier memberIdentifier = new MemberIdentifier(wrappedRequest.getHeader(HttpHeaders.AUTHORIZATION));
        String httpMethod = wrappedRequest.getMethod();
        String statusCode = String.valueOf(wrappedResponse.getStatus());
        HttpStatusCategory statusCategory = HttpStatusCategory.fromStatusCode(wrappedResponse.getStatus());
        String responseBody = parseResponseBody(wrappedResponse);
        String stackTrace = (String) request.getAttribute("stackTrace");

        statusCategory.log(log,
                new LogContext(identifier, memberIdentifier, latency, httpMethod, uri, requestBody, statusCode,
                        responseBody, stackTrace));
        wrappedResponse.copyBodyToResponse();
        MDC.clear();
    }

    /**
     * 로깅에서 제외할 경로인지 확인
     */
    private boolean shouldExcludeFromLogging(String requestURI) {
        return EXCLUDED_PATHS.stream().anyMatch(requestURI::startsWith);
    }

    private String parseResponseBody(ContentCachingResponseWrapper response) {
        try {
            String bodyString = new String(response.getContentAsByteArray());
            if (bodyString.length() > MAX_LOGGABLE_BODY_LENGTH) {
                return bodyString.substring(0, MAX_LOGGABLE_BODY_LENGTH) + " ... (truncated)";
            }
            return bodyString;
        } catch (Exception e) {
            return "";
        }
    }

    private String parseRequestBody(ContentCachingRequestWrapper request) {
        if (isMultipart(request)) {
            return "";
        }
        boolean isMasked = (boolean) request.getAttribute("loggingMasked");
        if (isMasked) {
            return MASKED_INFORMATION;
        }
        return request.getContentAsString();
    }

    private boolean isMultipart(HttpServletRequest request) {
        String contentType = request.getContentType();
        return contentType != null && contentType.toLowerCase().startsWith("multipart/");
    }

    private String parseUri(String requestUri, String queryString) {
        if (queryString == null) {
            return requestUri;
        }
        return requestUri + "?" + queryString;
    }
}