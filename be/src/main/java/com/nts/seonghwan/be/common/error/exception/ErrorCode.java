package com.nts.seonghwan.be.common.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // 인증 관련 에러
    INVALID_REPEATED_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    DUPLICATED_EMAIL(HttpStatus.BAD_REQUEST, "이미 가입된 이메일 입니다."),
    INVALID_AUTHENTICATION(HttpStatus.UNAUTHORIZED, "로그인에 실패했습니다."),
    NO_AUTHENTICATION(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다."),

     // 게시글 관련 에러
    INVALID_WRITER(HttpStatus.BAD_REQUEST, "작성자 정보를 확인해주세요."),
    NOT_FOUND_POST(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."),

    // 댓글 관련 에러
    INVALID_COMMENTER(HttpStatus.BAD_REQUEST, "댓글 작성자 정보를 확인해주세요."),

    // 서버 내부 에러
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 오류가 발생했습니다.");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
