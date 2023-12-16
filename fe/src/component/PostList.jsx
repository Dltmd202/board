import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import {postApi} from "../api/post";
import {formatDate} from "../common/utils/dateUtils";
import Button from "./Button";
import Form from 'react-bootstrap/Form';
import {Dropdown, DropdownButton, InputGroup} from "react-bootstrap";

const PostList = ({searchCondition, searchQuery}) => {
  const navigate = useNavigate();
  const [totalPage, setTotalPage] = useState(0);
  const [totalPostCount, setTotalPostCount] = useState(0);
  const [totalCommentCount, setTotalCommentCount] = useState(0);

  const [loading, setLoading] = useState(false);
  const [posts, setPosts] = useState([]);
  const [page, setPage] = useState(1);

  const [searchType, setSearchType] = useState('title');
  const [searchString, setSearchString] = useState('');

  const titleStyle = {minWidth: '200px'};

  const getPostList = async () => {
    try {
      const postResponse = await postApi.getPosts(page, searchCondition, searchQuery);
      setPosts(postResponse.data.response.posts.content);
      setTotalPage(postResponse.data.response.posts.totalPages);
      setTotalCommentCount(postResponse.data.response.totalCommentCount);
      setTotalPostCount(postResponse.data.response.posts.totalElements);
    } catch (e) {
      console.log(e);
    }
  }

  useEffect(() => {
    setLoading(true);
    setPage(1);
    getPostList();
    setLoading(false);
  }, [searchCondition, searchQuery]);

  useEffect( () => {
    getPostList();
  }, [page, searchCondition, searchQuery]);

  const onChangeSearchType = (type) => {
    setSearchType(type);
  }

  const handlePostItemClick = (postId) => {
    navigate(`/${postId}`);
  }

  const onClickSearch = (e) => {
    navigate(`/?${searchType}=${searchString}`)
  }

  return (
    <div>
      <div className="d-flex justify-content-between align-items-center">
        <h1 className="mb-2">게시판</h1>
        <div className="d-flex align-items-center">
          <Button word="글쓰기" onClick={() => navigate('/create')}/>
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
          <div className="spinner-border m-5" role="status">
            <span className="visually-hidden">Loading...</span>
          </div>
        )}
        </tbody>
      </table>
      <div className="d-flex justify-content-between">
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
        <div className={"w-50"}>
          <div className="d-flex align-items-center justify-content-end">
            <InputGroup className="me-2 w-75">
              <DropdownButton
                variant="outline-secondary"
                title={searchType}
                id="input-group-dropdown-1"
              >
                <Dropdown.Item onClick={() => onChangeSearchType("title")}>title</Dropdown.Item>
                <Dropdown.Item onClick={() => onChangeSearchType("content")}>content</Dropdown.Item>
                <Dropdown.Item onClick={() => onChangeSearchType("writer")}>writer</Dropdown.Item>
                <Dropdown.Item onClick={() => onChangeSearchType("tag")}>tag</Dropdown.Item>
              </DropdownButton>
              <Form.Control
                onInput={e => setSearchString(e.target.value)}
                value={searchString}
                aria-label="Text input with dropdown button"
              />
            </InputGroup>
            <Button
              onClick={onClickSearch}
              className={"w-auto"}
              word={"검색"}/>
          </div>
          <div className="d-flex justify-content-end">
            <p className="me-2">전체 게시글 개수:</p>
            <p>{totalPostCount}</p>
          </div>
          <div className="d-flex justify-content-end">
            <p className="me-2">전체 댓글 개수:</p>
            <p>{totalCommentCount}</p>
          </div>
        </div>
      </div>
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