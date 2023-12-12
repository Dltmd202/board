import Comment from "./Comment";
import {useEffect, useState} from "react";
import {postApi} from "../api/post";
import {formatDate} from "../common/utils/dateUtils";

const PostDetail = ({postId}) => {
  const [post, setPost] = useState({});
  const [loading, setLoading] = useState(true);

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


  return (
    <div>
      {loading ?
        <div>loading...</div> :
        (
          <div>
            <div>
              <div>
                <h1 className="mb-4">{post.title}</h1>
                <hr/>
              </div>
              <div>
                <div className="d-flex justify-content-between">
                  <WriterInformation label="작성자" info={post.user}/>
                  <WriterInformation label="작성일자" info={formatDate(post.createdAt)}/>
                  <WriterInformation label="조회" info={"1,000"}/>
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
                <button type="button" className="btn btn-outline-primary me-1">
                  <div className="d-flex">
                    <p className="me-1">좋아요</p><p>100000</p>
                  </div>
                </button>
                <button type="button" className="btn btn-danger">
                  <div className="d-flex">
                    <p className="me-1">싫어요</p><p>100000</p>
                  </div>
                </button>
              </div>
              <hr/>
            </div>
            <Comment/>
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