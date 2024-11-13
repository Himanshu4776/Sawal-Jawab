import React from 'react';
import { Button } from '@/components/ui/button';
import { LogOut } from 'lucide-react';
import Login from './Login';
import Register from './Register';

interface User {
  username: string;
}

interface HeaderProps {
  currentUser: User | null;
  handleLogout: () => void;
  showLoginModal: boolean;
  setShowLoginModal: (show: boolean) => void;
  showRegisterModal: boolean;
  setShowRegisterModal: (show: boolean) => void;
  loginData: { [key: string]: string };
  setLoginData: (data: { [key: string]: string }) => void;
  registerData: { [key: string]: string };
  setRegisterData: (data: { [key: string]: string }) => void;
  handleLogin: () => void;
  handleRegister: () => void;
  loginError: string | null;
  registerError: string | null;
}

export const Header: React.FC<HeaderProps> = ({
  currentUser,
  handleLogout,
  showLoginModal,
  setShowLoginModal,
  showRegisterModal,
  setShowRegisterModal,
  loginData,
  setLoginData,
  registerData,
  setRegisterData,
  handleLogin,
  handleRegister,
  loginError,
  registerError,
}) => {
  return (
    <div className="flex justify-between items-center">
      <h1 className="text-2xl font-bold">Sawal Jawab</h1>
      <div className="flex gap-2">
        {currentUser ? (
          <div className="flex items-center gap-4">
            <span className="text-sm">Welcome, {currentUser.username}</span>
            <Button onClick={handleLogout} variant="outline" size="sm">
              <LogOut className="w-4 h-4 mr-2" />
              Logout
            </Button>
          </div>
        ) : (
          <>
            <Login
              showModal={showLoginModal}
              setShowModal={setShowLoginModal}
              loginData={loginData}
              setLoginData={setLoginData}
              handleLogin={handleLogin}
              loginError={loginError}
            />
            <Register
              showModal={showRegisterModal}
              setShowModal={setShowRegisterModal}
              registerData={registerData}
              setRegisterData={setRegisterData}
              handleRegister={handleRegister}
              registerError={registerError}
            />
          </>
        )}
      </div>
    </div>
  );
};
