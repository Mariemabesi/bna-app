import React, { useEffect, useState } from "react";
import "./Transactions.css";

export default function Transactions() {
  const [transactions, setTransactions] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchTransactions = async () => {
      try {
        const user = JSON.parse(localStorage.getItem("user"));
        const token = localStorage.getItem("token");
        if (!user || !token) return;

        const res = await fetch(`http://localhost:8080/api/transactions/user/${user.username}`, {
          headers: {
            "Authorization": `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        });
        if (!res.ok) {
          console.error("Erreur serveur:", res.status);
          return;
        }
        const data = await res.json();
        setTransactions(data);
      } catch (err) {
        console.error("Erreur réseau:", err);
      } finally {
        setLoading(false);
      }
    };

    fetchTransactions();
  }, []);

  if (loading) {
    return <p>Chargement des transactions...</p>;
  }

  return (
    <div className="transactions-page">
      <h2>📋 Mes transactions</h2>
      <table className="transactions-table">
        <thead>
          <tr>
            <th>Date</th>
            <th>Type</th>
            <th>Montant</th>
            <th>Description</th>
          </tr>
        </thead>
        <tbody>
          {transactions.map((t) => (
            <tr key={t.id}>
              <td>{new Date(t.date).toLocaleDateString()}</td>
              <td>{t.type}</td>
              <td>{t.amount} €</td>
              <td>{t.description}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
