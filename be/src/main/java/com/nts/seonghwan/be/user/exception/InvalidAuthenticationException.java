package com.nts.seonghwan.be.user.exception;

import com.nts.seonghwan.be.common.error.exception.BusinessException;
import com.nts.seonghwan.be.common.error.exception.ErrorCode;

public class InvalidAuthenticationException extends BusinessException {
    public InvalidAuthenticationException() {
        super(ErrorCode.INVALID_AUTHENTICATION);
    }
}
