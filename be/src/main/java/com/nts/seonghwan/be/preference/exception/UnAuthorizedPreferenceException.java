package com.nts.seonghwan.be.preference.exception;

import com.nts.seonghwan.be.common.error.exception.BusinessException;
import com.nts.seonghwan.be.common.error.exception.ErrorCode;

public class UnAuthorizedPreferenceException extends BusinessException {
    public UnAuthorizedPreferenceException() {
        super(ErrorCode.UNAUTHORIZED_PREFERENCE);
    }
}
