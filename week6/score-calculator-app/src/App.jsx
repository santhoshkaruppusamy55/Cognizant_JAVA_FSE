import React from 'react';
import CalculateScore from './components/CalculateScore';

function App() {
  return (
    <div className="App">
      <h1>🎓 Student Management Portal</h1>
      
      {/* Example student */}
      <CalculateScore
        name="NK"
        school="VSB Engineering College"
        total={450}
        goal={5} // Number of subjects
      />
    </div>
  );
}

export default App;
