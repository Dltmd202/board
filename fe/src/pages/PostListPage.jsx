import {Link} from "react-router-dom";
import Header from "../component/Header";
import Container from "../component/Container";
import PostList from "../component/PostList";

const PostListPage = () => {

  return(
    <div>
      <Header />
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