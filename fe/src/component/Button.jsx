const Button = ({
                  word,
                  className,
                  onClick
}) => {
  return (
    <button
      type="button"
      className={`btn btn-primary btn-sm ${className}`}
      onClick={onClick}
    >
      {word}
    </button>
  )
}

export default Button;