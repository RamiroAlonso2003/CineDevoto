import './Select.css';

function Select({ 
  options = [], 
  value, 
  onChange, 
  name, 
  placeholder = 'Seleccionar...',
  required = false 
}) {
  return (
    <select
      className="select"
      value={value}
      onChange={onChange}
      name={name}
      required={required}
    >
      <option value="">{placeholder}</option>
      {options.map((option, index) => (
        <option key={index} value={option.value}>
          {option.label}
        </option>
      ))}
    </select>
  );
}

export default Select;
