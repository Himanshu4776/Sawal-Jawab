import { useState } from 'react';
import { useToast } from "../hooks/use-toast";
import { LoginData } from './types';

interface LoginResponse {
  success: boolean;
  message: string;
  token?: string;
}

export const useLogin = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const { toast } = useToast();

  const apiKey = import.meta.env.VITE_API_URL;

  const login = async (loginData: LoginData): Promise<LoginResponse> => {
    setIsLoading(true);
    setError(null);

    try {
      const response = await fetch(`${apiKey}/user/login`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          userName: loginData.userName,
          password: loginData.password
        }),
      });

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.details || 'Login failed');
      }
      const data = await response.text();

      // Store JWT token in localStorage
      localStorage.setItem('token', data);
      localStorage.setItem('username', loginData.userName);
      
      return {
        success: true,
        message: 'Login successful',
        token: data
      };

    } catch (err) {
      const message = err instanceof Error ? err.message : 'Login failed';
      setError(message);
      return {
        success: false,
        message
      };
    } finally {
      setIsLoading(false);
    }
  };
  const handleLoginSubmit = async (
    loginData: LoginData,
    setCurrentUser: (user: any) => void,
    setShowLoginModal: (show: boolean) => void,
    setLoginData: (data: LoginData) => void,
    setLoginError: (error: string) => void
  ) => {
    setLoginError('');
    
    if (!loginData.userName || !loginData.password) {
      setLoginError('Please fill in all fields');
      toast({
        variant: "destructive",
        title: "Login Error",
        description: "Please fill in all fields"
      });
      return;
    }

    const response = await login(loginData);
    
    if(response && response.success) {
      setCurrentUser({
        userName: loginData.userName,
        username: loginData.userName
      });
      setShowLoginModal(false);
      setLoginData({
        userName: '',
        password: ''
      });
      toast({
        title: "Login Successful",
        description: "Welcome back to Sawal Jawab!"
      });
    } else {
      setLoginError(response.message);
      toast({
        variant: "destructive",
        title: "Login Failed",
        description: response.message || "An error occurred during login"
      });
    }
  };

  return {
    login,
    handleLoginSubmit,
    isLoading,
    error
  };
};
