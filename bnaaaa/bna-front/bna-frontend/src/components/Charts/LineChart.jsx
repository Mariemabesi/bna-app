import React from "react";
import {
  LineChart as ReLineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  ResponsiveContainer
} from "recharts";

const data = [
  { name: "Jan", revenus: 4000, dépenses: 2400 },
  { name: "Feb", revenus: 3000, dépenses: 1398 },
  { name: "Mar", revenus: 2000, dépenses: 9800 },
  { name: "Apr", revenus: 2780, dépenses: 3908 },
  { name: "May", revenus: 1890, dépenses: 4800 },
  { name: "Jun", revenus: 2390, dépenses: 3800 },
];

export default function LineChart() {
  return (
    <div style={{ width: "100%", height: 300, background: "#fff", borderRadius: "12px", padding: "20px" }}>
      <h3 style={{ color: "#700000ff", marginBottom: "10px" }}>Tendance Mensuelle</h3>
      <ResponsiveContainer width="100%" height="90%">
        <ReLineChart
          data={data}
          margin={{ top: 10, right: 30, left: 0, bottom: 5 }}
        >
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="name" />
          <YAxis />
          <Tooltip />
          <Legend />
          <Line type="monotone" dataKey="revenus" stroke="#4400ffff" strokeWidth={3} />
          <Line type="monotone" dataKey="dépenses" stroke="#df00f3ff" strokeWidth={3} />
        </ReLineChart>
      </ResponsiveContainer>
    </div>
  );
}
