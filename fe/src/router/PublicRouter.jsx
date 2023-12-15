import {Navigate, Outlet} from "react-router-dom";

const PublicRouter = ({restricted=false}) => {
  const token = localStorage.getItem('TOKEN');

  if(token && restricted) return <Navigate to="/" />;
  return <Outlet />;
}

export default PublicRouter;