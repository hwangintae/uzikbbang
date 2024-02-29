package com.khpl.uzikbbang.api.controller.auth;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khpl.uzikbbang.api.controller.auth.request.RefreshRequest;
import com.khpl.uzikbbang.api.controller.auth.request.SignIn;
import com.khpl.uzikbbang.api.controller.auth.request.SignOut;
import com.khpl.uzikbbang.api.controller.auth.request.SignUp;
import com.khpl.uzikbbang.api.controller.auth.response.SessionResponse;
import com.khpl.uzikbbang.api.service.auth.AuthService;
import com.khpl.uzikbbang.config.TokenParser;
import com.khpl.uzikbbang.config.data.UserSession;
import com.khpl.uzikbbang.domain.user.UzikUser;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final TokenParser tokenParser;


    @PostMapping(value = "/signup")
    public void signUp(@RequestBody SignUp signUp) {
        authService.signUp(signUp);
    }

    // CHECKLIST
    // [v] refresh token 만들기
    // [v] refresh token은 cookie에 저장
    // [v] access token 갱신 주기 짧게 하기
    // [v] access token은 json으로 리턴한다.
    @PostMapping(value = "/signin")
    public ResponseEntity<SessionResponse> singIn(@RequestBody SignIn signIn) {
        UzikUser user = authService.signIn(signIn);

        String accessToken = authService.createAccessToken(user.getId());
        String refreshToken = authService.createRefreshToken(user.getId());

        ResponseCookie cookie = authService.createCookie(refreshToken, 7);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new SessionResponse(accessToken));
    }

    @PostMapping(value = "/signout")
    public ResponseEntity<SessionResponse> singOut(@RequestBody SignOut signOut) {
        authService.signOut(signOut);

        // cookie 만료 시키기
        ResponseCookie cookie = authService.createCookie("", 0);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new SessionResponse(""));
    }

    @GetMapping("/foo")
    public Long foo(UserSession userSession) {
        return userSession.getId();
    }

    @PostMapping("/refresh")
    public ResponseEntity<SessionResponse> refresh(HttpServletRequest httpServletRequest) {
        RefreshRequest request = RefreshRequest.builder()
                .httpServletRequest(httpServletRequest)
                .tokenParser(tokenParser)
                .build();

        request.valid();

        Long id = request.getUserId();
        String accessToken = authService.createAccessToken(id);

        return ResponseEntity.ok()
                .body(new SessionResponse(accessToken));
    }

}
