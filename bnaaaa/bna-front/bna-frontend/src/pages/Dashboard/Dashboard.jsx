import React, { useState } from "react";
import Sidebar from "../../components/Sidebar/Sidebar";
import ContentMain from "../../components/ContentMain/ContentMain";
import Transactions from "../Transactions/Transactions";
import "./Dashboard.css";

export default function Dashboard() {
  const [activePage, setActivePage] = useState("dashboard");

  return (
    <div className="layout">
<Sidebar activePage={activePage} setActivePage={setActivePage} />
      <div className="content">
        {activePage === "dashboard" && <ContentMain />}
        {activePage === "transactions" && <Transactions />}
      </div>
    </div>
  );
}
