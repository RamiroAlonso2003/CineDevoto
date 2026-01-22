import "./CardsGrid.css";

function CardsGrid({ children }) {
  return (
    <section className="cards-grid">
      {children}
    </section>
  );
}

export default CardsGrid;
