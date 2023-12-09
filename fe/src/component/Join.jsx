import React from "react";
import Input from "./Input";
import Button from "./Button";

const Join = () => {
    return (
      <div>
        <div className="text-center">
          <h1 className="py-5">회원가입</h1>
        </div>
        <div className="m-5">
          <div className="row justify-content-between">
            <div className="col-10">
              <Input label="아이디" type="text"/>
            </div>
            <div className="col-2 d-flex align-items-center">
              <Button word="중복확인" className="w-100 h-50"/>
            </div>
          </div>
          <div className="row">
            <div className="col-10">
              <Input label="비밀번호" type="password"/>
            </div>
          </div>
          <div className="row">
            <div className="col-10">
              <Input label="비밀번호 확인" type="password"/>
            </div>
          </div>
          <div className="row mt-5">
            <Button word="회원가입" className={"py-2"}/>
          </div>
        </div>
      </div>
    )
}

export default Join;