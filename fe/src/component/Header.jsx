import 'bootstrap/dist/css/bootstrap.min.css';
import {useNavigate} from "react-router-dom";
import Button from "./Button";
import React from "react";

const Header = () => {
  const navigate = useNavigate();

  const onHeaderButtonClick = () => {
    navigate('/login')
  }

  const onLogoutButtonClick = () => {
    localStorage.removeItem('NAME');
    localStorage.removeItem("TOKEN");
    navigate('/login');
  }

  const name = localStorage.getItem('NAME');
  return (
    <nav className="navbar navbar-expand-lg bg-body-tertiary">
      <div className="container-fluid">
        <div className="header_center">NTS</div>
        <div className="header_left">
          {name ?
            <div className="header_left_name d-flex">
              <p className="m-0 align-self-center me-1">{name}</p>
              <Button word="로그아웃" onClick={onLogoutButtonClick}/>
            </div> :
            <Button word="로그인" onClick={onHeaderButtonClick}/> }
        </div>
      </div>
    </nav>
  )
}

export default Header;