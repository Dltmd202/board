import {BrowserRouter, Route, Routes} from "react-router-dom";
import JoinPage from "../pages/JoinPage";
import LoginPage from "../pages/LoginPage";
import PostListPage from "../pages/PostListPage";

const Routers = () => {
  return(
    <BrowserRouter>
      <Routes>
        <Route path="/join" element={<JoinPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/posts" element={<PostListPage />} />
      </Routes>
    </BrowserRouter>
  )
}

export default Routers;