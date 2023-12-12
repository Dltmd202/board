import Comment from "./Comment";

const PostDetail = ({postId}) => {
  const post = {
    title: "안녕하세요",
    user: "dltmd202@gmail.com",
    createdAt: "2023.12.12. 17:32",
    content: `책방 아리아 미리내 가온해 바나나 나비잠 다솜 아슬라 예그리나 아름드리 바람꽃 컴퓨터 도담도담 바나나 도담도담 늘품 소솜 옅구름 나비잠 여우비 함초롱하다 도서관 옅구름 감사합니다 나래 컴퓨터 소록소록 예그리나 감사합니다 포도 감또개 아슬라 소솜 미리내 아슬라 바람꽃 안녕 가온해 도담도담 늘품 책방 예그리나 감또개 소솜 도르레 미쁘다 포도 감사합니다 늘품 포도.
              소록소록 소솜 그루잠 컴퓨터 옅구름 함초롱하다 나래 도서 다솜 함초롱하다 사과 아련 여우비 도서관 포도 우리는 여우비 컴퓨터 아리아 바나나 감사합니다 미쁘다 나비잠 도담도담 감사합니다 소솜 아련 감또개 달볓 아름드리 도담도담 소록소록 함초롱하다 도르레 소솜 여우비 우리는 아슬라 감사합니다 도담도담 노트북 여우비 노트북 도르레 노트북 도담도담 여우비 컴퓨터 달볓 포도.
              노트북 늘품 감또개 아슬라 안녕 옅구름 감또개 소솜 로운 나래 소솜 늘품 아름드리 바나나 별빛 도담도담 아리아 아슬라 옅구름 달볓 다솜 도르레 미쁘다 달볓 곰다시 여우비 옅구름 사과 늘품 그루잠 책방 감사합니다 도서 산들림 나비잠 도담도담 소솜 소록소록 곰다시 도서관 사과 감사합니다 산들림 도서 미쁘다 우리는 안녕 감사합니다 함초롱하다 도르레.
    `}


  return (
    <div>
      <div>
        <div>
          <div>
            <h1 className="mt-5 mb-4">{post.title}</h1>
            <hr/>
          </div>
          <div>
            <div className="d-flex justify-content-between">
              <WriterInformation label="작성자" info={post.user}/>
              <WriterInformation label="작성일자" info={post.createdAt}/>
              <WriterInformation label="조회" info={"1,000"}/>
            </div>
            <hr/>
          </div>
          <div>
            {post.content.split('\n').map((line, i) => (
              <>
                <p key={i}>{line}</p>
                <br/>
              </>
            ))}
          </div>
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
        <Comment/>
      </div>
    </div>
  );
}

function WriterInformation({label, info}) {
  return <div className="d-flex">
    <p>{label}:</p>
    <p>{info}</p>
  </div>;
}

export default PostDetail;