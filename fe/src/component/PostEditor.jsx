import Input from "./Input";
import Button from "./Button";
import {useEffect, useState} from "react";

const PostEditor = () => {
  const [post, setPost] = useState({
    title: '',
    content: '',
  })
  const [availableSubmit, setAvailableSubmit] = useState(false);

  useEffect(() => {
    setAvailableSubmit(0 < post.title.length && post.content.length < 255);
  }, [post.title])


  useEffect(() => {
    console.log(post);
  }, [post]);

  const onTitleChange = (e) => {
    setPost({
      ...post,
      title: e.target.value,
    })
  }

  const onContentChange = (e) => {
    setPost({
      ...post,
      content: e.target.value,
    })
  }

  return (
    <div>
      <Input label="제목" onInput={onTitleChange}/>
      <label>내용</label>
      <textarea
        onInput={onContentChange}
        className="form-control mb-3"
        rows="10" />
      <Input label="해시태그" />
      <div className="d-flex justify-content-end">
        <Button word="제출하기" disabled={!availableSubmit} />
      </div>
    </div>
  );
}

export default PostEditor;