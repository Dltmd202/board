import React from "react";
import Header from "../component/Header";
import Join from "../component/Join";
import Container from "../component/Container";

const JoinPage = () => {

  return (
    <div>
      <Header/>
      <Container maxWidth={"864px"}>
        <Join />
      </Container>
    </div>
  );
}

export default JoinPage;