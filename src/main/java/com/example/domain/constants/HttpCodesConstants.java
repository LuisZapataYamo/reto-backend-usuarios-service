package com.example.domain.constants;

import lombok.Getter;

@Getter
public enum HttpCodesConstants {
    // 1xx: Informational
    CONTINUE(100),
    SWITCHING_PROTOCOLS(101),
    PROCESSING(102),

    // 2xx: Success
    OK(200),
    CREATED(201),
    ACCEPTED(202),
    NON_AUTHORITATIVE_INFORMATION(203),
    NO_CONTENT(204),
    RESET_CONTENT(205),
    PARTIAL_CONTENT(206),

    // 3xx: Redirection
    MULTIPLE_CHOICES(300),
    MOVED_PERMANENTLY(301),
    FOUND(302),
    SEE_OTHER(303),
    NOT_MODIFIED(304),
    USE_PROXY(305),
    TEMPORARY_REDIRECT(307),

    // 4xx: Client Error
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    PAYMENT_REQUIRED(402),
    FORBIDDEN(403),
    NOT_FOUND(404),

    // 5xx: Server Error
    INTERNAL_SERVER_ERROR(500);

    private final int code;

    HttpCodesConstants(int code) {
        this.code = code;
    }

}
