import React from 'react';
import '../styles/CalculateScore.css';

function calculatePercentage(total, goal) {
  if (goal === 0) return 0; // Avoid divide-by-zero
  const fullMarks = goal * 100;
  return (total / fullMarks) * 100;
}

function CalculateScore({ name, school, total, goal }) {
  const percentage = calculatePercentage(total, goal);

  return (
    <div className="score-container">
      <h2>Student Score Summary</h2>
      <p><strong>Name:</strong> {name}</p>
      <p><strong>School:</strong> {school}</p>
      <p><strong>Total Score:</strong> {total}</p>
      <p><strong>Subjects:</strong> {goal}</p>
      <p><strong>Percentage:</strong> {percentage.toFixed(2)}%</p>
    </div>
  );
}

export default CalculateScore;
