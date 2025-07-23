import React, { useEffect, useState } from "react";
import "./VirtualKeyboard.css";

export default function VirtualKeyboard({ onClose, onSubmit, onDigit }) {
  const [digits, setDigits] = useState([]);

  useEffect(() => {
    // Generate and shuffle digits 0-9
    const nums = Array.from({ length: 10 }, (_, i) => i.toString());
    for (let i = nums.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [nums[i], nums[j]] = [nums[j], nums[i]];
    }
    setDigits(nums);
  }, []);

  return (
    <div className="vk-container">
      <div className="vk-grid">
        {digits.map((num) => (
          <button
            key={num}
            type="button"
            onClick={() => onDigit(num)}
            className="vk-digit"
            aria-label={`taper ${num}`}
          >
            {num}
          </button>
        ))}
      </div>
      <div className="vk-actions">
        <button
          type="button"
          className="cancel"
          onClick={onClose}
        >
          annuler
        </button>
        <button
          type="button"
          className="validate"
          onClick={onSubmit}
        >
          valider
        </button>
      </div>
    </div>
  );
}
