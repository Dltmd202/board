import {Link, useLocation} from "react-router-dom";
import Header from "../component/Header";
import Container from "../component/Container";
import PostList from "../component/PostList";
import {useEffect, useState} from "react";

const PostListPage = () => {
  const location = useLocation();
  const [readyToCondition, setReadyToCondition] = useState(false);
  const [condition, setCondition] = useState({
    searchType: '',
    searchValue: ''
  });

  useEffect(() => {
    const queryString = location.search;
    const queryParams = new URLSearchParams(queryString);
    const search = getFirstQueryParam(queryParams);
    setCondition({
      searchType: search?.key || '',
      searchValue: search?.value || ''
    });
    setReadyToCondition(true);
  }, [location.search]);

  const getFirstQueryParam = (params) => {
    const firstEntry = params.entries().next().value;
    if (firstEntry) {
      const [key, value] = firstEntry;
      return { key, value };
    }
    return null;
  }

  return(
    <div>
      <Header />
      <Container className="pt-5">
        <nav aria-label="breadcrumb">
          <ol className="breadcrumb">
            <li className="breadcrumb-item"><Link to="/" replace={true}>게시판</Link></li>
            {
              readyToCondition && condition.searchType && (
                <>
                  <li className="breadcrumb-item active" aria-current="page">
                    {condition.searchType}
                  </li>
                  <li className="breadcrumb-item active" aria-current="page">
                    {condition.searchValue}
                  </li>
                </>
              )}
          </ol>
        </nav>
        {readyToCondition && (
          <PostList
            searchQuery={condition.searchValue}
            searchCondition={condition.searchType}/>
        )}
      </Container>
    </div>
  );
}

export default PostListPage;