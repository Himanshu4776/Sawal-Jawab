import { useState } from 'react';
import { useToast } from "../hooks/use-toast";

interface Question {
  id: number;
  title: string;
  text: string;
  answers: Answer[];
  votes: number;
  author: string;
  timestamp: string;
}

interface Answer {
  id: number;
  text: string;
  votes: number;
  author: string;
  timestamp: string;
}

export const useQuestions = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const { toast } = useToast();
  
  const apiKey = import.meta.env.VITE_API_URL;

  const fetchQuestions = async (setQuestions: (questions: Question[]) => void) => {
    setIsLoading(true);
    setError(null);

    try {
      const response = await fetch(`${apiKey}/questions/all`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        //   'Authorization': `Bearer ${localStorage.getItem('token')}`
        }
      });

      const data = await response?.json();
      if (!response.ok) {
        throw new Error(data?.message || 'Failed to fetch questions');
      }

      if(data == null) {
        setQuestions([]);
      } else {
        setQuestions(data);
      }
      
    } catch (err) {
      const message = err instanceof Error ? err.message : 'Failed to fetch questions';
      setError(message);
      toast({
        variant: "destructive",
        title: "Error",
        description: message
      });
    } finally {
      setIsLoading(false);
    }
  };

  return {
    fetchQuestions,
    isLoading,
    error
  };
};

// How is the UI?
// Hey Everyone!! I am Himanshu. Welcome to this platform. Could you help us by letting us know how much you liked the platform. How is the User interface and will you help us in making the community grow.
