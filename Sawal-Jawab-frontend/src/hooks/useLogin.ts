import { useState } from 'react';
import { useToast } from "./use-toast";
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
          email: loginData.email,
          password: loginData.password
        }),
      });

      const data = await response.json();

      if (!response.ok) {
        throw new Error(data.message || 'Login failed');
      }

      // Store JWT token in localStorage
      localStorage.setItem('token', data.token);
      
      return {
        success: true,
        message: 'Login successful',
        token: data.token
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

  const handleLogin = async (
    loginData: LoginData,
    setCurrentUser: (user: any) => void,
    setShowLoginModal: (show: boolean) => void,
    setLoginData: (data: LoginData) => void,
    setLoginError: (error: string) => void
  ) => {
    setLoginError('');
    
    if (!loginData.email || !loginData.password) {
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
        email: loginData.email,
        username: loginData.email.split('@')[0]
      });
      setShowLoginModal(false);
      setLoginData({
        email: '',
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
    handleLogin,
    isLoading,
    error
  };
};
