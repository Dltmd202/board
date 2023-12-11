import {useNavigate} from "react-router-dom";
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
      <Container>
        <PostList />
      </Container>
    </div>
  );
}

export default PostListPage;