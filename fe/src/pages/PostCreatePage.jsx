import {Link, useNavigate} from "react-router-dom";
import Header from "../component/Header";
import Button from "../component/Button";
import Container from "../component/Container";
import PostEditor from "../component/PostEditor";

const PostCreatePage = () => {
  const navigate = useNavigate();

  const onLoginButtonClick = () => {
    navigate('/login');
  }

  return (
    <div>
      <Header child={<Button word="로그인" onClick={onLoginButtonClick}/>}/>
      <Container className="pt-5">
        <nav aria-label="breadcrumb">
          <ol className="breadcrumb">
            <li className="breadcrumb-item"><Link to="/posts">게시판</Link></li>
            <li className="breadcrumb-item active" aria-current="page">생성</li>
          </ol>
        </nav>
        <PostEditor/>
      </Container>
    </div>
  );
}

export default PostCreatePage;