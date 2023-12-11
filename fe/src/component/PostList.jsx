import {useNavigate} from "react-router-dom";

const PostList = () => {
  const navigate = useNavigate();
  const titleStyle = {minWidth: '200px'};
  const defaultPost = {
    title: '제목1',
    user: 'abc123@abc.com',
    createdAt: '2023.12.12',
    postId: 'ea642987-0e39-4cfc-9d4f-f8356b0e8658',
  }

  const handlePostItemClick = (postId) => {
    navigate(`/post/${postId}`);
  }

  return (
    <div>
      <h1 className="mt-5 mb-2">게시판</h1>
      <table className="table table-hover">
        <thead className="table-light">
        <tr>
          <th scope="col" style={titleStyle}>제목</th>
          <th scope="col">작성자</th>
          <th scope="col">작성일시</th>
          <th scope="col">댓글</th>
          <th scope="col">좋아요</th>
          <th scope="col">조회수</th>
        </tr>
        </thead>
        <tbody>
        <PostListItem post={defaultPost} onItemClick={handlePostItemClick}/>
        <PostListItem post={defaultPost} onItemClick={handlePostItemClick}/>
        <PostListItem post={defaultPost} onItemClick={handlePostItemClick}/>
        <PostListItem post={defaultPost} onItemClick={handlePostItemClick}/>
        <PostListItem post={defaultPost} onItemClick={handlePostItemClick}/>
        <PostListItem post={defaultPost} onItemClick={handlePostItemClick}/>
        <PostListItem post={defaultPost} onItemClick={handlePostItemClick}/>
        <PostListItem post={defaultPost} onItemClick={handlePostItemClick}/>
        <PostListItem post={defaultPost} onItemClick={handlePostItemClick}/>
        <PostListItem post={defaultPost} onItemClick={handlePostItemClick}/>
        <PostListItem post={defaultPost} onItemClick={handlePostItemClick}/>
        </tbody>
      </table>

      <nav aria-label="Page navigation example">
        <ul className="pagination">
          <li className="page-item"><a className="page-link" href="#">Previous</a></li>
          <li className="page-item"><a className="page-link" href="#">1</a></li>
          <li className="page-item"><a className="page-link" href="#">2</a></li>
          <li className="page-item"><a className="page-link" href="#">3</a></li>
          <li className="page-item"><a className="page-link" href="#">4</a></li>
          <li className="page-item"><a className="page-link" href="#">5</a></li>
          <li className="page-item"><a className="page-link" href="#">Next</a></li>
        </ul>
      </nav>
    </div>
  )
}

const PostListItem = ({
                        post,
                        onItemClick
}) => {
  const {title, user, createdAt, postId} = post;

  const onClick = () => {
    onItemClick(postId);
  }

  const truncateString = (str, maxLength) => {
    if (str.length > maxLength) {
      return str.substring(0, maxLength) + '...';
    } else {
      return str;
    }
  }

  return (
    <tr onClick={onClick}>
      <td>{truncateString(title, 20)}</td>
      <td>{truncateString(user, 15)}</td>
      <td>{createdAt}</td>
      <td>3</td>
      <td>3</td>
      <td>1000</td>
    </tr>
  )
}

export default PostList;