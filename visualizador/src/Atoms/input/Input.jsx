import './Input.css';

function Input({ 
  type = 'text', 
  placeholder, 
  value, 
  onChange, 
  name,
  required = false 
}) {
  return (
    <input
      type={type}
      className="input"
      placeholder={placeholder}
      value={value}
      onChange={onChange}
      name={name}
      required={required}
    />
  );
}

export default Input;
