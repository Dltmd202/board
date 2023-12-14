package com.nts.seonghwan.be.user.exception;

import com.nts.seonghwan.be.common.error.exception.BusinessException;
import com.nts.seonghwan.be.common.error.exception.ErrorCode;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException() {
        super(ErrorCode.NOT_FOUND_USER);
    }
}
