export const Container = ({children, maxWidth, className}) => {
  const style = {
    maxWidth: maxWidth,
  };
  return (
    <div className={`container-sm ${className}`} style={style}>
      {children}
    </div>
  )
}

export default Container;