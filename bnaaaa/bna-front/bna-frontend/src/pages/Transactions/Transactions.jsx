import React from "react";
import Sidebar from "../../components/Sidebar/Sidebar";
import "./Transactions.css";

export default function Transactions() {
  return (
    <div className="layout">
      <Sidebar />
      <div className="content-main-wrapper">
        <h1>Transactions</h1>
        {/* ton tableau ou liste de transactions */}
      </div>
    </div>
  );
}
