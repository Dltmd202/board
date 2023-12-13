export const Input = ({
                        type,
                        value,
                        label,
                        onInput,
                        status,
                        valideMessage,
                        invalidMessage
                      }) => {

  const checkInvalid = () => {
    if(status === undefined) return '';
    if (status === false) return 'is-invalid';
    if (status === true) return 'is-valid';
  }

  const valificationMessage = () => {
    if(status === undefined) return '';
    if (status === false) return invalidMessage;
    if (status === true) return valideMessage;
  }

  return (
    <div className="mb-3 ">
      <div className="d-flex justify-content-between">
        <label>{label}</label>
        <p>{valificationMessage()}</p>
      </div>
      <input
        value={value}
        onInput={onInput}
        type={type}
        className={`form-control ${checkInvalid()}`} />
    </div>
  )
}

export default Input;