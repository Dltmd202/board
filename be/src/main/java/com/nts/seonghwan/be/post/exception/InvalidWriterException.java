package com.nts.seonghwan.be.post.exception;

import com.nts.seonghwan.be.common.error.exception.BusinessException;
import com.nts.seonghwan.be.common.error.exception.ErrorCode;

public class InvalidWriterException extends BusinessException {

    public InvalidWriterException() {
        super(ErrorCode.INVALID_REPEATED_PASSWORD);
    }
}
