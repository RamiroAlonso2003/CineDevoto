import "./boton.css";

function Button({
  variant = "primary",
  size = "md",
  children,
  onClick,
  disabled = false,
  type = "button"
}) {
  return (
    <button
      className={`btn btn-${variant} btn-${size}`}
      onClick={onClick}
      disabled={disabled}
      type={type}
    >
      {children}
    </button>
  );
}

export default Button;
