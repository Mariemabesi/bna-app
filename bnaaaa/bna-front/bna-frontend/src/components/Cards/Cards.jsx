import React from "react";
import "./Cards.css";

export default function Cards() {
  return (
    <div className="cards">
      <div className="card">
        <h4>Solde Actuel</h4>
        <p>5 000 TND</p>
      </div>
      <div className="card">
        <h4>Revenus</h4>
        <p>1 500 TND</p>
      </div>
      <div className="card">
        <h4>Dépenses</h4>
        <p>800 TND</p>
      </div>
    </div>
  );
}
