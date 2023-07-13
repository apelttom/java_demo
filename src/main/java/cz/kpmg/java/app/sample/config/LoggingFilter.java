package cz.kpmg.java.app.sample.config;

import com.google.common.collect.Maps;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class LoggingFilter extends OncePerRequestFilter implements HandlerInterceptor {

    private static final String REQUEST_ID = "requestId";
    private static final String ACTUATOR = "actuator";
    private static final String SWAGGER = "swagger-ui";
    private static final String API_DOCS = "api-docs";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MDC.put(REQUEST_ID, UUID.randomUUID().toString());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        response.addHeader(REQUEST_ID, MDC.get(REQUEST_ID));
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        long startTime = System.currentTimeMillis();
        filterChain.doFilter(requestWrapper, responseWrapper);
        long timeTaken = System.currentTimeMillis() - startTime;
        if (!StringUtils.containsAny(request.getRequestURI(), ACTUATOR, SWAGGER, API_DOCS)) {
            log.debug("========================================");
            log.debug("REQUEST & RESPONSE INFO:");
            log.debug("========================================");
            logRequest(request, requestWrapper);
            logResponse(response, responseWrapper);
            log.debug("Process time: {} ms", timeTaken);
            log.debug("========================================");
        }
        responseWrapper.copyBodyToResponse();
        MDC.remove(REQUEST_ID);
    }

    private void logRequest(HttpServletRequest request, ContentCachingRequestWrapper requestWrapper) {
        Map<String, String> headers = Maps.newHashMap();
        IteratorUtils.toList(request.getHeaderNames().asIterator())
                .forEach(header -> {
                    headers.put(header, request.getHeader(header));
                });
        String requestBody = IOUtils.toString(requestWrapper.getContentAsByteArray(), request.getCharacterEncoding());
        log.debug("Request headers: {}", headers);
        if (StringUtils.isEmpty(requestBody)) {
            log.debug("Request [{} {}]", request.getMethod(), request.getRequestURI());
        } else {
            log.debug("Request [{} {}] payload: {}", request.getMethod(), request.getRequestURI(), StringUtils.normalizeSpace(requestBody));
        }
    }

    private void logResponse(HttpServletResponse response, ContentCachingResponseWrapper responseWrapper) {
        Map<String, String> headers = Maps.newHashMap();
        response.getHeaderNames().stream().toList()
                .forEach(header -> {
                    headers.put(header, response.getHeader(header));
                });
        String responseBody = IOUtils.toString(responseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8.name());
        log.debug("Response headers: {}", headers);
        log.debug("Response [HttpStatus={}]: {}", response.getStatus(), StringUtils.normalizeSpace(responseBody));
    }

}