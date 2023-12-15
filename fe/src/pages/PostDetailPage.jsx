import {Link, useParams} from "react-router-dom";
import Header from "../component/Header";
import Container from "../component/Container";
import PostDetail from "../component/PostDetail";

const PostDetailPage = () => {
  const { postId } = useParams();

  return(
    <div>
      <Header />
      <Container className="pt-5">
        <nav aria-label="breadcrumb">
          <ol className="breadcrumb">
            <li className="breadcrumb-item"><Link to="/">게시판</Link></li>
            <li className="breadcrumb-item active" aria-current="page">{postId}</li>
          </ol>
        </nav>
        <PostDetail postId={postId}/>
      </Container>
    </div>
  );
}

export default PostDetailPage;