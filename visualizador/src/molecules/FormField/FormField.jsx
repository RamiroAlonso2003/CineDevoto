import './FormField.css';
import Label from '../../Atoms/label/Label';

function FormField({
  label,
  children,
  htmlFor,
  required = false,
  last = false
}) {
  return (
    <div className={`field${last ? ' field--last' : ''}`}>
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
