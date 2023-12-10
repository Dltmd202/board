import React from "react";
import Header from "../component/Header";
import {useNavigate} from "react-router-dom";
import Button from "../component/Button";
import Join from "../component/Join";
import Container from "../component/Container";

const JoinPage = () => {
  const navigate = useNavigate();
  const onHeaderButtonClick = () => {
    navigate('/login')
  }

  return (
    <div>
      <Header
        child={<Button word="로그인" onClick={onHeaderButtonClick}/>}
        />
      <Container maxWidth={"864px"}>
        <Join />
      </Container>
    </div>
  );
}

export default JoinPage;