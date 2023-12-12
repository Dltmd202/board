import Button from "./Button";

const Comment = () => {
  const comments = [
    {
      user: "abc123@gmail.com",
      content: "게시글에 댓글을 쓰면 이런 형식으로 보여집니다.",
      date: "2023.11.23.23:15"
    },
    {
      user: "abc123@gmail.com",
      content: "게시글에 댓글을 쓰면 이런 형식으로 보여집니다.",
      date: "2023.11.23.23:15"
    },
    {
      user: "abc123@gmail.com",
      content: "게시글에 댓글을 쓰면 이런 형식으로 보여집니다.",
      date: "2023.11.23.23:15"
    },
    {
      user: "abc123@gmail.com",
      content: "게시글에 댓글을 쓰면 이런 형식으로 보여집니다.",
      date: "2023.11.23.23:15"
    },
    {
      user: "abc123@gmail.com",
      content: "게시글에 댓글을 쓰면 이런 형식으로 보여집니다.",
      date: "2023.11.23.23:15"
    },
  ]
  return (
    <div>
      <div className="d-flex mb-4 text-body-secondary">
        <p className="me-1">댓글</p>
        <p>1</p>
        <p>개</p>
      </div>
      <div className="mb-5">
        <CommentDetail comment={comments[0]}/>
        <hr/>
        <CommentDetail comment={comments[1]}/>
        <hr/>
        <CommentDetail comment={comments[2]}/>
        <hr/>
        <CommentDetail comment={comments[3]}/>
        <hr/>
        <CommentDetail comment={comments[4]}/>
        <hr/>
        <div className="d-flex justify-content-end">
          <Button word="더보기" />
        </div>
      </div>
      <CommentForm />
    </div>
  )
}

const CommentDetail = ({comment}) => {
  const {user, content, date} = comment;
  return (
    <div className="d-flex">
      <div className="col-sm-10 mb-3 pe-2">
        <p className="mb-1">{user}</p>
        <p className="mb-1">{content}</p>
        <p className="text-body-secondary">{date}</p>
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