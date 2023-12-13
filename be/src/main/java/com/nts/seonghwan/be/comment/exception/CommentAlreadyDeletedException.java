package com.nts.seonghwan.be.comment.exception;

import com.nts.seonghwan.be.common.error.exception.BusinessException;
import com.nts.seonghwan.be.common.error.exception.ErrorCode;

public class CommentAlreadyDeletedException extends BusinessException {
    public CommentAlreadyDeletedException() {
        super(ErrorCode.ALREADY_DELETED_COMMENT);
    }
}
