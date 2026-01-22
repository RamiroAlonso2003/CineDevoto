import './FormField.css';
import Label from '../../Atoms/label/Label';

function FormField({ 
  label, 
  children, 
  htmlFor, 
  required = false 
}) {
  return (
    <div className="form-field">
      {label && (
        <Label htmlFor={htmlFor} required={required}>
          {label}
        </Label>
      )}
      {children}
    </div>
  );
}

export default FormField;
