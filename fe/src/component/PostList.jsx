import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import {postApi} from "../api/post";
import {formatDate} from "../common/utils/dateUtils";

const PostList = () => {
  const navigate = useNavigate();
  const [totalPage, setTotalPage] = useState(0);
  const [totalPostCount, setTotalPostCount] = useState(0);
  const [totalCommentCount, setTotalCommentCount] = useState(0);

  const [loading, setLoading] = useState(false);
  const [posts, setPosts] = useState([]);
  const [page, setPage] = useState(1);
  const titleStyle = {minWidth: '200px'};

  useEffect( () => {
    const getPostList = async () => {
      setLoading(true);
      try {
        const postResponse = await postApi.getPosts(page);
        setPosts(postResponse.data.response.posts.content);
        setTotalPage(postResponse.data.response.posts.totalPages);
        setTotalCommentCount(postResponse.data.response.totalCommentCount);
        setTotalPostCount(postResponse.data.response.posts.totalElements);
        setLoading(false);
      } catch (e) {
        console.log(e);
      }
    }
    getPostList();
  }, [page]);



  const handlePostItemClick = (postId) => {
    navigate(`/posts/${postId}`);
  }

  return (
    <div>
      <div className="d-flex justify-content-between">
        <h1 className="mb-2">게시판</h1>
        <div className="w-25">
          <div className="d-flex justify-content-between">
            <p>전체 게시글 개수:</p>
            <p>{totalPostCount}</p>
          </div>
          <div className="d-flex justify-content-between">
            <p>전체 댓글 개수:</p>
            <p>{totalCommentCount}</p>
          </div>
        </div>
      </div>
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
        {!loading ? posts.map((p, i) =>
          <PostListItem post={p} onItemClick={handlePostItemClick} key={i}/>
        ) : (
          <tr>
            <td colSpan="6" className="text-center">Loading...</td>
          </tr>
        )}
        </tbody>
      </table>

      <nav aria-label="Page navigation example">
        <ul className="pagination">
          <li
            className={`page-item ${page <= 1 ? 'disabled' : ''}`}>
            <p className="page-link" onClick={() => setPage(page - 1)}>Previous</p>
          </li>
          <li
            className={`page-item ${page >= totalPage ? 'disabled' : ''}`}>
            <p className="page-link" onClick={() => setPage(page + 1)}>Next</p>
          </li>
        </ul>
      </nav>
    </div>
  )
}

const PostListItem = ({
                        post,
                        onItemClick
}) => {
  const {title, user, createdAt, postId, recent, likeCount, commentCount, viewCount} = post;

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
      <td>
        { recent && <span className="badge bg-secondary me-1">New</span> }
        {truncateString(title, 10)}</td>
      <td>{truncateString(user, 15)}</td>
      <td>{formatDate(createdAt)}</td>
      <td>{commentCount}</td>
      <td>{likeCount}</td>
      <td>{viewCount}</td>
    </tr>
  )
}

export default PostList;