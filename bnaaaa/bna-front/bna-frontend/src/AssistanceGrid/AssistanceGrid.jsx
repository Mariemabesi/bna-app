import React from "react";
import "./AssistanceGrid.css";
import { FaMapMarkerAlt, FaExchangeAlt, FaInfoCircle } from "react-icons/fa";

export default function AssistanceGrid() {
  return (
    <div className="assistance-grid">
      <h3>Besoin d’une assistance ?</h3>
      <p>Notre équipe est à votre disposition pour tout renseignement.</p>
      <button className="assistance-btn">
        <FaInfoCircle /> Contacter
      </button>

      <div className="section">
        <h4>Demande de Réclamation</h4>
        <button className="info-btn">
          <FaInfoCircle /> Voir
        </button>
      </div>

      <div className="section">
        <h4>Trouver une agence BNA</h4>
        <button className="map-btn">
          <FaMapMarkerAlt /> Découvrir
        </button>
      </div>

      <div className="section">
        <h4>Convertisseur</h4>
        <p>1 TND = 0.348 USD</p>
        <input type="number" placeholder="Montant TND" />
        <button className="convert-btn">
          <FaExchangeAlt /> Convertir
        </button>
      </div>
    </div>
  );
}
