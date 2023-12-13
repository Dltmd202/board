package com.nts.seonghwan.be.comment.exception;

import com.nts.seonghwan.be.common.error.exception.BusinessException;
import com.nts.seonghwan.be.common.error.exception.ErrorCode;

public class NotFoundCommentException extends BusinessException {
    public NotFoundCommentException() {
        super(ErrorCode.NOT_FOUND_COMMENT);
    }
}
