import React from "react";
import "./ContentMain.css";
import AssistanceGrid from "../../AssistanceGrid/AssistanceGrid";

// components
import Cards from "../Cards/Cards";
import Budget from "../Budget/Budget";
import BarChart from "../Charts/BarChart";
import LineChart from "../Charts/LineChart";
import PieChart from "../Charts/PieChart";

export default function ContentMain() {
  return (
    <div className="content-main">
      {/* ===== Cards row ===== */}
      <div className="cards-row">
        <Cards />
      </div>

      {/* ===== Budget section ===== */}
      <div className="budget-row">
        <Budget />
      </div>

      {/* ===== Charts row ===== */}
      <div className="charts-row">
        <div className="chart-box">
          <BarChart />
        </div>
        <div className="chart-box">
          <LineChart />
        </div>
        <div className="chart-box">
          <PieChart />
        </div>
      </div>
    </div>
  );
  
}
