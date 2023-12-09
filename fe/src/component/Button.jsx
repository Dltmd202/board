const Button = ({
                  word,
                  className,
                  disabled,
                  onClick,
}) => {
  return (
    <button
      type="button"
      className={`btn btn-primary btn-sm ${className} ${disabled ? 'disabled' : ''}`}
      onClick={onClick}
      disabled={disabled}
    >
      {word}
    </button>
  )
}

export default Button;