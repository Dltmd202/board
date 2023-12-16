import Comment from "./Comment";
import {useEffect, useState} from "react";
import {postApi} from "../api/post";
import {formatDate} from "../common/utils/dateUtils";
import {preferenceApi} from "../api/preference";
import Button from "./Button";
import {useNavigate} from "react-router-dom";

const PostDetail = ({postId}) => {
  const [post, setPost] = useState({});
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
  }, [post]);

  useEffect(() => {

    const getPost = async () => {
      try {
        setLoading(true);
        const post = await postApi.getPost(postId);
        setPost(post.data.response);
        setLoading(false);
      } catch (e){
        console.log(e);
      }
    }
    getPost();
  }, []);

  const onLikeButtonClick = async () => {
    let response = await preferenceApi.like(postId);
    setPost((prevPost) => ({
      ...prevPost,
      like: response.data.response,
    }));
  }

  const onUnlikeButtonClick = async () => {
    let response = await preferenceApi.unlike(postId);
    setPost((prevPost) => ({
      ...prevPost,
      unlike: response.data.response,
    }));
  }

  const onClickDeleteButton = async () => {
    await postApi.deletePost(postId);
    navigate('/');
  }

  const onClickEditButton = async () => {
    navigate(`/${postId}/edit`);
  }

  const onTagClick = (tag) => {
    navigate(`/?tag=${tag}`, {replace: true});
  }


  return (
    <div>
      {loading ?
        <div>loading...</div> :
        (
          <div>
            <div>
              <div className="d-flex">
                <h2 className="text-break">{post.title}</h2>
                <hr/>
              </div>
              <div>
                <div className="d-flex justify-content-between">
                  <WriterInformation label="작성자" info={post.user}/>
                  <WriterInformation label="작성일자" info={formatDate(post.createdAt)}/>
                  <WriterInformation label="조회" info={post.viewCount}/>
                </div>
                <hr/>
              </div>
              <div>
                {post.content?.split('\n').map((line, i) => (
                  <>
                    <p key={i}>{line}</p>
                    <br/>
                  </>
                ))}
              </div>
            </div>
            <div className="row">
              <div className="col-lg-4 col-sm-8 d-flex justify-content-start mb-3">
                <button
                  onClick={onLikeButtonClick}
                  type="button"
                  className={`btn ${post.like?.ableToPreference ? 'btn-outline-primary' : 'btn-primary'} me-1`}>
                  <div className="d-flex">
                    <p className="me-1">좋아요</p><p>{post.like?.count || 0}</p>
                  </div>
                </button>
                <button
                  onClick={onUnlikeButtonClick}
                  type="button"
                  className={`btn ${post.unlike?.ableToPreference ? 'btn-outline-danger' : 'btn-danger'}`}>
                  <div className="d-flex">
                    <p className="me-1">싫어요</p><p>{post.unlike?.count || 0}</p>
                  </div>
                </button>
              </div>
              {post.owner && (
                <div className="col-lg-8 col-sm-4 d-flex justify-content-end mb-3">
                  <Button
                    word="삭제하기"
                    type="button"
                    onClick={onClickDeleteButton}
                    className="me-1"/>
                  <Button
                    word="수정하기"
                    type="button"
                    onClick={onClickEditButton}
                    className="me-1"/>
                </div>
              )}
              <hr/>
            </div>
            <div className="mb-3 d-flex">
              {post.tag.map((tag, index) =>
                <button
                  onClick={() => onTagClick(tag)}
                  key={index}
                  className="me-2 btn btn-primary d-flex">
                  <span>#{tag}</span>
                </button>
              )}
            </div>
            <Comment postId={postId}/>
          </div>
        )}
    </div>
  );
}

function WriterInformation({label, info}) {
  return <div className="d-flex">
    <p>{label}:</p>
    <p>{info}</p>
  </div>;
}

export default PostDetail;