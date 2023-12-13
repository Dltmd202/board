package com.nts.seonghwan.be.post.exception;

import com.nts.seonghwan.be.common.error.exception.BusinessException;
import com.nts.seonghwan.be.common.error.exception.ErrorCode;

public class NotFoundPostException extends BusinessException {

        public NotFoundPostException() {
            super(ErrorCode.NOT_FOUND_POST);
        }
}
