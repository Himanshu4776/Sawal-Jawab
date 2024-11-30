export interface RegisterResponse {
  success: boolean;
  message: string;
  token?: string;
}

export interface User {
  username: string;
  email: string;
}

export interface Answer {
  id: number;
  text: string;
  votes: number;
  author: string;
  timestamp: string;
}

export interface QuestionData {
  id: number;
  title: string;
  text: string;
  answers: Answer[];
  votes: number;
  author: string;
  timestamp: string;
}

export interface RegisterData {
    username: string;
    email: string;
    password: string;
    confirmPassword: string;
}

export interface LoginData {
    userName: string;
    password: string;
}

export interface QuestionPayload {
    title: string;
    content: string;
  }
  