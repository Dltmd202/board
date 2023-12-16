import React, {useState} from "react";
import Input from "./Input";
import Button from "./Button";
import {Link, useNavigate} from "react-router-dom";
import {isEmailFormat} from "../common/utils/email";
import {userApi} from "../api/user";

const Login = () => {
  const navigate = useNavigate();
  const [signin, setSignin] = useState({});
  const [validatetdEmail, setValidatetdEmail] = useState(null);

  const onEmailChange = (e) => {
    if(isEmailFormat(signin.email)) setValidatetdEmail(true);
    else setValidatetdEmail(false);

    setSignin({
      ...signin,
      email: e.target.value,
    });
  }

  const onPasswordChange = (e) => {
    setSignin({
      ...signin,
      password: e.target.value,
    });
  }

  const submitSignup = async () => {
    try {
      const { data } = await userApi.signin(signin);
      localStorage.setItem("TOKEN", data.response.sessionId);
      localStorage.setItem("NAME", data.response.email);
      alert('로그인에 성공햇습니다.');
      navigate('/');
    } catch (e){
      alert(e.response.data.error.message);
      setSignin({
        email: '',
        password: '',
      })
    }
  }

  return (
    <div>
      <div className="text-center">
        <h1 className="py-5">로그인</h1>
      </div>
      <div className="m-5">
        <div className="row justify-content-between">
          <div className="col-12">
            <Input
              label="아이디"
              type="text"
              onInput={onEmailChange}
              value={signin.email}
              status={validatetdEmail}
            />
          </div>
        </div>
        <div className="row">
          <div className="col-12">
            <Input
              label="비밀번호"
              type="password"
              onInput={onPasswordChange}
              value={signin.password}
            />
          </div>
        </div>
        <div className="row mt-5">
          <Button
            word="로그인"
            className={"py-2"}
            onClick={submitSignup}
          />
        </div>
      </div>
      <div className="d-flex justify-content-center align-items-center">
        <Link to="/join">회원가입하기</Link>
      </div>
    </div>
  )
}

export default Login;