import 'bootstrap/dist/css/bootstrap.min.css';

const Header = ({child}) => {
  return (
    <nav className="navbar navbar-expand-lg bg-body-tertiary">
      <div className="container-fluid">
        <div className="header_center">NTS</div>
        <div className="header_left">{child}</div>
      </div>
    </nav>
  )
}

export default Header;