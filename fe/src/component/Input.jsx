export const Input = ({type, label}) => {

  return (
    <div className="mb-3">
      <label type={type} className="">{label}</label>
      <input type={type} className="form-control"/>
    </div>
  )
}

export default Input;