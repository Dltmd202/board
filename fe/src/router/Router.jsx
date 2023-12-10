import {BrowserRouter, Route, Routes} from "react-router-dom";
import JoinPage from "../pages/JoinPage";
import LoginPage from "../pages/LoginPage";

const Routers = () => {
  return(
    <BrowserRouter>
      <Routes>
        <Route path="/join" element={<JoinPage />} />
        <Route path="/login" element={<LoginPage />} />
      </Routes>
    </BrowserRouter>
  )
}

export default Routers;