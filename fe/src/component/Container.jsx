export const Container = ({children, maxWidth}) => {
  const style = {
    maxWidth: maxWidth,
  };
  return (
    <div className="container-sm" style={style}>
      {children}
    </div>
  )
}

export default Container;