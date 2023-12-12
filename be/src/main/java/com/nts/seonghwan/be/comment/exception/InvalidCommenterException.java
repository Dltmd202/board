package com.nts.seonghwan.be.comment.exception;

import com.nts.seonghwan.be.common.error.exception.BusinessException;
import com.nts.seonghwan.be.common.error.exception.ErrorCode;

public class InvalidCommenterException extends BusinessException {
    public InvalidCommenterException() {
        super(ErrorCode.INVALID_REPEATED_PASSWORD);
    }
}
