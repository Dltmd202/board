import {Link, useNavigate} from "react-router-dom";
import Header from "../component/Header";
import Button from "../component/Button";
import Container from "../component/Container";
import PostList from "../component/PostList";

const PostListPage = () => {
  const navigate = useNavigate();

  const onLoginButtonClick = () => {
    navigate('/login');
  }

  return(
    <div>
      <Header child={<Button word="로그인" onClick={onLoginButtonClick}/> }/>
      <Container className="pt-5">
        <nav aria-label="breadcrumb">
          <ol className="breadcrumb">
            <li className="breadcrumb-item"><Link to="/posts">게시판</Link></li>
          </ol>
        </nav>
        <PostList/>
      </Container>
    </div>
  );
}

export default PostListPage;