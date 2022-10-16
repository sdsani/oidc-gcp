package com.shahkaar.oidcgcp.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuth2Controller {
    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }
    
    @GetMapping("/oidc-user")
    public OidcUser getOidcUserPrincipal(
      @AuthenticationPrincipal OidcUser principal) {
        return principal;
    }
    
    @GetMapping("/authZ")
    public Collection<? extends GrantedAuthority> getOidcUserAuthZ(
      @AuthenticationPrincipal OidcUser principal) {
    	return principal.getAuthorities();
    }
    
    @GetMapping("/authN")
    public OidcUserInfo getOidcUserAuthN(
      @AuthenticationPrincipal OidcUser principal) {
    	return principal.getUserInfo();
    }
    
    @GetMapping("/token")
    public OidcIdToken getToken(
      @AuthenticationPrincipal OidcUser principal) {
    	return principal.getIdToken();
    }
    
    @GetMapping("/claims")
    public Map<String, Object> getClaims(
      @AuthenticationPrincipal OidcUser principal) {
    	return principal.getClaims();
    }
}