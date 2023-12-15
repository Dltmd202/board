import {Navigate, Outlet} from "react-router-dom";

const PrivateRouter = () => {
  const token = localStorage.getItem('token');

  return token ? <Navigate to="/login" /> : <Outlet />;
}

export default PrivateRouter;