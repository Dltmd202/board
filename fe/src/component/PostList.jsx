const PostList = () => {
  const titleStyle = {minWidth: '200px'};

  return (
    <div>
      <h1 className="mt-5 mb-2">게시판</h1>
      <table className="table table-hover">
        <thead className="table-light">
        <tr>
          <th scope="col" style={titleStyle}>제목</th>
          <th scope="col">작성자</th>
          <th scope="col">작성일시</th>
          <th scope="col">댓글</th>
          <th scope="col">조회수</th>
          <th scope="col">좋아요</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td>제목1</td>
          <td>작성자1</td>
          <td>2023.12.12</td>
          <td>10</td>
          <td>1000</td>
          <td>3</td>
        </tr>
        <tr>
          <td>제목1</td>
          <td>작성자1</td>
          <td>2023.12.12</td>
          <td>10</td>
          <td>1000</td>
          <td>3</td>
        </tr>
        <tr>
          <td>제목1</td>
          <td>작성자1</td>
          <td>2023.12.12</td>
          <td>10</td>
          <td>1000</td>
          <td>3</td>
        </tr>
        <tr>
          <td>제목1</td>
          <td>작성자1</td>
          <td>2023.12.12</td>
          <td>10</td>
          <td>1000</td>
          <td>3</td>
        </tr>
        <tr>
          <td>제목1</td>
          <td>작성자1</td>
          <td>2023.12.12</td>
          <td>10</td>
          <td>1000</td>
          <td>3</td>
        </tr>
        <tr>
          <td>제목1</td>
          <td>작성자1</td>
          <td>2023.12.12</td>
          <td>10</td>
          <td>1000</td>
          <td>3</td>
        </tr>
        <tr>
          <td>제목1</td>
          <td>작성자1</td>
          <td>2023.12.12</td>
          <td>10</td>
          <td>1000</td>
          <td>3</td>
        </tr>
        <tr>
          <td>제목1</td>
          <td>작성자1</td>
          <td>2023.12.12</td>
          <td>10</td>
          <td>1000</td>
          <td>3</td>
        </tr>
        <tr>
          <td>제목1</td>
          <td>작성자1</td>
          <td>2023.12.12</td>
          <td>10</td>
          <td>1000</td>
          <td>3</td>
        </tr>
        <tr>
          <td>제목1</td>
          <td>작성자1</td>
          <td>2023.12.12</td>
          <td>10</td>
          <td>1000</td>
          <td>3</td>
        </tr>
        </tbody>
      </table>

      <nav aria-label="Page navigation example">
        <ul className="pagination">
          <li className="page-item"><a className="page-link" href="#">Previous</a></li>
          <li className="page-item"><a className="page-link" href="#">1</a></li>
          <li className="page-item"><a className="page-link" href="#">2</a></li>
          <li className="page-item"><a className="page-link" href="#">3</a></li>
          <li className="page-item"><a className="page-link" href="#">4</a></li>
          <li className="page-item"><a className="page-link" href="#">5</a></li>
          <li className="page-item"><a className="page-link" href="#">Next</a></li>
        </ul>
      </nav>
    </div>
  )
}

export default PostList;