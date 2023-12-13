package com.nts.seonghwan.be.user.exception;

import com.nts.seonghwan.be.common.error.exception.BusinessException;
import com.nts.seonghwan.be.common.error.exception.ErrorCode;

public class ForbiddenUserAccessException extends BusinessException {
    public ForbiddenUserAccessException() {
        super(ErrorCode.FORBIDDEN_USER_ACCESS);
    }
}
