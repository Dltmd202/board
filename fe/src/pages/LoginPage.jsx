import Header from "../component/Header";
import Container from "../component/Container";
import Login from "../component/Login";

const LoginPage = () => {

  return(
    <div>
      <Header />
      <Container maxWidth={"864px"}>
        <Login />
      </Container>
    </div>
  );
}

export default LoginPage;