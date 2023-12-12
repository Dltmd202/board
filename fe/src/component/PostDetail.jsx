const PostDetail = ({postId}) => {
  return (
    <div>
      <div>
        <div>
          <h1 className="mt-5 mb-4">안녕하세요</h1>
          <hr/>
        </div>
        <div>
          <div className="d-flex justify-content-between">
            <div className="d-flex">
              <p>작성자:</p>
              <p>dltmd202@gmail.com</p>
            </div>
            <div className="d-flex">
              <p>작성일자:</p>
              <p>2023.12.12. 17:32</p>
            </div>
            <div className="d-flex">
              <p>조회:</p>
              <p>1,000</p>
            </div>
          </div>
          <hr/>
        </div>
        <div>
          <p>책방 아리아 미리내 가온해 바나나 나비잠 다솜 아슬라 예그리나 아름드리 바람꽃 컴퓨터 도담도담 바나나 도담도담 늘품 소솜 옅구름 나비잠 여우비 함초롱하다 도서관 옅구름 감사합니다 나래 컴퓨터
            소록소록 예그리나 감사합니다 포도 감또개 아슬라 소솜 미리내 아슬라 바람꽃 안녕 가온해 도담도담 늘품 책방 예그리나 감또개 소솜 도르레 미쁘다 포도 감사합니다 늘품 포도.</p>
          <br/>
          <p>소록소록 소솜 그루잠 컴퓨터 옅구름 함초롱하다 나래 도서 다솜 함초롱하다 사과 아련 여우비 도서관 포도 우리는 여우비 컴퓨터 아리아 바나나 감사합니다 미쁘다 나비잠 도담도담 감사합니다 소솜
            아련 감또개 달볓 아름드리 도담도담 소록소록 함초롱하다 도르레 소솜 여우비 우리는 아슬라 감사합니다 도담도담 노트북 여우비 노트북 도르레 노트북 도담도담 여우비 컴퓨터 달볓 포도.</p>
          <br/>
          <p>노트북 늘품 감또개 아슬라 안녕 옅구름 감또개 소솜 로운 나래 소솜 늘품 아름드리 바나나 별빛 도담도담 아리아 아슬라 옅구름 달볓 다솜 도르레 미쁘다 달볓 곰다시 여우비 옅구름 사과 늘품
            그루잠 책방 감사합니다 도서 산들림 나비잠 도담도담 소솜 소록소록 곰다시 도서관 사과 감사합니다 산들림 도서 미쁘다 우리는 안녕 감사합니다 함초롱하다 도르레.</p>
          <br/>
        </div>
        <div className="row">
          <div className="col-lg-4 col-sm-8 d-flex justify-content-start mb-3">
            <button type="button" className="btn btn-outline-primary me-1">
              <div className="d-flex">
                <p className="me-1">좋아요</p><p>100000</p>
              </div>
            </button>
            <button type="button" className="btn btn-danger">
              <div className="d-flex">
                <p className="me-1">싫어요</p><p>100000</p>
              </div>
            </button>
          </div>
          <hr/>
        </div>
        <div>
          <div className="d-flex mb-4 text-body-secondary">
            <p className="me-1">댓글</p>
            <p>1</p>
            <p>개</p>
          </div>
          <div className="mb-5">
            <div className="d-flex">
              <div className="col-sm-10 mb-3 pe-2">
                <p className="mb-1">abc123@gmail.com</p>
                <p className="mb-1">게시글에 댓글을 쓰면 이런 형식으로 보여집니다.</p>
                <p className="text-body-secondary">2023.11.23.23:15</p>
              </div>
              <div className="col-sm-2 d-flex justify-content-end lh-1 align-items-start">
                <button type="button" className="btn btn-sm btn-outline-primary me-1">삭제하기</button>
              </div>
            </div>
            <hr />
            <div className="d-flex justify-content-between mb-3">
              <div className="col-sm-10 mb-3 pe-2">
                <p className="mb-1">abc123@gmail.com</p>
                <p className="mb-1">게시글에 댓글을 쓰면 이런 형식으로 보여집니다. 게시글에 댓글을 쓰면 이런 형식으로 보여집니다. 게시글에 댓글을 쓰면 이런 형식으로 보여집니다.</p>
                <p className="text-body-secondary">2023.11.23.23:15</p>
              </div>
              <div className="col-sm-2 d-flex justify-content-end lh-1 align-items-start">
                <button type="button" className="btn btn-sm btn-outline-primary me-1">삭제하기</button>
              </div>
            </div>
            <hr/>
          </div>
          <div className="mb-5">
            <div className="d-flex justify-content-between mb-2">
              <label className="text-al text-body-secondary mb-2">dltmd202@gmail.com</label>
              <button className="btn btn-sm btn-outline-primary">제출하기</button>
            </div>
            <textarea className="form-control" id="comment" rows="3"></textarea>
          </div>
        </div>
      </div>


    </div>
  );
}

export default PostDetail;