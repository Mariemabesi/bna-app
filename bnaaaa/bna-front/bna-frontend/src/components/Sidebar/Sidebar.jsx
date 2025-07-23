import React, { useState } from "react";
import "./Sidebar.css";
import {
  FaBars,
  FaHome,
  FaChartPie,
  FaPaperPlane,
  FaWallet,
  FaChartLine,
  FaPiggyBank,
  FaLightbulb,
  FaUser,
  FaCog
} from "react-icons/fa";

export default function Sidebar({ setActivePage, activePage }) {
  const [collapsed, setCollapsed] = useState(false);

  return (
    <>
      {/* Bouton toggle (mobile) */}
      <div className="sidebar-toggle" onClick={() => setCollapsed(!collapsed)}>
        <FaBars />
      </div>

      <aside className={`sidebar ${collapsed ? "collapsed" : ""}`}>
        {/* Logo / Titre */}
        <div className="sidebar-header">
          {!collapsed && (
            <>
              <img src="/bnalogo.png" alt="BNA Logo" className="sidebar-logo" />
              <h2>BNA eBanking</h2>
            </>
          )}
        </div>

        {/* Menu */}
        <ul className="sidebar-menu">
          <li
            className={activePage === "dashboard" ? "active" : ""}
            onClick={() => setActivePage("dashboard")}
          >
            <FaHome className="icon" />
            {!collapsed && "Dashboard"}
          </li>

          <li
            className={activePage === "budget" ? "active" : ""}
            onClick={() => setActivePage("budget")}
          >
            <FaChartPie className="icon" />
            {!collapsed && "Budget"}
          </li>

          <li
            className={activePage === "transactions" ? "active" : ""}
            onClick={() => setActivePage("transactions")}
          >
            <FaPaperPlane className="icon" />
            {!collapsed && "Transactions"}
          </li>

          {/* Tu peux continuer à ajouter d’autres sections comme ceci */}
          <li
            className={activePage === "subscriptions" ? "active" : ""}
            onClick={() => setActivePage("subscriptions")}
          >
            <FaWallet className="icon" />
            {!collapsed && "Subscriptions"}
          </li>

          <li
            className={activePage === "loans" ? "active" : ""}
            onClick={() => setActivePage("loans")}
          >
            <FaChartLine className="icon" />
            {!collapsed && "Loans"}
          </li>

          <li
            className={activePage === "reports" ? "active" : ""}
            onClick={() => setActivePage("reports")}
          >
            <FaChartLine className="icon" />
            {!collapsed && "Reports"}
          </li>

          <li
            className={activePage === "savings" ? "active" : ""}
            onClick={() => setActivePage("savings")}
          >
            <FaPiggyBank className="icon" />
            {!collapsed && "Savings"}
          </li>

          <li
            className={activePage === "advice" ? "active" : ""}
            onClick={() => setActivePage("advice")}
          >
            <FaLightbulb className="icon" />
            {!collapsed && "Financial Advice"}
          </li>

          <li
            className={activePage === "account" ? "active" : ""}
            onClick={() => setActivePage("account")}
          >
            <FaUser className="icon" />
            {!collapsed && "Account"}
          </li>

          <li
            className={activePage === "settings" ? "active" : ""}
            onClick={() => setActivePage("settings")}
          >
            <FaCog className="icon" />
            {!collapsed && "Settings"}
          </li>
        </ul>
      </aside>
    </>
  );
}
