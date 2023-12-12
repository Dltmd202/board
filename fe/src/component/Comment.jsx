import Button from "./Button";
import {useEffect, useState} from "react";
import {commentApi} from "../api/comment";
import {formatDate} from "../common/utils/dateUtils";

const Comment = ({postId}) => {
  const [totalCommentCount, setTotalCommentCount] = useState(0);
  const [page, setPage] = useState(1);
  const [comments, setComments] = useState([]);
  const [hasNextComment, setHasNextComment] = useState(true);

  useEffect(() => {
    const getComments = async () => {
      try {
        const commentsResponse = await commentApi.getComments(postId, page);
        setTotalCommentCount(commentsResponse.data.response.totalElements);
        setComments([...comments, ...(commentsResponse.data.response.content)]);
        setHasNextComment(!commentsResponse.data.response.last);
      } catch (e) {
        console.log(e);
      }
    }

    getComments();
  }, [page]);

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
            <CommentDetail key={i} comment={comment}/>
            <hr/>
          </>
        ))}
        <div className="d-flex justify-content-end">
            <Button
              disabled={!hasNextComment}
              word="더보기"
              onClick={() => setPage(page + 1)}/>
        </div>
      </div>
      <CommentForm />
    </div>
  )
}

const CommentDetail = ({comment}) => {
  const {user, content, createdAt} = comment;
  return (
    <div className="d-flex">
      <div className="col-sm-10 mb-3 pe-2">
        <p className="mb-1">{user}</p>
        <p className="mb-1">{content}</p>
        <p className="text-body-secondary">{formatDate(createdAt)}</p>
      </div>
      <div className="col-sm-2 d-flex justify-content-end lh-1 align-items-start">
        <Button
          type="button"
          word="삭제하기"
          className="me-1" />
      </div>
    </div>
  )
}

const CommentForm = () => {
  return <div className="mb-5">
    <div className="d-flex justify-content-between mb-2">
      <label className="text-al text-body-secondary mb-2">댓글 작성하기</label>
      <Button word="제출하기" />
    </div>
    <textarea className="form-control" id="comment" rows="3"></textarea>
  </div>;
}

export default Comment;