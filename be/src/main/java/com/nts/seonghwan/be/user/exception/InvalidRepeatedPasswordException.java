package com.nts.seonghwan.be.user.exception;

import com.nts.seonghwan.be.common.error.exception.BusinessException;
import com.nts.seonghwan.be.common.error.exception.ErrorCode;

public class InvalidRepeatedPasswordException extends BusinessException {
    public InvalidRepeatedPasswordException() {
        super(ErrorCode.INVALID_REPEATED_PASSWORD);
    }
}
