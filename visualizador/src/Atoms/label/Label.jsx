import './Label.css';

function Label({ children, htmlFor, required = false }) {
  return (
    <label className="label" htmlFor={htmlFor}>
      {children}
      {required && <span className="label-required">*</span>}
    </label>
  );
}

export default Label;
