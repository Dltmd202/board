import React, {useEffect, useState} from "react";
import Input from "./Input";
import Button from "./Button";
import {userApi} from "../api/user";
import {useNavigate} from "react-router-dom";

const Join = () => {
  const navigate = useNavigate();
  const [signup, setSignup] = useState({});
  const [validatedEmail, setValidatedEmail] = useState(null);
  const [emailFailMessage, setEmailFailMessage] = useState('');
  const [validPassword, setValidPassword] = useState(null);
  const [validRepeatedPassword, setValidRepeatedPassword] = useState(null);
  const [activeSubmitButton, setActiveSubmitButton] = useState(true);

  useEffect(() => {
  }, [activeSubmitButton])

  useEffect(() => {
    console.log('activeSubmitButton');
    console.log(activeSubmitButton);
    setActiveSubmitButton(validatedEmail && validPassword && validRepeatedPassword || false);
  }, [validatedEmail, validPassword, validRepeatedPassword]);

  useEffect(() => {
    if(signup.password) setValidRepeatedPassword(signup.password === signup.repeatedPassword);
  }, [signup.repeatedPassword]);

  useEffect(() => {
    if(signup.password) setValidPassword(signup.password.length >= 8);
  }, [signup.password]);

  const isEmailFormat = (email) => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  }

  const validateEmail = async () => {
    if(!isEmailFormat(signup.email)) {
      setValidatedEmail(false);
      setEmailFailMessage("올바른 이메일 형식이 아닙니다.");
      return;
    }

    const { data } = await userApi.validateEmail(signup.email);
    if(!data.response.success) setEmailFailMessage('이미 존재하는 아이디입니다.');
    setValidatedEmail(data.response);
  }

  const onEmailChange = (e) => {
    setValidatedEmail(null);
    setSignup({
      ...signup,
      email: e.target.value,
    });
  }

  const onPasswordChange = (e) => {
    setSignup({
      ...signup,
      password: e.target.value,
    });
  }

  const onRepeatedPasswordChange = (e) => {
    setSignup({
      ...signup,
      repeatedPassword: e.target.value,
    });
  }

  const submitSignup = async () => {
    try {
      await userApi.signup(signup);
      alert('회원가입에 성공햇습니다.');
      navigate('/');
    } catch (e){
      alert(e.data.error.message);
      setValidatedEmail(null);
      setValidPassword(null);
      setValidRepeatedPassword(null);
    }
  }


    return (
      <div>
        <div className="text-center">
          <h1 className="py-5">회원가입</h1>
        </div>
        <div className="m-5">
          <div className="row justify-content-between">
            <div className="col-10">
              <Input
                label="아이디"
                type="text"
                onInput={onEmailChange}
                value={signup.email}
                status={validatedEmail}
                invalidMessage={emailFailMessage}
              />
            </div>
            <div className="col-2 d-flex align-items-center">
              <Button word="중복확인" className="w-100 h-50" onClick={validateEmail}/>
            </div>
          </div>
          <div className="row">
            <div className="col-10">
              <Input
                label="비밀번호"
                type="password"
                onInput={onPasswordChange} value={signup.password}
                status={validPassword}
                invalidMessage={"비밀번호는 8자 이상이어야 합니다."}
              />
            </div>
          </div>
          <div className="row">
            <div className="col-10">
              <Input
                label="비밀번호 확인"
                type="password"
                onInput={onRepeatedPasswordChange}
                value={signup.repeatedPassword}
                status={validRepeatedPassword}
                invalidMessage={"비밀번호가 일치하지 않습니다."}
              />
            </div>
          </div>
          <div className="row mt-5">
            <Button
              word="회원가입"
              className={"py-2"}
              onClick={submitSignup}
              disabled={!activeSubmitButton}
            />
          </div>
        </div>
      </div>
    )
}

export default Join;