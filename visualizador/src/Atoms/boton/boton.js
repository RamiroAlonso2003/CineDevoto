import "./boton.css";

function Boton({
  variant = "primary",
  block = false,
  children,
  onClick,
  disabled = false,
  type = "button"
}) {
  const className = `boton boton--${variant}${block ? " boton--block" : ""}`;
  return (
    <button
      className={className}
      onClick={onClick}
      disabled={disabled}
      type={type}
    >
      {children}
    </button>
  );
}

export default Boton;
