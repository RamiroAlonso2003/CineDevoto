import "./navLink.css";

function NavLink({ children, href = "#", active = false }) {
  return (
    <a
      href={href}
      className={`nav-link ${active ? "active" : ""}`}
    >
      {children}
    </a>
  );
}

export default NavLink;
