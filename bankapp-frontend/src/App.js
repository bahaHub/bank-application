import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Navbar from './layout/Navbar';
import Home from './pages/Home';
import AddUser from './Users/AddUser';
import Account from './pages/Account';
import AddAccount from './Users/AddAccount';
import Deposit from './Users/Deposit';
import Withdraw from './Users/Withdraw';
import Deactivate from './Users/Deactivate';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

function App() {
  return (
    <div className="App">
      <Router>
          <Navbar/>
          <Routes>
            <Route exact path="/" element={<Home/>} />
            <Route exact path="/adduser" element={<AddUser/>} />
            <Route exact path="/account/:custNo" element={<Account/>} />
            <Route exact path="/addaccount/:custNo" element={<AddAccount/>} />
            <Route exact path="/deposit/:custNo" element={<Deposit/>} />
            <Route exact path="/withdraw/:custNo" element={<Withdraw/>} />
            <Route exact path="/deactivate/:custNo" element={<Deactivate/>} />
          </Routes>
      </Router>
  
    </div>
  );
}

export default App;
