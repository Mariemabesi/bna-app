import React from "react";
import {
  PieChart as RePieChart,
  Pie,
  Tooltip,
  Legend,
  Cell,
  ResponsiveContainer
} from "recharts";

const data = [
  { name: "Épargne", value: 400 },
  { name: "Investissements", value: 300 },
  { name: "Dépenses", value: 300 },
  { name: "Autres", value: 200 },
];

// Couleurs en vert et variantes
const COLORS = ["#00703c", "#f80707ff", "#dfee07ff", "#0fe3ffff"];

export default function PieChart() {
  return (
    <div style={{ width: "100%", height: 300, background: "#fff", borderRadius: "12px", padding: "20px" }}>
      <h3 style={{ color: "#740000ff", marginBottom: "10px" }}>Répartition des finances</h3>
      <ResponsiveContainer width="100%" height="90%">
        <RePieChart>
          <Pie
            data={data}
            cx="50%"
            cy="50%"
            labelLine={false}
            outerRadius={100}
            fill="#8884d8"
            dataKey="value"
          >
            {data.map((entry, index) => (
              <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
            ))}
          </Pie>
          <Tooltip />
          <Legend />
        </RePieChart>
      </ResponsiveContainer>
    </div>
  );
}
