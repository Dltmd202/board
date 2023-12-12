import {useNavigate, useParams} from "react-router-dom";
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
      <Container>
        <PostDetail postId={postId}/>
      </Container>
    </div>
  );
}

export default PostDetailPage;