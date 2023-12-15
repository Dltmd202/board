import Input from "./Input";
import Button from "./Button";
import {useEffect, useState} from "react";

const PostEditor = ({defaultPost, onSubmit}) => {
  const [post, setPost] = useState({
    title: '',
    content: '',
    tag: [],
  })
  const [tagString, setTagString] = useState('');
  const [availableSubmit, setAvailableSubmit] = useState(false);

  useEffect(() => {
    setPost(defaultPost);
  }, [defaultPost]);

  useEffect(() => {
    setAvailableSubmit(0 < post.title.length && post.content.length < 255);
  }, [post.title])

  useEffect(() => {
    if(tagString.length >= 20) setTagString(tagString.slice(0, 20));
    if(/^\s/.test(tagString)) setTagString('');
    else if(/\s$/.test(tagString)) {
      if(!post.tag.find(tag => tag === tagString)){
        const updatedHashtags = [...post.tag, tagString];
        setPost({
          ...post,
          tag: updatedHashtags
        })
      }
      setTagString('');
    }
  }, [tagString]);

  useEffect(() => {
    setTagString('')
  }, [post.tag]);


  useEffect(() => {
    console.log(post);
  }, [post]);

  const onSubmitPost = async () => {
    onSubmit(post);
  }

  const onTitleChange = (e) => {
    setPost({
      ...post,
      title: e.target.value,
    })
    setTagString('');
  }

  const onContentChange = (e) => {
    setPost({
      ...post,
      content: e.target.value,
    })
  }

  const onTagStringChange = (e) => {
    if(post.tag.length >= 5) return;
    setTagString(e.target.value);
  }

  const onTagDelete = (idx) => {
    const updatedHashtags = [...post.tag.slice(0, idx), ...post.tag.slice(idx + 1)];
    setPost({
      ...post,
      tag: updatedHashtags
    })
  }

  return (
    <div>
      <Input
        value={post.title}
        label="제목"
        onInput={onTitleChange}/>
      <label>내용</label>
      <textarea
        value={post.content}
        onInput={onContentChange}
        className="form-control mb-3"
        rows="10"/>
      <div className="mb-3 d-flex">
        {post.tag.map((tag, index) =>
            <button key={index} className="me-2 btn btn-primary d-flex">
              <span>#{tag}</span>
              <button
                onClick={() => onTagDelete(index)}
                type="button"
                className="btn-close"
                aria-label="Close"></button>
            </button>
        )}
      </div>
      <Input
        onInput={onTagStringChange}
        value={tagString}
        label="해시태그"/>
      <div className="d-flex justify-content-end">
        <Button
          word="제출하기"
          onClick={onSubmitPost}
          disabled={!availableSubmit}
        />
      </div>
    </div>
  );
}

export default PostEditor;