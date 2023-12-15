import {Link, useLocation, useNavigate, useParams} from "react-router-dom";
import {postApi} from "../api/post";
import Header from "../component/Header";
import Button from "../component/Button";
import Container from "../component/Container";
import PostEditor from "../component/PostEditor";
import {useEffect, useState} from "react";

const PostEditPage = () => {
  const { postId } = useParams();
  const [loading, setLoading] = useState(true);
  const [defaultPost, setDefaultPost] = useState({
    title: '',
    content: '',
    tag: [],
  });
  const navigate = useNavigate();

  useEffect(() => {

    const getPost = async () => {
      try {
        setLoading(true);
        const response = await postApi.getPost(postId);
        console.log(response.data.response)
        setDefaultPost({
          content: response.data.response.content,
          title: response.data.response.title,
          tag: response.data.response.tag,
        });
        console.log(defaultPost);
      } catch (e){
        console.log(e);
      }
    }

    setLoading(true);
    getPost();
    setLoading(false);
  }, []);

  const onLoginButtonClick = () => {
    navigate('/login');
  }

  const onPostSubmit = async (post) => {
    try{
      const postResponse = await postApi.editPost(postId, post);
      navigate(`/posts/${postId}`);
    } catch (e) {
      console.log(e);
    }
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
        {
          !loading &&
        <PostEditor
          defaultPost={defaultPost}
          onSubmit={onPostSubmit}/>
        }
      </Container>
    </div>
  )
}

export default PostEditPage;