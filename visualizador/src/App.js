import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomePage from './templates/HomePage/HomePage';
import MovieDetailPage from './templates/MovieDetailPage/MovieDetailPage';

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/movie/:id" element={<MovieDetailPage />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
