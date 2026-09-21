import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext';
import ProtectedRoute from './routes/ProtectedRoute';
import HomePage from './templates/HomePage/HomePage';
import MovieDetailPage from './templates/MovieDetailPage/MovieDetailPage';
import LoginPage from './templates/LoginPage/LoginPage';
import RegisterPage from './templates/RegisterPage/RegisterPage';
import SeatSelectionPage from './templates/SeatSelectionPage/SeatSelectionPage';

function App() {
  return (
    <AuthProvider>
      <Router>
        <div className="App">
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/movie/:id" element={<MovieDetailPage />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/register" element={<RegisterPage />} />
            <Route
              path="/showtime/:showtimeId/asientos"
              element={
                <ProtectedRoute>
                  <SeatSelectionPage />
                </ProtectedRoute>
              }
            />
          </Routes>
        </div>
      </Router>
    </AuthProvider>
  );
}

export default App;
