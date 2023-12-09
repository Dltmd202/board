import React from "react";
import Input from "./Input";
import Button from "./Button";
import {Link, useNavigate} from "react-router-dom";

const Login = () => {
  const navigate = useNavigate();

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
              type="text"/>
          </div>
        </div>
        <div className="row">
          <div className="col-12">
            <Input
              label="비밀번호"
              type="password"
            />
          </div>
        </div>
        <div className="row mt-5">
          <Button
            word="로그인"
            className={"py-2"}
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