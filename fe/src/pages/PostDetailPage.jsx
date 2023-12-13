import {Link, useNavigate, useParams} from "react-router-dom";
import Header from "../component/Header";
import Button from "../component/Button";
import Container from "../component/Container";
import PostDetail from "../component/PostDetail";

const PostDetailPage = () => {
  const { postId } = useParams();
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
            <li className="breadcrumb-item active" aria-current="page">{postId}</li>
          </ol>
        </nav>
        <PostDetail postId={postId}/>
      </Container>
    </div>
  );
}

export default PostDetailPage;