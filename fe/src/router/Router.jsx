import {BrowserRouter, Route, Routes} from "react-router-dom";
import JoinPage from "../pages/JoinPage";
import LoginPage from "../pages/LoginPage";
import PostListPage from "../pages/PostListPage";
import PostDetailPage from "../pages/PostDetailPage";
import PostCreatePage from "../pages/PostCreatePage";
import PostEditPage from "../pages/PostEditPage";
import PrivateRouter from "./PrivateRouter";
import PublicRouter from "./PublicRouter";

const Routers = () => {
  return(
    <BrowserRouter>
      <Routes>
        <Route element={<PublicRouter restricted={true}/>}>
          <Route path="/join" element={<JoinPage />} />
          <Route path="/login" element={<LoginPage />} />
        </Route>

        <Route element={<PrivateRouter />}>
          <Route path="/" element={<PostListPage />} />
          <Route path="/:postId" element={<PostDetailPage />} />
          <Route path="/create" element={<PostCreatePage />} />
          <Route path="/:postId/edit" element={<PostEditPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default Routers;