import {useNavigate} from "react-router-dom";
import Header from "../component/Header";
import Button from "../component/Button";
import Container from "../component/Container";
import Login from "../component/Login";

const LoginPage = () => {
  const navigate = useNavigate();

  const onLoginButtonClick = () => {
    navigate('/login');
  }

  return(
    <div>
      <Header child={<Button word="로그인" onClick={onLoginButtonClick}/> }/>
      <Container maxWidth={"864px"}>
        <Login />
      </Container>
    </div>
  );
}

export default LoginPage;