import Button from "./Button";
import {useEffect, useState} from "react";
import {commentApi} from "../api/comment";
import {formatDate} from "../common/utils/dateUtils";

const Comment = ({postId}) => {
  const [totalCommentCount, setTotalCommentCount] = useState(0);
  const [page, setPage] = useState(1);
  const [comments, setComments] = useState([]);
  const [hasNextComment, setHasNextComment] = useState(true);
  const [newComment, setNewComment] = useState('');
  const [availToSubmit, setAvailToSubmit] = useState(false);

  useEffect(() => {
    setAvailToSubmit(0 < newComment.length && newComment.length < 255);
  }, [newComment]);

  useEffect(() => {
    getComments(page);
  }, []);

  const getComments = async (page, slice=0) => {
    try {
      const commentsResponse = await commentApi.getComments(postId, page);
      const newComments = commentsResponse.data.response.content;

      setTotalCommentCount(commentsResponse.data.response.totalElements);
      setComments([...comments.slice(0, comments.length - slice), ...newComments]);
      setHasNextComment(!commentsResponse.data.response.last);
    } catch (e) {
      console.log(e);
    }
  }

  const onMoreCommentButtonClick = async () => {
    const excessCount = comments.length % 5;
    if (comments.length === 0 || excessCount > 0) {
      await getComments(page, excessCount);
    } else {
      await getComments(page + 1);
      setPage(page + 1);
    }
  }

  const onSubmitCreateComment = async () => {
    try {
      await commentApi.createComment(postId, newComment);
      setHasNextComment(true);
      setTotalCommentCount(totalCommentCount + 1);
      setNewComment('');
    } catch (e) {
      console.log(e);
    }
  }

  const onDeleteComment = async (commentId, commentIdx) => {
    try {
      await commentApi.deleteComment(postId, commentId);
      const comment = comments[commentIdx];
      comment.content = '삭제된 댓글입니다.';
      comment.ableToDelete = false;
      comment.deleted = true;
      setComments([...comments.slice(0, commentIdx), comment, ...comments.slice(commentIdx + 1)]);
    } catch (e) {
      console.log(e);
    }
  }

  return (
    <div>
      <div className="d-flex mb-4 text-body-secondary">
        <p className="me-1">댓글</p>
        <p>{totalCommentCount}</p>
        <p>개</p>
      </div>
      <div className="mb-5">
        { comments.map((comment, i) => (
          <>
            <CommentDetail
              key={i}
              commentIdx={i}
              onDelete={onDeleteComment}
              comment={comment}/>
            <hr/>
          </>
        ))}
        <div className="d-flex justify-content-end">
            <Button
              disabled={!hasNextComment}
              word="더보기"
              onClick={onMoreCommentButtonClick}/>
        </div>
      </div>
      <CommentForm
        onInput={setNewComment}
        onSubmit={onSubmitCreateComment}
        availToSubmit={availToSubmit}
        comment={newComment}/>
    </div>
  )
}

const CommentDetail = ({comment, onDelete, commentIdx}) => {
  const {commentId, user, content, createdAt, ableToDelete, deleted} = comment;
  return (
    <div className="d-flex">
      <div className="col-sm-10 mb-3 pe-2">
        <p className="mb-1">{user}</p>
        <p className={`mb-1 ${deleted ? 'text-body-tertiary' : ''}`}>{content}</p>
        <p className="text-body-secondary">{formatDate(createdAt)}</p>
      </div>
      <div className="col-sm-2 d-flex justify-content-end lh-1 align-items-start">
        {ableToDelete &&
          <Button
            onClick={() => onDelete(commentId, commentIdx)}
            type="button"
            word="삭제하기"
            className="me-1" />
        }
      </div>
    </div>
  )
}

const CommentForm = ({availToSubmit, comment, onSubmit, onInput}) => {

  return <div className="mb-5">
    <div className="d-flex justify-content-between mb-2">
      <label className="text-al text-body-secondary mb-2">댓글 작성하기</label>
      <Button word="제출하기" disabled={!availToSubmit} onClick={onSubmit}/>
    </div>
    <textarea
      onInput={(e) => onInput(e.target.value)}
      className="form-control"
      id="comment"
      rows="3"
      value={comment}
    />
  </div>;
}

export default Comment;